package me.ckffmc.farm.block.crop;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
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

public class TallCropBlock extends CropBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public static final IntProperty AGE = Properties.AGE_7;
    public static final VoxelShape[] SHAPES;

    public TallCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(HALF) == DoubleBlockHalf.UPPER
                ? this.getAge(state) % 4
                : Math.min(3, this.getAge(state))];
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState stateFrom,
                                                 WorldAccess world, BlockPos pos, BlockPos posFrom) {
        DoubleBlockHalf h = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y && h == DoubleBlockHalf.LOWER == (direction == Direction.UP) && state.get(AGE) > this.getMidAge() && (!stateFrom.isOf(this) || stateFrom.get(HALF) == h))
            return Blocks.AIR.getDefaultState();
        if (h == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos))
            return Blocks.AIR.getDefaultState();
        if ((h == DoubleBlockHalf.UPPER && world.getBlockState(pos.down()).isOf(this)) || (h == DoubleBlockHalf.LOWER && world.getBlockState(pos.up()).isOf(this)))
            return state;
        return super.getStateForNeighborUpdate(state, direction, stateFrom, world, pos, posFrom);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER && world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge() && random.nextInt((int) (25.0F / getAvailableMoisture(this, world, pos)) + 1) == 0) {
                i++;
                BlockPos posUp = pos.up();
                BlockState stateUp = world.getBlockState(posUp);
                if (i <= this.getMidAge()) world.setBlockState(pos, this.withAge(i), 2);
                else if (stateUp.isAir() || stateUp.isOf(this)) {
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
            world.setBlockState(pos, this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
            world.setBlockState(pos.down(), this.withAge(i).with(HALF, DoubleBlockHalf.LOWER), 2);
        } else if (i > this.getMidAge() && (stateUp.isAir() || stateUp.isOf(this))) {
            world.setBlockState(pos, this.withAge(i), 2);
            world.setBlockState(pos.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
        } else world.setBlockState(pos, this.withAge(Math.min(3, i)), 2);
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (player.isCreative()) onCreativeBreak(world, pos, state, player);
            else dropStacks(state, world, pos, null, player, player.getMainHandStack());
        }
        super.onBreak(world, pos, state, player);
    }

    protected static void onCreativeBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockPos otherPos = (state.get(HALF) == DoubleBlockHalf.UPPER) ? pos.down() : pos.up();
        BlockState otherState = world.getBlockState(otherPos);
        if (otherState.getBlock() == state.getBlock() && otherState.get(HALF) != state.get(HALF)) {
            world.setBlockState(otherPos, Blocks.AIR.getDefaultState(), 0b0110010);
            world.syncWorldEvent(player, 2001, otherPos, Block.getRawIdFromState(otherState));
        }
    }

    public int getMidAge() { return 3; }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF); builder.add(AGE);
    }

    static {
        SHAPES = new VoxelShape[]{
                Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D),
                Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
                Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
                Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)};
    }
}
