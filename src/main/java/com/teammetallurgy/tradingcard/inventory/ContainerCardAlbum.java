package com.teammetallurgy.tradingcard.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCardAlbum extends Container {
    private final InventoryCardAlbum inventoryAlbum;

    public ContainerCardAlbum(EntityPlayer entityPlayer, InventoryCardAlbum inventoryAlbum) {
        this.inventoryAlbum = inventoryAlbum;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new SlotCard(inventoryAlbum, j + i * 9 + 36, 10 + j * 18, 18 + i * 18));
            }
        }

        bindPlayerInventory(entityPlayer.inventory);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 198));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return inventoryAlbum.isUseableByPlayer(player);
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        super.onContainerClosed(p_75134_1_);
        if (p_75134_1_.worldObj.isRemote) {
            System.out.println("HELP");
            inventoryAlbum.writeToNBT(inventoryAlbum.stack.getTagCompound());
            inventoryAlbum.stack.getTagCompound().setString("test", "IDK");
        }
    }

}
