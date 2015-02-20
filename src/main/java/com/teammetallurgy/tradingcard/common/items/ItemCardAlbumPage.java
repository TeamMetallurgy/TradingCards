package com.teammetallurgy.tradingcard.common.items;


import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import net.minecraft.item.Item;

public class ItemCardAlbumPage extends Item {

    public ItemCardAlbumPage() {
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setTextureName(LibMisc.MODID + ":CardPage");
        this.setUnlocalizedName("cardAlbumPage");
    }
}
