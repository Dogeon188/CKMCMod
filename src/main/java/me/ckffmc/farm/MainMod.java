package me.ckffmc.farm;

import net.fabricmc.api.ModInitializer;

public class MainMod implements ModInitializer {

    public static final String MOD_ID = "ckfarm";
    private static final String[] toLoadClasses = {
            "me.ckffmc.farm.item.MyItems"
    };

    @Override
    public void onInitialize() {
        try {
            for (String c : toLoadClasses) Class.forName(c).getDeclaredConstructor().newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        System.out.println("CKFFMC farming mod is ready!");
    }
}
