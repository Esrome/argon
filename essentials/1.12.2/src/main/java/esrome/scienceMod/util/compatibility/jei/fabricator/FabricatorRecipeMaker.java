package esrome.scienceMod.util.compatibility.jei.fabricator;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import esrome.scienceMod.recipes.RecipesFabricator;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class FabricatorRecipeMaker {

	public static List<FabricatorRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		RecipesFabricator instance = RecipesFabricator.getInstance();
		List<List<ItemStack>>  recipes = instance.getSmeltingList();
		List<FabricatorRecipe> jeiRecipes = Lists.newArrayList();
		
		for(int i = 0; i<recipes.get(0).size();i++) {
			FabricatorRecipe recipe = new FabricatorRecipe(recipes.get(0).get(i), recipes.get(1).get(i), recipes.get(2).get(i), recipes.get(3).get(i), recipes.get(4).get(i));
			jeiRecipes.add(recipe);
		}
		return jeiRecipes;
	}
	
}
