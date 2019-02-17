package esrome.scienceMod.util.compatibility.jei.belt_grinder;

import esrome.scienceMod.util.Reference;
import esrome.scienceMod.util.compatibility.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

public class BeltGrinderRecipeCategory extends AbstractBeltGrinderRecipeCategory<BeltGrinderRecipe>{
	
	private final IDrawable background;
	private final String name;
	
	public BeltGrinderRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 34, 12, 88, 63);
		name = "Belt Grinder";
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
		animatedProgress.draw(minecraft, 26, 22);
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
		return RecipeCategories.BELT_GRINDER;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, 6, 21);
		stacks.init(output1, false, 63, 3);
		stacks.init(output2, false, 63, 22);
		stacks.init(output3, false, 63, 41);
		stacks.set(ingredients);
	}
	
}
