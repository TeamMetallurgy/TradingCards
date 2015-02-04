package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCardAlbum extends Item {
    public ItemCardAlbum() {
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setTextureName(LibMisc.MODID + ":CardBinder");
        this.setUnlocalizedName("cardalbum");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        player.openGui(TradingCard.instance, 0, world, 0, 0, 0);
        return itemstack;

    }

}
