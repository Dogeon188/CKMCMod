package me.ckffmc.farm.block.crop

import me.ckffmc.farm.item.MyItems
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.ItemConvertible

class SweetPotatoBlock(settings: Settings?) : SoilFourBlock(settings) {
    @Environment(EnvType.CLIENT)
    override fun getSeedsItem(): ItemConvertible { return MyItems.SWEET_POTATO }
}

/*
// just to be sure.

public class SweetPotatoBlock extends SoilFourBlock {
    public SweetPotatoBlock(Settings settings) { super(settings); }

    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() { return MyItems.SWEET_POTATO; }
}
*/