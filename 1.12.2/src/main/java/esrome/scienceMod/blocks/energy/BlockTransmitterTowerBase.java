package esrome.scienceMod.blocks.energy;

import esrome.scienceMod.ScienceMod;
import esrome.scienceMod.blocks.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTransmitterTowerBase extends BlockBase {

	public BlockTransmitterTowerBase(String name) {
		super(name, Material.IRON, 3.0f, -1, SoundType.METAL, ScienceMod.TAB);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
