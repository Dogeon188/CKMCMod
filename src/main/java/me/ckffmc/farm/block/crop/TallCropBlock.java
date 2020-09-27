package me.ckffmc.farm.block.crop;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.concurrent.Callable;

public class TallCropBlock extends CropBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static final IntProperty AGE;
    public static final VoxelShape[] SHAPES;
    private final Callable<ItemConvertible> SEEDS_ITEM_CALLABLE;
    private final Callable<Block> REPRESENTATIVE_CALLABLE;

    public TallCropBlock(Callable<ItemConvertible> seedsItem, Callable<Block> representative, Settings settings) {
        super(settings.noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
        this.SEEDS_ITEM_CALLABLE = seedsItem;
        this.REPRESENTATIVE_CALLABLE = representative;
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(HALF) == DoubleBlockHalf.UPPER ? this.getAge(state) - 4 : Math.min(3, this.getAge(state))];
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        DoubleBlockHalf h = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && h == DoubleBlockHalf.LOWER == (direction == Direction.UP) && state.get(AGE) > this.getMidAge() && (!newState.isOf(this) || newState.get(HALF) == h))
            return Blocks.AIR.getDefaultState();
        if (h == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos))
            return Blocks.AIR.getDefaultState();
        if ((world.getBlockState(pos.down()).isOf(this.getInstance()) && h == DoubleBlockHalf.UPPER) || (world.getBlockState(pos.up()).isOf(this.getInstance()) && h == DoubleBlockHalf.LOWER))
            return state;
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER && world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge() && random.nextInt((int) (25.0F / getAvailableMoisture(this, world, pos)) + 1) == 0) {
                i++;
                BlockPos posUp = pos.up();
                BlockState stateUp = world.getBlockState(posUp);
                if (i <= this.getMidAge()) world.setBlockState(pos, this.withAge(i), 2);
                else if (stateUp.isAir() || stateUp.isOf(this.getInstance())) {
                    world.setBlockState(pos, this.withAge(i), 2);
                    world.setBlockState(posUp, this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
                }
            }
        }
    }

    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getGrowthAmount(world);
        int j = this.getMaxAge();
        if (i > j) i = j;

        BlockState stateUp = world.getBlockState(pos.up());
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            world.setBlockState(pos, this.withAge(i), 2);
            world.setBlockState(pos.down(), this.withAge(i).with(HALF, DoubleBlockHalf.LOWER), 2);
        } else if (i > this.getMidAge() && (stateUp.isAir() || stateUp.isOf(this.getInstance()))) {
            world.setBlockState(pos, this.withAge(i), 2);
            world.setBlockState(pos.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
        } else world.setBlockState(pos, this.withAge(Math.min(3, i)), 2);
    }

    public int getMidAge() { return 3; }

    protected Block getInstance() {
        try { return this.REPRESENTATIVE_CALLABLE.call();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() {
        try { return this.SEEDS_ITEM_CALLABLE.call();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF); builder.add(AGE);
    }

    static {
        HALF = Properties.DOUBLE_BLOCK_HALF;
        AGE = Properties.AGE_7;
        SHAPES = new VoxelShape[]{
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    }
}
