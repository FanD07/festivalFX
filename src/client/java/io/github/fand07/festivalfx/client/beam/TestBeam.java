package io.github.fand07.festivalfx.client.beam;

import net.minecraft.world.phys.Vec3;

/**
 * A client-only test beam for the FFX-005 renderer spike. Not persisted, not synced.
 *
 * @param origin          world position of the beam start, in blocks
 * @param direction       normalized beam direction
 * @param rgb             beam color as 0xRRGGBB
 * @param lengthBlocks    visible beam length, in blocks
 * @param apertureDegrees full opening angle of the outer cone, in degrees
 */
public record TestBeam(Vec3 origin, Vec3 direction, int rgb, float lengthBlocks, float apertureDegrees) {
	// Provisional spike values until fixture profiles exist (FFX-010 ff.).
	public static final float DEFAULT_LENGTH_BLOCKS = 40.0F;
	public static final float DEFAULT_APERTURE_DEGREES = 4.0F;

	public static TestBeam of(Vec3 origin, Vec3 direction, int rgb) {
		return new TestBeam(origin, direction.normalize(), rgb, DEFAULT_LENGTH_BLOCKS, DEFAULT_APERTURE_DEGREES);
	}
}
