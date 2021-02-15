package me.ckffmc.farm.block;

import me.ckffmc.farm.sound.MySoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class FruitLeavesBlock extends LeavesBlock implements Fertilizable {
    public static final BooleanProperty HAS_FRUIT = BooleanProperty.of("has_fruit");
    private final Supplier<ItemStack> fruitItem;

    public FruitLeavesBlock(Supplier<ItemStack> fruitItem, Settings settings) {
        super(settings);
        this.fruitItem = fruitItem;
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(HAS_FRUIT, false).with(DISTANCE, 7)
                .with(PERSISTENT, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_FRUIT);
    }

    public boolean hasRandomTicks(BlockState state) { return super.hasRandomTicks(state) || !state.get(HAS_FRUIT); }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!state.get(PERSISTENT) && !state.get(HAS_FRUIT) && adjacentToAir(world, pos) && random.nextFloat() <= 0.005F)
            world.setBlockState(pos, state.with(HAS_FRUIT, true));
    }

    private boolean adjacentToAir(ServerWorld world, BlockPos pos) {
        for (Direction direction : FACINGS) {
            if (world.getBlockState(pos.offset(direction)).isAir()) return true;
        }
        return false;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(HAS_FRUIT)) {
            dropStack(world, pos, getFruitStack());
            world.setBlockState(pos, state.with(HAS_FRUIT, false));
            if (world.isClient) {
                ((ClientWorld)world).playSound(pos, MySoundEvents.MANGO_PICK_FROM_LEAVES, SoundCategory.BLOCKS,
                        1F, 1F, false);
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    protected ItemStack getFruitStack() {
        return fruitItem.get();
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) { return true; }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) { return !state.get(HAS_FRUIT); }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (random.nextFloat() <= 0.3F) world.setBlockState(pos, state.with(HAS_FRUIT, true));
    }
}
