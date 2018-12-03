package esrome.scienceMod.blocks;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.init.ModBlocks;
import esrome.scienceMod.init.ModItems;
import esrome.scienceMod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	
	public BlockBase(String name, Material material, float toughness, int harvestlevel, SoundType type, CreativeTabs creativeTab) 
	{
		super(material);
		if(!(harvestlevel == -1)){
			setHarvestLevel("pickaxe", harvestlevel);
		}
		setSoundType(type);
		setHardness(toughness);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	
	
	@Override
	public void registerModels() 
	{
		ScienceMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
