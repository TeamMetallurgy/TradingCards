package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.cards.CardHandler;
import com.teammetallurgy.tradingcard.common.cards.CardSet;
import com.teammetallurgy.tradingcard.common.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBooster extends Item {
    private String setName;
    private CardSet cardSet;

    private Random random = new Random(System.nanoTime());

    public ItemBooster(String setName, CardSet cardSet) {
        this.setName = setName;
        this.cardSet = cardSet;

        this.setTextureName(LibMisc.MODID + ":BoosterBasic");
        this.setUnlocalizedName(setName.replace(" ", "") + ".booster");
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setMaxDamage(0);
    }


    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        for (int i = 0; i < cardSet.getBoosterCards(); i++) {
            player.inventory.addItemStackToInventory(new ItemStack(CardHandler.cardHashMap.get(setName), 1, random.nextInt(cardSet.getCards().size())));
        }
        --player.getCurrentEquippedItem().stackSize;
        return true;
    }
}
