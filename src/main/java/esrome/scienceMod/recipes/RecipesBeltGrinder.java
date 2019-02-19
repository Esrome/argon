package esrome.scienceMod.recipes;

import java.util.ArrayList;
import java.util.List;

import esrome.scienceMod.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesBeltGrinder {

	private static final RecipesBeltGrinder INSTANCE = new RecipesBeltGrinder();
	private final List<ItemStack> inputList = new ArrayList<ItemStack>();
	private final List<ItemStack[]> outputList = new ArrayList<ItemStack[]>();
	
	public static RecipesBeltGrinder getInstance(){
		return INSTANCE;
	}
	
	private RecipesBeltGrinder() {
		addGrinderRecipe(new ItemStack(Items.QUARTZ), new ItemStack[] {new ItemStack(ModItems.SILICA), new ItemStack(ModItems.SILICA), ItemStack.EMPTY});
		addGrinderRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack[] {new ItemStack(ModItems.IRON_DUST), new ItemStack(ModItems.IRON_DUST), new ItemStack(ModItems.IRON_DUST)});
		addGrinderRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack[] {new ItemStack(ModItems.GOLD_DUST), new ItemStack(ModItems.GOLD_DUST), new ItemStack(ModItems.GOLD_DUST)});
		addGrinderRecipe(new ItemStack(ModItems.COPPER_INGOT), new ItemStack[] {new ItemStack(ModItems.COPPER_DUST), new ItemStack(ModItems.COPPER_DUST), new ItemStack(ModItems.COPPER_DUST)});
		addGrinderRecipe(new ItemStack(ModItems.LEAD_INGOT), new ItemStack[] {new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.LEAD_DUST)});
	}

	
	public void addGrinderRecipe(ItemStack input, ItemStack[] results) 
	{
		if(getGrinderRecipe(input) != null) return;
		inputList.add(input);
		outputList.add(results);
	}
	
	public ItemStack[] getGrinderRecipe(ItemStack input) 
	{
		for(int i=0;i<inputList.size();i++) 
		{
			int[] ids = OreDictionary.getOreIDs(inputList.get(i));
			for(int id: ids) {
				String name = OreDictionary.getOreName(id);
				for(ItemStack stack: OreDictionary.getOres(name)) {
					if(ItemStack.areItemsEqual(input, stack)) return outputList.get(i);
				}
			}
		}
		return null;
	}
	
	public List<List<ItemStack[]>> getSmeltingList() {
		List<ItemStack[]> list = new ArrayList<ItemStack[]>();
		for(int i = 0; i<inputList.size(); i++) {
			list.add(new ItemStack[] {inputList.get(i)});
		}
		List<List<ItemStack[]>> output = new ArrayList<List<ItemStack[]>>();
		output.add(list);
		output.add(outputList);
		return output;
	}
	
}
