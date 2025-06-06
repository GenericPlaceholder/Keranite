package net.generic404_.keranite.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.generic404_.keranite.Keranite;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleInitializer {
    public static final DefaultParticleType CHARGED_TRAIL = FabricParticleTypes.simple();

    private static void registerParticle(String name, DefaultParticleType particle){
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(Keranite.MOD_ID, name), particle);
    }

    public static void initialize(){
        registerParticle("charged_trail", CHARGED_TRAIL);
    }
}
