package esrome.scienceMod.util.compatibility.jei.fabricator;

import esrome.scienceMod.util.Reference;
import esrome.scienceMod.util.compatibility.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

public class FabricatorRecipeCategory extends AbstractFabricatorRecipeCategory<FabricatorRecipe>{
	
	private final IDrawable background;
	private final String name;
	
	public FabricatorRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 14, 12, 108, 63);
		name = "Fabricator";
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
		animatedProgress.draw(minecraft, 53, 23);
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
		return RecipeCategories.FABRICATOR;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 26, 2);
		stacks.init(input2, true, 26, 21);
		stacks.init(input3, true, 26, 40);
		stacks.init(blueprint, true, 3, 21);
		stacks.init(output, false, 83, 21);
		stacks.set(ingredients);
	}
	
}
