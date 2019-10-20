package esrome.scienceMod.util.compatibility.jei;

import java.util.IllegalFormatException;

import esrome.scienceMod.blocks.containers.ContainerBeltGrinder;
import esrome.scienceMod.blocks.containers.ContainerCrystalizer;
import esrome.scienceMod.gui.GuiBeltGrinder;
import esrome.scienceMod.gui.GuiCrystalizer;
import esrome.scienceMod.util.compatibility.jei.belt_grinder.BeltGrinderRecipeCategory;
import esrome.scienceMod.util.compatibility.jei.belt_grinder.BeltGrinderRecipeMaker;
import esrome.scienceMod.util.compatibility.jei.crystalizer.CrystalizerRecipeCategory;
import esrome.scienceMod.util.compatibility.jei.crystalizer.CrystalizerRecipeMaker;
import esrome.scienceMod.util.compatibility.jei.fabricator.FabricatorRecipeCategory;
import esrome.scienceMod.util.compatibility.jei.fabricator.FabricatorRecipeMaker;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin {

	 @Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new CrystalizerRecipeCategory(gui));
		registry.addRecipeCategories(new BeltGrinderRecipeCategory(gui));
	    ((IModRegistry) registry).addRecipes(JeiAccessor.ALTERNATIVES, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipeCategories(new FabricatorRecipeCategory(gui));
	}
	 
	 @Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(CrystalizerRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.CRYSTALIZER);
		registry.addRecipes(BeltGrinderRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.BELT_GRINDER);
		registry.addRecipes(FabricatorRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.FABRICATOR);
		
		registry.addRecipeClickArea(GuiCrystalizer.class, 64, 37, 18, 18, RecipeCategories.CRYSTALIZER);
		registry.addRecipeClickArea(GuiBeltGrinder.class, 116, 3, 31, 80, RecipeCategories.BELT_GRINDER);
		
		recipeTransfer.addRecipeTransferHandler(ContainerCrystalizer.class, RecipeCategories.CRYSTALIZER, 0, 1, 3, 36);
		recipeTransfer.addRecipeTransferHandler(ContainerBeltGrinder.class, RecipeCategories.BELT_GRINDER, 0, 0, 4, 36);
	}
	
	public static String translateToLocal(String key) {
		if(I18n.canTranslate(key))return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		}catch(IllegalFormatException e) {
			return "Format Error: " + s;
		}
	}
	 
}
