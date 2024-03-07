package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.entities.TimeFissureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MobRegistry {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, VortexM.MODID);

    //Entity Types
    //EntityType.Builder.create is now EntityType.Builder.of
    public static final RegistryObject<EntityType<TimeFissureEntity>> FISSURE = ENTITY_TYPES.register("time_fissure",
            () -> EntityType.Builder.of(TimeFissureEntity::new, EntityClassification.MISC)
                    .sized(1.0F, 1.0F)
                    .build(new ResourceLocation(VortexM.MODID, "time_fissure").toString()));

    public static void init() {
        MobRegistry.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
