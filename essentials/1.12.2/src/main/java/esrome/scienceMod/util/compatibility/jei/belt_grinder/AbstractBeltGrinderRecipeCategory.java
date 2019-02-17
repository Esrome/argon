package esrome.scienceMod.util.compatibility.jei.belt_grinder;

import esrome.scienceMod.util.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractBeltGrinderRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/belt_grinder.png");
	
	protected static final int input = 0;
	protected static final int output1 = 1;
	protected static final int output2 = 2;
	protected static final int output3 = 3;
	
	protected final IDrawableStatic staticProgress;
	protected final IDrawableAnimated animatedProgress;
	
	public AbstractBeltGrinderRecipeCategory(IGuiHelper helper) {
		staticProgress = helper.createDrawable(TEXTURES, 176, 0, 35, 16);
		animatedProgress = helper.createAnimatedDrawable(staticProgress, 300, IDrawableAnimated.StartDirection.LEFT, false);
	}
	
}
