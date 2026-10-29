package io.github.fand07.festivalfx.client.beam;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

/**
 * Client-side list of spike test beams. Only touched from the client thread.
 */
public final class TestBeamStore {
	public static final int MAX_BEAMS = 64;

	private static final List<TestBeam> BEAMS = new ArrayList<>();

	private TestBeamStore() {
	}

	/** Drops all beams when leaving a world or server, so they never leak into the next one. */
	public static void register() {
		ClientPlayConnectionEvents.DISCONNECT.register((listener, client) -> clear());
	}

	/** @return {@code false} if the limit of {@value #MAX_BEAMS} beams is reached */
	public static boolean add(TestBeam beam) {
		if (BEAMS.size() >= MAX_BEAMS) {
			return false;
		}

		BEAMS.add(beam);
		return true;
	}

	public static void clear() {
		BEAMS.clear();
	}

	public static int size() {
		return BEAMS.size();
	}

	/** Immutable copy handed to the render state during extraction. */
	public static List<TestBeam> snapshot() {
		return List.copyOf(BEAMS);
	}
}
