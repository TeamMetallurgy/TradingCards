package com.teammetallurgy.tradingcard;

import com.teammetallurgy.tradingcard.common.cards.CardHandler;
import com.teammetallurgy.tradingcard.common.lib.LibMisc;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

@Mod(modid = LibMisc.MODID, name = LibMisc.MODNAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class TradingCard {
    

    @Mod.Instance(LibMisc.MODID)
    public static TradingCard instance;

    @SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
    public static CommonProxy proxy;
    public CreativeTabs creativeTabItems= new CreativeTab(LibMisc.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        System.out.println("Test");
        CardHandler.register("base");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CardHandler.loadSets();
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CardHandler.getBooster();
    }

    @SubscribeEvent
    public void onMonsterDead(LivingDropsEvent event){
        
        event.entityLiving.dropItem(CardHandler.getBooster(),1);
        // drop booster

    }
}
