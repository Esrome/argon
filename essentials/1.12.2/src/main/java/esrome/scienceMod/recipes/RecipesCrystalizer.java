package esrome.scienceMod.recipes;

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
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	
	public static RecipesCrystalizer getInstance(){
		return INSTANCE;
	}
	
	private RecipesCrystalizer() {
		addCrystalizerRecipe(new ItemStack(ModItems.SILICON), new ItemStack(Items.QUARTZ), new ItemStack(ModItems.CRYSTALLINE_SILICON));
		addCrystalizerRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.SAND), new ItemStack(Blocks.GLASS, 3));
		addCrystalizerRecipe(new ItemStack(ModItems.SILICA), new ItemStack(ModItems.SILICA), new ItemStack(Items.QUARTZ));
	}

	
	public void addCrystalizerRecipe(ItemStack input1, ItemStack input2, ItemStack result) 
	{
		if(getCrystalizerRecipe(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
	}
	
	public ItemStack getCrystalizerRecipe(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
			//oredictionary testing for Item 1
			int[] ids = OreDictionary.getOreIDs((ItemStack)entry.getKey());
			for(int id: ids) {
				String name = OreDictionary.getOreName(id);
				for(ItemStack stack1 : OreDictionary.getOres(name)) {
					if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
						for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
							//oredictionary testing for Item 2
							int[] ids2 = OreDictionary.getOreIDs((ItemStack)ent.getValue());
							for(int id2: ids2) {
								String name2 = OreDictionary.getOreName(id2);
								for(ItemStack stack : OreDictionary.getOres(name2)) {
									if(this.compareItemStacks(input2, stack)) {
										return (ItemStack)ent.getValue();
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
}
