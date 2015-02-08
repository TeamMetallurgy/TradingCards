package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import com.teammetallurgy.tradingcard.common.network.PacketGui;
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
        if (!itemstack.hasTagCompound())
            itemstack.setTagCompound(new NBTTagCompound());
        
        if (world.isRemote)
            TradingCard.network.sendToServer(new PacketGui(0));
        else
            player.openGui(TradingCard.instance, 0, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
        
        return itemstack;

    }

}
