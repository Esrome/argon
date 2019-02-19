package esrome.scienceMod.util.compatibility.jei.crystalizer;

import esrome.scienceMod.util.Reference;
import esrome.scienceMod.util.compatibility.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

public class CrystalizerRecipeCategory extends AbstractCrystalizerRecipeCategory<CrystalizerRecipe>{
	
	private final IDrawable background;
	private final String name;
	
	public CrystalizerRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 34, 12, 78, 69);
		name = "Crystalizer";
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public IDrawable getIcon() {
		return null;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedFlame.draw(minecraft, 32, 12);
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public String getModName() {
		return Reference.NAME;
	}
	
	@Override
	public String getUid() {
		return RecipeCategories.CRYSTALIZER;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 8, 8);
		stacks.init(input2, true, 52, 8);
		stacks.init(output, false, 30, 43);
		stacks.set(ingredients);
	}
	
}
