package me.ckffmc.farm.block.crop;

import me.ckffmc.farm.item.MyItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemConvertible;

public class SweetPotatoBlock extends SoilCropFourBlock {
    public SweetPotatoBlock(Settings settings) { super(settings); }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() { return MyItems.SWEET_POTATO; }
}
