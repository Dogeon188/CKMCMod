package me.ckffmc.farm.block;

import me.ckffmc.farm.block.entity.MillstoneBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class MillstoneBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty MILLING = BooleanProperty.of("milling");
    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.createCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 11.0D, 13.0D));

    public MillstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(MILLING, false);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, MILLING);
    }

    public BlockEntity createBlockEntity(BlockView world) { return new MillstoneBlockEntity(); }

    public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        else {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
    }

    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MillstoneBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MillstoneBlockEntity) {
                ItemScatterer.spawn(world, pos, (MillstoneBlockEntity) blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        MillstoneBlockEntity entity = (MillstoneBlockEntity) world.getBlockEntity(pos);
        if (entity != null && entity.isCrafting()) {
            double d = pos.getX() + 0.5D;
            double e = pos.getY();
            double f = pos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.ENTITY_MINECART_INSIDE, SoundCategory.BLOCKS, 0.1F, 1.0F, false);
            }
            double i = random.nextDouble() * 0.6D - 0.3D;
            double j = 0.6875D;
            double k = random.nextDouble() * 0.6D - 0.3D;
            world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, entity.getStack(0)),
                    d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
        }
    }
}
