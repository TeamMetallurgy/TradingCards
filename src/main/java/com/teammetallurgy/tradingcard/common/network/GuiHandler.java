package com.teammetallurgy.tradingcard.common.network;

import com.teammetallurgy.tradingcard.client.gui.GuiAlbum;
import com.teammetallurgy.tradingcard.common.inventory.ContainerCardAlbum;
import com.teammetallurgy.tradingcard.common.inventory.InventoryCardAlbum;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z){
        if (id == 0)
            return new ContainerCardAlbum(entityPlayer, new InventoryCardAlbum(entityPlayer.getHeldItem()));

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new GuiAlbum(player, new InventoryCardAlbum(player.getHeldItem()));
        return null;
    }
}

