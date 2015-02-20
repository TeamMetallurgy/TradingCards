package com.teammetallurgy.tradingcard.common.handler;

import com.teammetallurgy.tradingcard.TradingCard;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler {

    public RecipeHandler() {
        GameRegistry.addShapedRecipe(new ItemStack(TradingCard.itemAlbumPage), new Object[]{" p ", "pbp", "p", Character.valueOf('p'), Items.paper, Character.valueOf('b'), "cardBooster"});
        GameRegistry.addShapedRecipe(new ItemStack(TradingCard.itemCardAlbum), new Object[]{" p ", "pbp", "p", Character.valueOf('p'), TradingCard.itemAlbumPage, Character.valueOf('b'), Items.book});

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TradingCard.randomCardSet.get("Common")), new Object[]{"cardCommon", "cardCommon", "cardCommon", "cardCommon"}));
        GameRegistry.addRecipe(new AlbumRecipe(new ItemStack(TradingCard.itemCardAlbum), new Object[]{new ItemStack(TradingCard.itemCardAlbum), new ItemStack(TradingCard.itemAlbumPage)}));
    }

}
