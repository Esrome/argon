package esrome.scienceMod.util.compatibility.jei.crystalizer;

import esrome.scienceMod.util.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractCrystalizerRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/crystalizer.png");
	
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int output = 2;
	
	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;
	
	public AbstractCrystalizerRecipeCategory(IGuiHelper helper) {
		staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.BOTTOM, false);
	}
	
}
