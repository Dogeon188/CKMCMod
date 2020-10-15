package me.ckffmc.farm;

import me.ckffmc.farm.screen.MillstoneScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class MyScreenHandlerType {
    public static final ScreenHandlerType<MillstoneScreenHandler> MILL_SCREEN_HANDLER;

    static {
        MILL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MainMod.MOD_ID, "mill"),
                MillstoneScreenHandler::new);
    }
}
