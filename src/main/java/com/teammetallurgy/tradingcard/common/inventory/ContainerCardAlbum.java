package com.teammetallurgy.tradingcard.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCardAlbum extends Container {


    public final InventoryCardAlbum inventoryAlbum;

    public ContainerCardAlbum(EntityPlayer entityPlayer, InventoryCardAlbum inventoryAlbum) {
        this.inventoryAlbum = inventoryAlbum;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {

                addSlotToContainer(new SlotCard(inventoryAlbum, j + i * 4 + inventoryAlbum.currentPage * 54, 12 + j * 18, 11 + i * 18));
            }
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {

                addSlotToContainer(new SlotCard(inventoryAlbum, j + i * 4 + 24 + inventoryAlbum.currentPage * 54, 98 + j * 18, 11 + i * 18));
            }
        }

        bindPlayerInventory(entityPlayer.inventory);
        System.out.println("New");
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
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        if (player.worldObj.isRemote) {
            inventoryAlbum.writeToNBT(inventoryAlbum.stack.getTagCompound());
        }
    }

}
