package io.github.fand07.festivalfx;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FestivalFX implements ModInitializer {
	public static final String MOD_ID = "festivalfx";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("FestivalFX common initialization");
	}
}
