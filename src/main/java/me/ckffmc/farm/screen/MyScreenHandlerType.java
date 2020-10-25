package me.ckffmc.farm.screen;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.SimpleClientHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class MyScreenHandlerType {
    public static final ScreenHandlerType<MillstoneScreenHandler> MILLSTONE_SCREEN_HANDLER =
            register("millstone", MillstoneScreenHandler::new);
    public static final ScreenHandlerType<CookingTableScreenHandler> COOKING_TABLE_SCREEN_HANDLER =
            register("cooking_table", CookingTableScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, SimpleClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(MainMod.MOD_ID, id), factory);
    }
}
