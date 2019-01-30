package esrome.scienceMod.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidLiquid extends Fluid {

	private boolean vaporizes = false;
	
	public FluidLiquid(String name, ResourceLocation still, ResourceLocation flowing) {
		super(name, still, flowing);
		setUnlocalizedName(name);
	}
	
	public FluidLiquid(String name, ResourceLocation still, ResourceLocation flowing, boolean vaporizes) {
		super(name, still, flowing);
		setUnlocalizedName(name);
		this.vaporizes = vaporizes;
	}
	
	@Override
	public boolean doesVaporize(FluidStack fluidStack) {
		return vaporizes;
	}
	
}
