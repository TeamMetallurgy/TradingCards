package com.teammetallurgy.tradingcard.common.inventory;

import com.teammetallurgy.tradingcard.common.items.ItemCards;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCard extends Slot {
    public SlotCard(InventoryCardAlbum inventoryAlbum, int id, int x, int y) {
        super(inventoryAlbum, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemCards;
    }
}
