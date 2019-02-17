package esrome.scienceMod.util.compatibility.jei;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.item.crafting.IRecipe;

public class JeiAccessor {

	  static final @Nonnull List<IRecipe> ALTERNATIVES = new ArrayList<IRecipe>();

	  public static void addAlternativeRecipe(@Nonnull IRecipe recipe) {
	    ALTERNATIVES.add(recipe);
	  }
	
}
