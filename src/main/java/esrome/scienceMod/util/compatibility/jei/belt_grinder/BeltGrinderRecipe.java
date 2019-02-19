package esrome.scienceMod.util.compatibility.jei.belt_grinder;

import java.util.List;

import esrome.scienceMod.recipes.RecipesCrystalizer;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class BeltGrinderRecipe implements IRecipeWrapper {

	private final ItemStack inputs;
	private final List<ItemStack> output;
	
	public BeltGrinderRecipe(ItemStack input, List<ItemStack> output) {
		this.inputs = input;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		
	}
	
}
