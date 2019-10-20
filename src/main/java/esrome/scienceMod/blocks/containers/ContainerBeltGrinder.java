package esrome.scienceMod.blocks.containers;

import esrome.scienceMod.blocks.containers.slots.SlotOutput;
import esrome.scienceMod.energy.ElectricityStorage;
import esrome.scienceMod.tileentity.TileEntityBeltGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBeltGrinder extends Container {

		private final TileEntityBeltGrinder tileentity;
		
		public ContainerBeltGrinder(InventoryPlayer player, TileEntityBeltGrinder tileentity) 
		{
			this.tileentity = tileentity;
			IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			this.addSlotToContainer(new SlotItemHandler(handler, 0, 41, 34));
			this.addSlotToContainer(new SlotOutput(handler, 1, 98, 16));
			this.addSlotToContainer(new SlotOutput(handler, 2, 98, 35));
			this.addSlotToContainer(new SlotOutput(handler, 3, 98, 54));
			
			//add player inventory slots
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
				
				if(index >=0 && index < 4) {
					if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				}if(index >= 4 && index < 31){
					if(!this.mergeItemStack(stack1, 0, 1, false))
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
				}else if(index >= 31 && index < 40){
					if(!this.mergeItemStack(stack1, 0, 1, false))
						if(!this.mergeItemStack(stack1, 4, 31, false)) return ItemStack.EMPTY;
				}else if(!this.mergeItemStack(stack1, 0, 40, true)){
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
