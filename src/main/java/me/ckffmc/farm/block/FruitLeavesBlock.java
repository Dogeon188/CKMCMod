package me.ckffmc.farm.block;

import me.ckffmc.farm.item.MyItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FruitLeavesBlock extends LeavesBlock {
    public static final BooleanProperty HAS_FRUIT = BooleanProperty.of("has_fruit");

    public FruitLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(HAS_FRUIT, false).with(DISTANCE, 7)
                .with(PERSISTENT, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_FRUIT);
    }

    public boolean hasRandomTicks(BlockState state) {
        return super.hasRandomTicks(state) || !state.get(HAS_FRUIT);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!state.get(PERSISTENT) && !state.get(HAS_FRUIT) && random.nextFloat() <= 0.005F)
            world.setBlockState(pos, state.with(HAS_FRUIT, true));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(HAS_FRUIT)) {
            dropStack(world, pos, new ItemStack(MyItems.MANGO, 1));
            world.setBlockState(pos, state.with(HAS_FRUIT, false));
            return ActionResult.success(world.isClient);
        }
        else return super.onUse(state, world, pos, player, hand, hit);
    }
}
