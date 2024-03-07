package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.vm.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.tardis.mod.registries.VortexMFunctionCategories;
import net.tardis.mod.vm.AbstractVortexMFunction;
import net.tardis.mod.vm.VortexMFunctionCategory;

import java.util.function.Supplier;

public class VortexFunctions {
    public static final DeferredRegister<AbstractVortexMFunction> FUNCTIONS = DeferredRegister.create(AbstractVortexMFunction.class, VortexM.MODID);
    public static Supplier<IForgeRegistry<AbstractVortexMFunction>> FUNCTIONS_REGISTRY;
    public static final RegistryObject<AbstractVortexMFunction> BIO_SCANNER;
    public static final RegistryObject<AbstractVortexMFunction> WAYPOINTS;
    public static final RegistryObject<AbstractVortexMFunction> RANDOMISER;
    public static final RegistryObject<AbstractVortexMFunction> LOCATOR;
    public static final RegistryObject<AbstractVortexMFunction> TEMPORAL_SCAN;

    public VortexFunctions() {
    }

    public static void addFunctionToCategories() {
        ((VortexMFunctionCategory) VortexMFunctionCategories.DIAGNOSTIC.get()).appendFunctionToList((AbstractVortexMFunction)BIO_SCANNER.get());
        ((VortexMFunctionCategory) VortexMFunctionCategories.DIAGNOSTIC.get()).appendFunctionToList((AbstractVortexMFunction)LOCATOR.get());
        ((VortexMFunctionCategory)VortexMFunctionCategories.DIAGNOSTIC.get()).appendFunctionToList((AbstractVortexMFunction)TEMPORAL_SCAN.get());
        ((VortexMFunctionCategory)VortexMFunctionCategories.TELEPORT.get()).appendFunctionToList((AbstractVortexMFunction)WAYPOINTS.get());
        ((VortexMFunctionCategory)VortexMFunctionCategories.TELEPORT.get()).appendFunctionToList((AbstractVortexMFunction)RANDOMISER.get());
    }

    static {
        FUNCTIONS_REGISTRY = VortexFunctions.FUNCTIONS_REGISTRY;
        BIO_SCANNER = FUNCTIONS.register("bioscanner", () -> {
            return new VortexMScanner();
        });
        WAYPOINTS = FUNCTIONS.register("waypoints", () -> {
            return new VortexMWaypoint();
        });
        RANDOMISER = FUNCTIONS.register("randomiser", () -> {
            return new VortexMRandom();
        });
        LOCATOR = FUNCTIONS.register("locator", () -> {
            return new VortexMLocator();
        });
        TEMPORAL_SCAN = FUNCTIONS.register("temporal_scan", () -> {
            return new VortexMTemporalScan();
        });
    }
}
