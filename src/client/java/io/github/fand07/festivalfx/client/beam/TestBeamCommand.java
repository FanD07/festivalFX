package io.github.fand07.festivalfx.client.beam;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

/**
 * Spike-only client command: {@code /ffxbeam add [#RRGGBB]}, {@code /ffxbeam scene}, {@code /ffxbeam clear}.
 */
public final class TestBeamCommand {
	private static final int DEFAULT_RGB = 0x00FFFF;

	private static final int SCENE_BEAMS = 8;
	private static final double SCENE_SPACING_BLOCKS = 2.0;
	private static final double SCENE_FIRST_TILT_DEGREES = -28.0;
	private static final double SCENE_TILT_STEP_DEGREES = 8.0;
	private static final int[] SCENE_COLORS = {0xFF0000, 0xFF8000, 0xFFFF00, 0x00FF00, 0x00FFFF, 0x0040FF, 0xFF00FF, 0xFFFFFF};

	private TestBeamCommand() {
	}

	public static void register() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext) -> registerCommands(dispatcher));
	}

	private static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(ClientCommands.literal("ffxbeam")
				.then(ClientCommands.literal("add")
						.executes(context -> add(context.getSource(), DEFAULT_RGB))
						.then(ClientCommands.argument("color", StringArgumentType.greedyString())
								.executes(TestBeamCommand::addWithColor)))
				.then(ClientCommands.literal("scene").executes(context -> scene(context.getSource())))
				.then(ClientCommands.literal("clear").executes(context -> clear(context.getSource()))));
	}

	private static int addWithColor(CommandContext<FabricClientCommandSource> context) {
		String input = StringArgumentType.getString(context, "color").trim();
		String hex = input.startsWith("#") ? input.substring(1) : input;

		if (!hex.matches("[0-9A-Fa-f]{6}")) {
			context.getSource().sendError(Component.literal("Color must be #RRGGBB, got: " + input));
			return 0;
		}

		return add(context.getSource(), Integer.parseInt(hex, 16));
	}

	private static int add(FabricClientCommandSource source, int rgb) {
		Vec3 origin = source.getPlayer().getEyePosition();
		Vec3 direction = source.getPlayer().getLookAngle();

		if (!TestBeamStore.add(TestBeam.of(origin, direction, rgb))) {
			source.sendError(Component.literal("Beam limit reached (" + TestBeamStore.MAX_BEAMS + ")"));
			return 0;
		}

		source.sendFeedback(Component.literal("Test beam added (" + TestBeamStore.size() + "/" + TestBeamStore.MAX_BEAMS + ")"));
		return 1;
	}

	private static int scene(FabricClientCommandSource source) {
		Vec3 base = source.getPlayer().position();
		int added = 0;

		for (int i = 0; i < SCENE_BEAMS; i++) {
			double tilt = Math.toRadians(SCENE_FIRST_TILT_DEGREES + i * SCENE_TILT_STEP_DEGREES);
			Vec3 origin = base.add(i * SCENE_SPACING_BLOCKS, 0, 4);
			Vec3 direction = new Vec3(0, Math.cos(tilt), Math.sin(tilt));

			if (!TestBeamStore.add(TestBeam.of(origin, direction, SCENE_COLORS[i]))) {
				break;
			}

			added++;
		}

		source.sendFeedback(Component.literal("Test scene: " + added + " beams added (" + TestBeamStore.size() + "/" + TestBeamStore.MAX_BEAMS + ")"));
		return added;
	}

	private static int clear(FabricClientCommandSource source) {
		TestBeamStore.clear();
		source.sendFeedback(Component.literal("All test beams removed"));
		return 1;
	}
}
