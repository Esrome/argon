package esrome.scienceMod.util.compatibility.jei.fabricator;

import esrome.scienceMod.util.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractFabricatorRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/fabricator.png");
	
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int input3 = 2;
	protected static final int blueprint = 3;
	protected static final int output = 4;
	
	protected final IDrawableStatic staticProgress;
	protected final IDrawableAnimated animatedProgress;
	
	public AbstractFabricatorRecipeCategory(IGuiHelper helper) {
		staticProgress = helper.createDrawable(TEXTURES, 176, 0, 35, 16);
		animatedProgress = helper.createAnimatedDrawable(staticProgress, 300, IDrawableAnimated.StartDirection.LEFT, false);
	}
	
}
