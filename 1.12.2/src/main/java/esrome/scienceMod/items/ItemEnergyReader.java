package esrome.scienceMod.items;

import esrome.scienceMod.ScienceMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnergyReader extends ItemBase {

	public ItemEnergyReader(String name) {
		super(name, ScienceMod.TAB);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te!=null && worldIn.isRemote) {
			if(te.hasCapability(CapabilityEnergy.ENERGY, facing)) {
				player.sendMessage(new TextComponentTranslation("translation.energy").appendText(": " + String.valueOf(te.getCapability(CapabilityEnergy.ENERGY, facing).getEnergyStored())));
			}else {
				player.sendMessage(new TextComponentTranslation("translation.energy_reader_fail"));
			}
		}
		return EnumActionResult.SUCCESS;
	}

}
