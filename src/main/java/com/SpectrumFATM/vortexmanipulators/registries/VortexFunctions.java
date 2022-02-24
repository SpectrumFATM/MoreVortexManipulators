package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.vm.VortexMScanner;
import com.SpectrumFATM.vortexmanipulators.vm.VortexMWaypoint;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.registries.VortexMFunctionCategories;
import net.tardis.mod.vm.AbstractVortexMFunction;
import net.tardis.mod.vm.VortexMFunctionCategory;

public class VortexFunctions {
    public static final DeferredRegister<AbstractVortexMFunction> FUNCTIONS = DeferredRegister.create(AbstractVortexMFunction.class, VortexM.MODID);

    public static final RegistryObject<AbstractVortexMFunction> SCANNER = FUNCTIONS.register("bioscanner", VortexMScanner::new);
    public static final RegistryObject<AbstractVortexMFunction> WAYPOINT = FUNCTIONS.register("waypoints", VortexMWaypoint::new);

    public static void addToCatagories() {
        ((VortexMFunctionCategory)VortexMFunctionCategories.DIAGNOSTIC.get()).appendFunctionToList((AbstractVortexMFunction)SCANNER.get());
        ((VortexMFunctionCategory)VortexMFunctionCategories.TELEPORT.get()).appendFunctionToList((AbstractVortexMFunction)WAYPOINT.get());
    }
}
