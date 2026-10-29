package io.github.fand07.festivalfx.client;

import io.github.fand07.festivalfx.FestivalFX;
import io.github.fand07.festivalfx.client.beam.BeamRenderer;
import io.github.fand07.festivalfx.client.beam.TestBeamCommand;
import io.github.fand07.festivalfx.client.beam.TestBeamStore;

import net.fabricmc.api.ClientModInitializer;

public final class FestivalFXClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FestivalFX.LOGGER.info("FestivalFX client initialization");

		TestBeamStore.register();
		BeamRenderer.register();
		TestBeamCommand.register();
	}
}
