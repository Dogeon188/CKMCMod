package me.ckffmc.farm.mixin.structure;

import com.google.common.collect.ImmutableList;
import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

@Mixin(StructureProcessorLists.class)
public class StructureProcessorListsMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 11,
                    target = "Lnet/minecraft/structure/processor/RuleStructureProcessor;<init>(Ljava/util/List;)V")
    )
    private static List<?> addFarmProcessorToPlain(List<? extends StructureProcessorRule> rules) {
        return ImmutableList.builder()
                .addAll(rules)
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.RICE.getDefaultState()))
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SWEET_POTATO.getDefaultState()))
                .build();
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 12,
            target = "Lnet/minecraft/structure/processor/RuleStructureProcessor;<init>(Ljava/util/List;)V")
    )
    private static List<?> addFarmProcessorToSavanna(List<? extends StructureProcessorRule> rules) {
        return ImmutableList.builder()
                .addAll(rules)
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SWEET_POTATO.getDefaultState()))
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SOYBEAN.getDefaultState()))
                .build();
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 13,
            target = "Lnet/minecraft/structure/processor/RuleStructureProcessor;<init>(Ljava/util/List;)V")
    )
    private static List<?> addFarmProcessorToSnowy(List<? extends StructureProcessorRule> rules) {
        return ImmutableList.builder()
                .addAll(rules)
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SWEET_POTATO.getDefaultState()))
                .build();
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 14,
            target = "Lnet/minecraft/structure/processor/RuleStructureProcessor;<init>(Ljava/util/List;)V")
    )
    private static List<?> addFarmProcessorToTaiga(List<? extends StructureProcessorRule> rules) {
        return ImmutableList.builder()
                .addAll(rules)
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SWEET_POTATO.getDefaultState()))
                .build();
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 15,
            target = "Lnet/minecraft/structure/processor/RuleStructureProcessor;<init>(Ljava/util/List;)V")
    )
    private static List<?> addFarmProcessorToDesert(List<? extends StructureProcessorRule> rules) {
        return ImmutableList.builder()
                .addAll(rules)
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.SWEET_POTATO.getDefaultState()))
                .add(new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F),
                        AlwaysTrueRuleTest.INSTANCE, MyBlocks.CORN.getDefaultState()))
                .build();
    }
}
