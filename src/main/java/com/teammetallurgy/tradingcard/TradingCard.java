package com.teammetallurgy.tradingcard;

import com.teammetallurgy.tradingcard.common.cards.CardHandler;
import com.teammetallurgy.tradingcard.common.items.ItemCardAlbum;
import com.teammetallurgy.tradingcard.common.lib.LibMisc;
import com.teammetallurgy.tradingcard.common.network.GuiHandler;
import com.teammetallurgy.tradingcard.inventory.InventoryCardAlbum;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Mod(modid = LibMisc.MODID, name = LibMisc.MODNAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class TradingCard {


    @Mod.Instance(LibMisc.MODID)
    public static TradingCard instance;

    @SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
    public static CommonProxy proxy;
    public CreativeTabs creativeTabItems = new CreativeTab(LibMisc.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        System.out.println("Test");
        CardHandler.register("base");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CardHandler.loadSets();

        GameRegistry.registerItem(new ItemCardAlbum(), "cardalbum");
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CardHandler.getBooster();
    }

    @SubscribeEvent
    public void onMonsterDead(LivingDropsEvent event) {
        event.entityLiving.dropItem(CardHandler.getBooster(), 1);
    }

}
