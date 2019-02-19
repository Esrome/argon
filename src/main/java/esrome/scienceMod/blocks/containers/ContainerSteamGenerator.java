package esrome.scienceMod.blocks.containers;

import esrome.scienceMod.blocks.containers.slots.SlotOutput;
import esrome.scienceMod.blocks.containers.slots.SlotSteamFuel;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSteamGenerator extends Container {

		private final TileEntitySteamGenerator tileentity;
		
		public ContainerSteamGenerator(InventoryPlayer player, TileEntitySteamGenerator tileentity) 
		{
			this.tileentity = tileentity;
			IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			this.addSlotToContainer(new SlotItemHandler(handler, 0, 18, 17));
			this.addSlotToContainer(new SlotSteamFuel(handler, 1, 45, 53));
			this.addSlotToContainer(new SlotOutput(handler, 2, 72, 17));
			
			//adding player inventory
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 9; x++)
				{
					this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
				}
			}
			//hotbar
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
			}
		}
		
		//can you open the container
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) 
		{
			return this.tileentity.isUsableByPlayer(playerIn);
		}
		
		//handles shift-click events
		@Override
		public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
		{
			ItemStack stack = ItemStack.EMPTY;
			Slot slot = (Slot)this.inventorySlots.get(index);
			
			if(slot != null && slot.getHasStack())
			{
				ItemStack stack1 = slot.getStack();
				stack = stack1.copy();
				
				if(index >= 0 && index < 27)
				{
					if(!this.mergeItemStack(stack1, 27, 36, false)) return ItemStack.EMPTY;
				}
				else if(index >= 27 && index < 36)
				{
					if(!this.mergeItemStack(stack1, 0, 27, false)) return ItemStack.EMPTY;
				}
				else if(!this.mergeItemStack(stack1, 0, 36, false))
				{
					return ItemStack.EMPTY;
				}
				
				if(stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
				else slot.onSlotChanged();
				
				if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
				slot.onTake(playerIn, stack1);
			}
			
			return stack;
		}
	
}
