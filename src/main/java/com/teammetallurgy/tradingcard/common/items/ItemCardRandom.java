package com.teammetallurgy.tradingcard.common.items;

import com.teammetallurgy.tradingcard.TradingCard;
import com.teammetallurgy.tradingcard.common.utils.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class ItemCardRandom extends Item {

    private String rarity;

    public ItemCardRandom(String rarity) {
        this.setCreativeTab(TradingCard.instance.creativeTabItems);
        this.setTextureName(LibMisc.MODID + ":Random" + rarity + "Card");
        this.setUnlocalizedName("cardRandom");

        this.rarity = rarity;
    }

    @Override
    public String getItemStackDisplayName(ItemStack p_77653_1_) {
        return "Random " + rarity + " Cards";
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        ArrayList<ItemStack> cards = OreDictionary.getOres("card" + rarity);

        ItemStack stack = cards.get(world.rand.nextInt(cards.size()));
        stack.stackSize++;
        player.inventory.addItemStackToInventory(stack);

        --player.getCurrentEquippedItem().stackSize;
        return true;
    }
}
