package esrome.scienceMod.util.compatibility.jei.fabricator;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class FabricatorRecipe implements IRecipeWrapper {

	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack input3;
	private final ItemStack blueprint;
	private final ItemStack output;
	
	public FabricatorRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack blueprint, ItemStack output) {
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.blueprint = blueprint;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(input1);
		list.add(input2);
		list.add(input3);
		list.add(blueprint);
		ingredients.setInputs(ItemStack.class, list);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		
	}
	
}
