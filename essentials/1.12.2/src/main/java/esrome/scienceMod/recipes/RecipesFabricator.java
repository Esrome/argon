package esrome.scienceMod.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import esrome.scienceMod.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesFabricator {

	private static final RecipesFabricator INSTANCE = new RecipesFabricator();
	private final List<ItemStack> input1List = new ArrayList<ItemStack>();
	private final List<ItemStack> input2List = new ArrayList<ItemStack>();
	private final List<ItemStack> input3List = new ArrayList<ItemStack>();
	private final List<ItemStack> blueprintList = new ArrayList<ItemStack>();
	private final List<ItemStack> outputList = new ArrayList<ItemStack>();
	
	public static RecipesFabricator getInstance(){
		return INSTANCE;
	}
	
	private RecipesFabricator() {
		addFabricatorRecipe(new ItemStack(ModItems.COPPER_DUST), new ItemStack(ModItems.COPPER_DUST), new ItemStack(ModItems.COPPER_DUST), new ItemStack(ModItems.GEAR_BLUEPRINT), new ItemStack(ModItems.COPPER_GEAR));
		addFabricatorRecipe(new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.GEAR_BLUEPRINT), new ItemStack(ModItems.LEAD_GEAR));
		addFabricatorRecipe(new ItemStack(ModItems.IRON_DUST), new ItemStack(ModItems.IRON_DUST), new ItemStack(ModItems.IRON_DUST), new ItemStack(ModItems.GEAR_BLUEPRINT), new ItemStack(ModItems.IRON_GEAR));
		addFabricatorRecipe(new ItemStack(ModItems.GOLD_DUST), new ItemStack(ModItems.GOLD_DUST), new ItemStack(ModItems.GOLD_DUST), new ItemStack(ModItems.GEAR_BLUEPRINT), new ItemStack(ModItems.GOLD_GEAR));
	}

	
	public void addFabricatorRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack blueprint, ItemStack results) 
	{
		if(getFabricatorRecipe(input1, input2, input3, blueprint) != null) return;
		input1List.add(input1);
		input2List.add(input2);
		input3List.add(input3);
		blueprintList.add(blueprint);
		outputList.add(results);
	}
	
	public ItemStack getFabricatorRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack blueprint) 
	{
		for(int i=0;i<input1List.size();i++) 
		{
			if(input1List.get(i)==input1) {
				if(input2List.get(i)==input2) {
					if(input3List.get(i)==input3) {
						if(blueprintList.get(i)==blueprint) return outputList.get(i);
					}
				}
			}
		}
		return null;
	}
	
	public List<List<ItemStack>> getSmeltingList() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		for(int i = 0; i<input1List.size(); i++) {
			list.add(input1List.get(i));
		}
		List<ItemStack> list1 = new ArrayList<ItemStack>();
		for(int i = 0; i<input2List.size(); i++) {
			list1.add(input2List.get(i));
		}
		List<ItemStack> list2 = new ArrayList<ItemStack>();
		for(int i = 0; i<input3List.size(); i++) {
			list2.add(input3List.get(i));
		}
		List<ItemStack> list3 = new ArrayList<ItemStack>();
		for(int i = 0; i<blueprintList.size(); i++) {
			list3.add(blueprintList.get(i));
		}
		List<List<ItemStack>> output = new ArrayList<List<ItemStack>>();
		output.add(list);
		output.add(list1);
		output.add(list2);
		output.add(list3);
		output.add(outputList);
		return output;
	}
	
}
