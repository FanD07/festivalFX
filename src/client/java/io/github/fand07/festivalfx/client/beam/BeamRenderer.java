package io.github.fand07.festivalfx.client.beam;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import io.github.fand07.festivalfx.FestivalFX;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.world.phys.Vec3;

/**
 * Spike renderer: draws each test beam as three nested, additive cones (look variant A).
 * Beam data is snapshotted during extraction and only read from the render state afterwards.
 */
public final class BeamRenderer {
	private static final RenderStateDataKey<List<TestBeam>> BEAMS_KEY = RenderStateDataKey.create(() -> "festivalfx:test_beams");

	private static final int SEGMENTS = 16;
	private static final float BASE_RADIUS_BLOCKS = 0.05F;

	// Per layer: share of the aperture, extra base radius in blocks, start alpha.
	private static final float[] LAYER_APERTURE_SHARE = {0.35F, 0.7F, 1.0F};
	private static final float[] LAYER_BASE_RADIUS = {0.0F, 0.04F, 0.09F};
	private static final float[] LAYER_ALPHA = {0.9F, 0.35F, 0.12F};

	private static boolean disabled;

	private BeamRenderer() {
	}

	public static void register() {
		LevelExtractionEvents.END_EXTRACTION.register(BeamRenderer::extract);
		LevelRenderEvents.COLLECT_SUBMITS.register(BeamRenderer::submit);
	}

	private static void extract(LevelExtractionContext context) {
		context.levelState().setData(BEAMS_KEY, TestBeamStore.snapshot());
	}

	private static void submit(LevelRenderContext context) {
		if (disabled) {
			return;
		}

		List<TestBeam> beams = context.levelState().getData(BEAMS_KEY);

		if (beams == null || beams.isEmpty()) {
			return;
		}

		Vec3 camera = context.levelState().cameraRenderState.pos;
		PoseStack poseStack = context.poseStack();

		try {
			context.submitNodeCollector().submitCustomGeometry(poseStack, RenderTypes.lightning(), (pose, consumer) -> {
				for (TestBeam beam : beams) {
					emitBeam(pose, consumer, beam, camera);
				}
			});
		} catch (RuntimeException e) {
			disabled = true;
			FestivalFX.LOGGER.warn("FestivalFX test beams disabled after a render error", e);
		}
	}

	private static void emitBeam(PoseStack.Pose pose, VertexConsumer consumer, TestBeam beam, Vec3 camera) {
		Vec3 direction = beam.direction();
		Vec3 helper = Math.abs(direction.y) < 0.99 ? new Vec3(0, 1, 0) : new Vec3(1, 0, 0);
		Vec3 u = direction.cross(helper).normalize();
		Vec3 v = direction.cross(u).normalize();

		Vec3 start = beam.origin().subtract(camera);
		Vec3 end = start.add(direction.scale(beam.lengthBlocks()));
		double halfApertureRadians = Math.toRadians(beam.apertureDegrees() / 2.0);

		int red = (beam.rgb() >> 16) & 0xFF;
		int green = (beam.rgb() >> 8) & 0xFF;
		int blue = beam.rgb() & 0xFF;

		for (int layer = 0; layer < LAYER_ALPHA.length; layer++) {
			double startRadius = BASE_RADIUS_BLOCKS + LAYER_BASE_RADIUS[layer];
			double endRadius = startRadius + beam.lengthBlocks() * Math.tan(halfApertureRadians * LAYER_APERTURE_SHARE[layer]);
			int startAlpha = Math.round(LAYER_ALPHA[layer] * 255);

			for (int segment = 0; segment < SEGMENTS; segment++) {
				double angleA = 2 * Math.PI * segment / SEGMENTS;
				double angleB = 2 * Math.PI * (segment + 1) / SEGMENTS;

				vertex(pose, consumer, ring(start, u, v, angleA, startRadius), red, green, blue, startAlpha);
				vertex(pose, consumer, ring(start, u, v, angleB, startRadius), red, green, blue, startAlpha);
				vertex(pose, consumer, ring(end, u, v, angleB, endRadius), red, green, blue, 0);
				vertex(pose, consumer, ring(end, u, v, angleA, endRadius), red, green, blue, 0);
			}
		}
	}

	private static Vec3 ring(Vec3 center, Vec3 u, Vec3 v, double angle, double radius) {
		return center.add(u.scale(Math.cos(angle) * radius)).add(v.scale(Math.sin(angle) * radius));
	}

	private static void vertex(PoseStack.Pose pose, VertexConsumer consumer, Vec3 position, int red, int green, int blue, int alpha) {
		consumer.addVertex(pose, (float) position.x, (float) position.y, (float) position.z).setColor(red, green, blue, alpha);
	}
}
