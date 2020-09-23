package me.ckffmc.farm.block.crop;

import me.ckffmc.farm.item.MyItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemConvertible;

public class RiceBlock extends SoilCropEightBlock {

    public RiceBlock(Settings settings) { super(settings); }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() { return MyItems.RICE; }
}
