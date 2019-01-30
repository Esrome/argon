package esrome.scienceMod.blocks.containers;

import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSteamGenerator extends Container {

		private final TileEntitySteamGenerator tileentity;
		private int energy, cookTime, steam, fuelTime;
		
		public ContainerSteamGenerator(InventoryPlayer player, TileEntitySteamGenerator tileentity) 
		{
			this.tileentity = tileentity;
			IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			this.addSlotToContainer(new SlotItemHandler(handler, 0, 18, 17));
			this.addSlotToContainer(new SlotItemHandler(handler, 1, 45, 53));
			this.addSlotToContainer(new SlotItemHandler(handler, 2, 72, 17));
			
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 9; x++)
				{
					this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
				}
			}
			
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
			}
		}
		
		@Override
		public boolean canInteractWith(EntityPlayer playerIn) 
		{
			return this.tileentity.isUsableByPlayer(playerIn);
		}
		
		@Override
		public void updateProgressBar(int id, int data) 
		{
			this.tileentity.setField(id, data);
		}
		
		@Override
		public void detectAndSendChanges() 
		{
			super.detectAndSendChanges();
			
			for(int i = 0; i < this.listeners.size(); ++i) 
			{
				IContainerListener listener = (IContainerListener)this.listeners.get(i);
				if(this.energy != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
				if(this.cookTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
				if(this.steam != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
				if(this.fuelTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			}
			
			this.energy = this.tileentity.getField(0);
			this.cookTime = this.tileentity.getField(1);
			this.steam = this.tileentity.getField(2);
			this.steam = this.tileentity.getField(3);
		}
		
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
