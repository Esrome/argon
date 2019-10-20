package esrome.scienceMod.util.compatibility.jei.crystalizer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import esrome.scienceMod.recipes.RecipesCrystalizer;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class CrystalizerRecipeMaker {

	public static List<CrystalizerRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		RecipesCrystalizer instance = RecipesCrystalizer.getInstance();
		List<ItemStack> input1 = instance.firstInputs();
		List<ItemStack> input2 = instance.secondInputs();
		List<ItemStack> outputs = instance.outputs();
		List<CrystalizerRecipe> jeiRecipes = Lists.newArrayList();
		
		for(int i=0; i<input1.size(); i++) {
			ItemStack item1 = input1.get(i);
			ItemStack item2 = input2.get(i);
			ItemStack output = outputs.get(i);
			List<ItemStack> inputs = Lists.newArrayList(item1, item2);
			CrystalizerRecipe recipe = new CrystalizerRecipe(inputs, output);
			jeiRecipes.add(recipe);
		}
		return jeiRecipes;
	}
	
}
