package com.teammetallurgy.tradingcard.common.network;

import com.teammetallurgy.tradingcard.client.gui.GuiAlbum;
import com.teammetallurgy.tradingcard.common.inventory.ContainerCardAlbum;
import com.teammetallurgy.tradingcard.common.inventory.InventoryCardAlbum;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    static InventoryCardAlbum album;

    public static void test(InventoryCardAlbum t) {
        album = t;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (ID == 0)
            if (album != null) {
                InventoryCardAlbum az = album;
                return new ContainerCardAlbum(entityPlayer, az);
            } else
                return new ContainerCardAlbum(entityPlayer, new InventoryCardAlbum(entityPlayer.getHeldItem()));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            if (album != null) {
                InventoryCardAlbum az = album;
                return new GuiAlbum(player, az);
            } else
                return new GuiAlbum(player, new InventoryCardAlbum(player.getHeldItem()));
        return null;
    }
}

