package me.ckffmc.farm;

import net.fabricmc.api.ModInitializer;

public class MainMod implements ModInitializer {

	public static final String MOD_ID = "ckfarm";
	private static final String[] toLoadClasses = {
			"me.ckffmc.farm.item.MyItems"
	};

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		try {
			for (String c : toLoadClasses) Class.forName(c).getDeclaredConstructor().newInstance();
		} catch (Exception e) { e.printStackTrace(); }
		System.out.println("CKMCFF farming mod is ready!");
	}
}
