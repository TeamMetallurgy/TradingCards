package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.handler.CardSet;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBooster extends Item {
    private CardSet cardSet;
    private ItemCards setCards;

    private Random random = new Random(System.nanoTime());

    public ItemBooster(CardSet cardSet, ItemCards setCards) {
        this.cardSet = cardSet;
        this.setCards = setCards;

        this.setTextureName(LibMisc.MODID + ":BoosterBasic");
        this.setUnlocalizedName(cardSet.getSetname().replace(" ", ".") + ".booster");
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
    }


    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        for (int i = 0; i < cardSet.getBoosterCards(); i++) {
            player.inventory.addItemStackToInventory(new ItemStack(setCards, 1, random.nextInt(cardSet.getCards().size())));
        }
        --player.getCurrentEquippedItem().stackSize;
        return true;
    }
}
