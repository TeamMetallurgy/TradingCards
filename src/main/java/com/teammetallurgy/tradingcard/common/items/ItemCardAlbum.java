package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.network.PacketGui;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCardAlbum extends Item {
    public ItemCardAlbum() {
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setTextureName(LibMisc.MODID + ":CardBinder");
        this.setUnlocalizedName("cardAlbum");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!itemStackIn.hasTagCompound())
            itemStackIn.setTagCompound(new NBTTagCompound());

        if (worldIn.isRemote)
            TradingCard.network.sendToServer(new PacketGui(0));

        return itemStackIn;

    }

}
