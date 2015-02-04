package com.teammetallurgy.tradingcard.inventory;

import com.teammetallurgy.tradingcard.common.items.ItemCards;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryCardAlbum implements IInventory {
    public ItemStack stack;
    protected ItemStack[] inventory = new ItemStack[6 * 9 * 16];

    public InventoryCardAlbum(ItemStack itemStack) {
        stack = itemStack;

        // Just in case the itemstack doesn't yet have an NBT Tag Compound:
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        readFromNBT(stack.getTagCompound());
    }


    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack stack = getStackInSlot(slotIndex);
        if (stack != null) {
            if (stack.stackSize > decrementAmount) {
                stack = stack.splitStack(decrementAmount);
                markDirty();
            } else {
                setInventorySlotContents(slotIndex, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "Card Album";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
                inventory[i] = null;
        }
        writeToNBT(stack.getTagCompound());
        
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {
        
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemCards;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < nbtTagList.tagCount(); i++) {
            NBTTagCompound dataTag = nbtTagList.getCompoundTagAt(i);
            byte slot = dataTag.getByte("slot");
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(dataTag);
            setInventorySlotContents(slot, itemStack);
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemStack = getStackInSlot(i);
            if (itemStack != null) {
                NBTTagCompound dataTag = new NBTTagCompound();
                itemStack.writeToNBT(dataTag);
                dataTag.setByte("slot", (byte) i);
                nbtTagList.appendTag(dataTag);
            }
        }
        nbtTagCompound.setTag("Inventory", nbtTagList);
    }
}

