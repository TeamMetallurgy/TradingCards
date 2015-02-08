package com.teammetallurgy.tradingcard.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCardAlbum extends Container {


    private final InventoryCardAlbum inventoryAlbum;

    private int id = 0;

    public ContainerCardAlbum(EntityPlayer entityPlayer, InventoryCardAlbum inventoryAlbum) {
        this.inventoryAlbum = inventoryAlbum;

        bindPlayerInventory(entityPlayer.inventory);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                addSlotToContainer(new SlotCard(inventoryAlbum, id++ + inventoryAlbum.page * 54, 12 + j * 18, 11 + i * 18));
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                addSlotToContainer(new SlotCard(inventoryAlbum, id++ + inventoryAlbum.page * 54, 98 + j * 18, 11 + i * 18));
            }
        }


    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, id++, 8 + i * 18, 198));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, id++, 8 + j * 18, 140 + i * 18));
            }
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
            inventoryAlbum.writeToNBT(inventoryAlbum.stack.getTagCompound());
        }
    }

}
