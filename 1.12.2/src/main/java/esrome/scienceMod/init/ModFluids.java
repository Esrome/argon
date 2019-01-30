package esrome.scienceMod.init;

import esrome.scienceMod.fluids.FluidLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

	//public static final Fluid MERCURY = new FluidLiquid("mercury", new ResourceLocation("escm:blocks/mercury_still"), new ResourceLocation("escm:blocks/mercury_flow"));
	public static final Fluid HOT_WATER = new FluidLiquid("hot_water", new ResourceLocation("escm:blocks/hot_water_still"), new ResourceLocation("escm:blocks/hot_water_flow"), true);
	//public static final Fluid BOILING_WATER = new FluidLiquid("");
	
	public static void registerFluids() {
		//registerFluid(MERCURY);
		registerFluid(HOT_WATER);
	}
	
	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
	
}
