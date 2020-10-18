package me.ckffmc.farm.screen;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class MyScreenHandlerType {
    public static final ScreenHandlerType<MillstoneScreenHandler> MILLSTONE_SCREEN_HANDLER;

    static {
        MILLSTONE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MainMod.MOD_ID, "millstone"), MillstoneScreenHandler::new);
    }
}
