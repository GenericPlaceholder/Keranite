package net.generic404_.keranite.border;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;

import static net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.*;

public class ParticleWallTask {
    private final ServerWorld world;
    private final List<BlockPos> positions;
    private int ticksRemaining = 100;

    public ParticleWallTask(ServerWorld world, List<BlockPos> positions) {
        this.world = world;
        this.positions = positions;
    }

    public void start() {
        END_SERVER_TICK.register(this::tick);
    }

    private void tick(MinecraftServer server) {
        if (ticksRemaining-- <= 0) {
//            END_SERVER_TICK.unregister(this::tick);
            return;
        }

        for (BlockPos pos : positions) {
            world.spawnParticles(ParticleTypes.END_ROD,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    2, 0.3, 0.5, 0.3, 0.01);
        }
    }
}
