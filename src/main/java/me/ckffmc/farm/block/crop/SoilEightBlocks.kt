package me.ckffmc.farm.block.crop

import me.ckffmc.farm.item.MyItems
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.item.ItemConvertible

class RiceBlock(settings: Settings?) : SoilEightBlock(settings) {
    @Environment(EnvType.CLIENT)
    override fun getSeedsItem(): ItemConvertible { return MyItems.RICE }
}

class LettuceBlock(settings: Settings?) : SoilEightBlock(settings) {
    @Environment(EnvType.CLIENT)
    override fun getSeedsItem(): ItemConvertible {
        return MyItems.LETTUCE_SEEDS
    }
}

/*
// just to be sure.

public class RiceBlock extends SoilEightBlock {
    public RiceBlock(Settings settings) { super(settings); }
    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() { return MyItems.RICE; }
}

public class LettuceBlock extends SoilEightBlock {
    public LettuceBlock(Settings settings) { super(settings); }
    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() { return MyItems.LETTUCE_SEEDS; }
}
*/