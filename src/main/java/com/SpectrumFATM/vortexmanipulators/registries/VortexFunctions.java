package com.SpectrumFATM.vortexmanipulators.registries;

import java.util.function.Supplier;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.vm.VortexMScanner;
import com.SpectrumFATM.vortexmanipulators.vm.VortexMWaypoint;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.tardis.mod.registries.VortexMFunctionCategories;
import net.tardis.mod.vm.AbstractVortexMFunction;
import net.tardis.mod.vm.DistressFunction;
import net.tardis.mod.vm.TeleportFunction;
import net.tardis.mod.vm.VortexMFunctionCategory;

public class VortexFunctions {
    public static final DeferredRegister<AbstractVortexMFunction> FUNCTIONS = DeferredRegister.create(AbstractVortexMFunction.class, VortexM.MODID);
    public static Supplier<IForgeRegistry<AbstractVortexMFunction>> FUNCTIONS_REGISTRY;
    public static final RegistryObject<AbstractVortexMFunction> BIO_SCANNER;
    public static final RegistryObject<AbstractVortexMFunction> WAYPOINTS;

    public VortexFunctions() {
    }

    public static void addFunctionToCategories() {
        ((VortexMFunctionCategory) VortexMFunctionCategories.DIAGNOSTIC.get()).appendFunctionToList((AbstractVortexMFunction)BIO_SCANNER.get());
        ((VortexMFunctionCategory)VortexMFunctionCategories.TELEPORT.get()).appendFunctionToList((AbstractVortexMFunction)WAYPOINTS.get());
    }

    static {
        FUNCTIONS_REGISTRY = VortexFunctions.FUNCTIONS_REGISTRY;
        BIO_SCANNER = FUNCTIONS.register("bioscanner", () -> {
            return new VortexMScanner();
        });
        WAYPOINTS = FUNCTIONS.register("waypoints", () -> {
            return new VortexMWaypoint();
        });

    }
}
