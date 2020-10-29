package me.ckffmc.farm.block.crop;

import me.ckffmc.farm.item.MyItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class TeaSaplingBlock extends CropBlock {
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape[] AGE_TO_SHAPE;

    public TeaSaplingBlock(Settings settings) { super(settings); }

    protected ItemConvertible getSeedsItem() { return MyItems.TEA_SAPLING; }

    public IntProperty getAgeProperty() { return AGE; }

    public int getMaxAge() { return 3; }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) != 0) super.randomTick(state, world, pos, random);
    }

    protected int getGrowthAmount(World world) { return super.getGrowthAmount(world) / 3; }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(AGE); }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }

    static {
        AGE_TO_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D),
                Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 7.0D, 10.0D),
                Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D),
                Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 11.0D)};
    }
}
