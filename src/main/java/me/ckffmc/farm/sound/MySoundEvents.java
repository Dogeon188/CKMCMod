package me.ckffmc.farm.sound;

import me.ckffmc.farm.MainMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MySoundEvents {
    public static final SoundEvent MILLSTONE_MILLING = register("block.millstone.milling");

    private static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(MainMod.MOD_ID, id),
                new SoundEvent(new Identifier(MainMod.MOD_ID, id)));
    }
}
