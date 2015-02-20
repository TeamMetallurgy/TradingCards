package com.teammetallurgy.tradingcard;

import com.teammetallurgy.tradingcard.common.CommonProxy;
import com.teammetallurgy.tradingcard.common.handler.CardHandler;
import com.teammetallurgy.tradingcard.common.handler.RecipeHandler;
import com.teammetallurgy.tradingcard.common.items.ItemCardAlbum;
import com.teammetallurgy.tradingcard.common.items.ItemCardAlbumPage;
import com.teammetallurgy.tradingcard.common.items.ItemCardRandom;
import com.teammetallurgy.tradingcard.common.network.GuiHandler;
import com.teammetallurgy.tradingcard.common.network.PacketGui;
import com.teammetallurgy.tradingcard.common.tabs.CreativeTab;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

@Mod(modid = LibMisc.MODID, name = LibMisc.MODNAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class TradingCard {


    @Mod.Instance(LibMisc.MODID)
    public static TradingCard instance;

    @SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
    public static CommonProxy proxy;
    public CreativeTabs creativeTabItems = new CreativeTab(LibMisc.MODID);

    public static SimpleNetworkWrapper network;

    public static Item itemCardAlbum;
    public static Item itemAlbumPage;

    public static HashMap<String, Item> randomCardSet = new HashMap<String, Item>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(LibMisc.MODID);
        network.registerMessage(PacketGui.Handler.class, PacketGui.class, 0, Side.SERVER);

        CardHandler.register("base");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CardHandler.loadSets();

        itemCardAlbum = new ItemCardAlbum();
        GameRegistry.registerItem(itemCardAlbum, "cardAlbum");

        itemAlbumPage = new ItemCardAlbumPage();
        GameRegistry.registerItem(itemAlbumPage, "cardAlbumPage");

        ItemCardRandom cardRandom = new ItemCardRandom("Common");
        GameRegistry.registerItem(cardRandom, "cardRandomCommon");
        randomCardSet.put("Common", cardRandom);

        new RecipeHandler();
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CardHandler.getBooster();
    }

    @Mod.EventHandler
    public void onServerJoin(PlayerEvent.PlayerLoggedInEvent event) {
//        EntityPlayer player = event.player;
//        if (!player.worldObj.isRemote) {
//            if (player.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
//                NBTTagCompound nbtTagCompound = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
//                System.out.println(nbtTagCompound);
//                if (!nbtTagCompound.hasKey("existingPlayer") || !nbtTagCompound.getBoolean("existingPlayer")) {
//                    nbtTagCompound.setBoolean("existingPlayer", true);
//
//                    player.inventory.addItemStackToInventory(new ItemStack(Items.apple));
//                }
//            }
//        }
    }

}
