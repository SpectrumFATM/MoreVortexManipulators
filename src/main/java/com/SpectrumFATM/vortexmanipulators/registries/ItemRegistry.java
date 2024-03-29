package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.items.RiftManipulator;
import com.SpectrumFATM.vortexmanipulators.items.TemporalEraser;
import com.SpectrumFATM.vortexmanipulators.items.TemporalShift;
import com.SpectrumFATM.vortexmanipulators.items.TimeConverter;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.itemgroups.TItemGroups;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VortexM.MODID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> TEMPORAL_ERASER = ITEMS.register("temporal_eraser", () -> new TemporalEraser(BlockRegistry.TIME_DESTRUCTOR.get(), new Item.Properties().stacksTo(1).tab(TItemGroups.MAIN)));
    public static final RegistryObject<Item> RIFT_MANIPULATOR = ITEMS.register("rift_manipulator", () -> new RiftManipulator(BlockRegistry.RIFT_MANIPULATOR.get(), new Item.Properties().stacksTo(1).tab(TItemGroups.MAIN)));
    public static final RegistryObject<Item> TEMPORAL_SHIFT = ITEMS.register("temporal_shift", () -> new TemporalShift(new Item.Properties().stacksTo(1).tab(TItemGroups.MAIN)));
    public static final RegistryObject<Item> TIME_CONVERTER = ITEMS.register("time_converter", () -> new TimeConverter(new Item.Properties().stacksTo(1).tab(TItemGroups.MAIN).durability(10)));
}
