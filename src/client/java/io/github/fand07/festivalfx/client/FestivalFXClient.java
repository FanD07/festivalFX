package io.github.fand07.festivalfx.client;

import io.github.fand07.festivalfx.FestivalFX;

import net.fabricmc.api.ClientModInitializer;

public final class FestivalFXClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FestivalFX.LOGGER.info("FestivalFX client initialization");
	}
}
