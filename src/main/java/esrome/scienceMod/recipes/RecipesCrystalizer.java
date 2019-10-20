package esrome.scienceMod.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import esrome.scienceMod.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesCrystalizer {

	private static final RecipesCrystalizer INSTANCE = new RecipesCrystalizer();
	private final List<ItemStack> inputList = new ArrayList<ItemStack>();
	private final List<ItemStack> inputTwoList = new ArrayList<ItemStack>();
	private final List<ItemStack> outputList = new ArrayList<ItemStack>();
	private final List<Integer> outputCountList = new ArrayList<Integer>();
	
	public static RecipesCrystalizer getInstance(){
		return INSTANCE;
	}
	
	private RecipesCrystalizer() {
		addCrystalizerRecipe(new ItemStack(ModItems.SILICON), new ItemStack(Items.QUARTZ), new ItemStack(ModItems.CRYSTALLINE_SILICON));
		addCrystalizerRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.SAND), new ItemStack(Blocks.GLASS), 3);
		addCrystalizerRecipe(new ItemStack(ModItems.SILICA), new ItemStack(ModItems.SILICA), new ItemStack(Items.QUARTZ));
	}

	public void addCrystalizerRecipe(ItemStack input1, ItemStack input2, ItemStack result) {
		addCrystalizerRecipe(input1, input2, result, 1);
	}
	
	public void addCrystalizerRecipe(ItemStack input1, ItemStack input2, ItemStack result, int count) {
		if(getCrystalizerRecipe(input1, input2) != null) return;
		inputList.add(input1);
		inputTwoList.add(input2);
		outputList.add(result);
		outputCountList.add(count);
	}
	
	public ItemStack getCrystalizerRecipe(ItemStack input1, ItemStack input2) 
	{
		for(int i=0;i<inputList.size();i++) 
		{
			int[] ids = OreDictionary.getOreIDs(inputList.get(i));
			for(int id: ids) {
				String name = OreDictionary.getOreName(id);
				for(ItemStack stack: OreDictionary.getOres(name)) {
					//Second Run
					int[] idsTwo = OreDictionary.getOreIDs(inputTwoList.get(i));
					for(int idTwo: idsTwo) {
						String nameTwo = OreDictionary.getOreName(idTwo);
						for(ItemStack stackTwo: OreDictionary.getOres(nameTwo)) {
							if(input1.getItem()==stack.getItem() && input2.getItem()==stackTwo.getItem()) return outputList.get(i);
							if(input2.getItem()==stack.getItem() && input1.getItem()==stackTwo.getItem()) return outputList.get(i);
						}
					}
					//Second Run
				}
			}
		}
		return null;
	}
	
	public int getRecipeCount(ItemStack input1, ItemStack input2) 
	{
		for(int i=0;i<inputList.size();i++) 
		{
			int[] ids = OreDictionary.getOreIDs(inputList.get(i));
			for(int id: ids) {
				String name = OreDictionary.getOreName(id);
				for(ItemStack stack: OreDictionary.getOres(name)) {
					//Second Run
					int[] idsTwo = OreDictionary.getOreIDs(inputTwoList.get(i));
					for(int idTwo: idsTwo) {
						String nameTwo = OreDictionary.getOreName(idTwo);
						for(ItemStack stackTwo: OreDictionary.getOres(nameTwo)) {
							if(input1.getItem()==stack.getItem() && input2.getItem()==stackTwo.getItem()) return outputCountList.get(i);
							if(input2.getItem()==stack.getItem() && input1.getItem()==stackTwo.getItem()) return outputCountList.get(i);
						}
					}
					//Second Run
				}
			}
		}
		return 1;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public List<ItemStack> firstInputs(){
		return this.inputList;
	}
	
	public List<ItemStack> secondInputs(){
		return this.inputTwoList;
	}
	
	public List<ItemStack> outputs(){
		return this.outputList;
	}
	
}
