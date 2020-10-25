package me.ckffmc.farm.block.crop;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CropHelper {
    public static Age8CropBlock newAge8(String seeds_item, FabricBlockSettings settings) {
        return new Age8CropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }

    public static Age4CropBlock newAge4(String seeds_item, FabricBlockSettings settings) {
        return new Age4CropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }

    public static TallCropBlock newTall(String seeds_item, FabricBlockSettings settings) {
        return new TallCropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }
}
