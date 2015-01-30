package com.teammetallurgy.tradingcard.common.cards;

import com.google.gson.Gson;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CardHandler {
    private static Random random = new Random(System.nanoTime());

    private static ArrayList<String> setNames = new ArrayList<String>();
    private static ArrayList<CardSet> cardSets = new ArrayList<CardSet>();

    public static void register(String set) {
        if (!setNames.contains(set)) {
            setNames.add(set);
        } else {
            System.out.println("The set " + set + " is already registered!");
        }

    }

    public static void loadSets() {
        for (String set : setNames) {
            try {
                String path = "assets/tradingcard/sets/";

                URL resource = Block.class.getClassLoader().getResource(path + set + ".json");
                if (resource != null) {
                    CardSet cards = new Gson().fromJson(new InputStreamReader(resource.openStream()), CardSet.class);
                    cardSets.add(cards);
                    System.out.println("The set " + cards.getSetname() + " has been loaded");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ItemStack getBooster() {

        if (random.nextInt(25) == 0) {
            // get booster in set
            int totalSum = 0;
            for (CardSet set : cardSets) {
                totalSum += set.getDropweight();
            }
            CardSet[] items = new CardSet[totalSum];
            for (int y = 0; y < cardSets.size(); y++) {
                CardSet set = cardSets.get(y);
                for (int i = 0; i < set.getDropweight(); i++) {
                    items[i + y] = set;
                }
            }
            String set = items[random.nextInt(totalSum)].getSetname();

            // return itemstack using set
            

        }
        return null;
    }
}
