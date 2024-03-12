package com.SpectrumFATM.vortexmanipulators.registry;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VortexM.MODID);

    public static final RegistryObject<SoundEvent> TIME_FISSURE =
            registerSoundEvent("time_fissure");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(VortexM.MODID, name)));
    }

}
