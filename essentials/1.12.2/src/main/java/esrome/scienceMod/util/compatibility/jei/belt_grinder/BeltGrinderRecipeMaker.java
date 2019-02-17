package esrome.scienceMod.util.compatibility.jei.belt_grinder;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import esrome.scienceMod.recipes.RecipesBeltGrinder;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class BeltGrinderRecipeMaker {

	public static List<BeltGrinderRecipe> getRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		RecipesBeltGrinder instance = RecipesBeltGrinder.getInstance();
		List<List<ItemStack[]>>  recipes = instance.getSmeltingList();
		List<BeltGrinderRecipe> jeiRecipes = Lists.newArrayList();
		
		for(int i = 0; i<recipes.get(0).size();i++) {
			List<ItemStack> list = new ArrayList<ItemStack>();
			for(int j = 0; j<=2; j++) {
				list.add(recipes.get(1).get(i)[j]);
			}
			BeltGrinderRecipe recipe = new BeltGrinderRecipe(recipes.get(0).get(i)[0], list);
			jeiRecipes.add(recipe);
		}
		return jeiRecipes;
	}
	
}
