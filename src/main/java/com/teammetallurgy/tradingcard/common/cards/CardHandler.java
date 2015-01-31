package com.teammetallurgy.tradingcard.common.cards;

import com.google.gson.Gson;
import com.teammetallurgy.tradingcard.common.items.ItemBooster;
import com.teammetallurgy.tradingcard.common.items.ItemCards;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CardHandler {
    private static Random random = new Random(System.nanoTime());

    private static ArrayList<String> setNames = new ArrayList<String>();
    private static ArrayList<CardSet> cardsetList = new ArrayList<CardSet>();

    public static HashMap<String, ItemCards> cardHashMap = new HashMap<String, ItemCards>();
    public static HashMap<String, ItemBooster> boosterCard = new HashMap<String, ItemBooster>();

    private static int totalSum;
    private static CardSet[] items;

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
                URL resource = Block.class.getClassLoader().getResource("assets/tradingcard/sets/" + set + ".json");
                if (resource != null) {
                    CardSet cardSet = new Gson().fromJson(new InputStreamReader(resource.openStream()), CardSet.class);
                    cardsetList.add(cardSet);
                    String setName = cardSet.getSetname();

                    ItemCards setCards = new ItemCards(setName.replace(" ", ".") + ".cards");
                    cardHashMap.put(setName, setCards);

                    for (int i = 0; i < cardSet.getCards().size(); i++) {
                        CardSet.Cards card = cardSet.getCards().get(i);
                        createCreateItem(setName, setCards, card, i);
                    }
                    totalSum += cardSet.getDropweight();

                    ItemBooster itemBooster = new ItemBooster(setName, cardSet);
                    GameRegistry.registerItem(itemBooster, setName + ".booster");
                    boosterCard.put(setName, itemBooster);

                    System.out.println("The set " + setName + " has been loaded");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        items = new CardSet[totalSum];
        for (int x = 0; x < cardsetList.size(); x++) {
            CardSet set = cardsetList.get(x);
            for (int i = 0; i < set.getDropweight(); i++) {
                items[i + x] = set;
            }
        }

    }

    private static void createCreateItem(String setname, ItemCards setCards, CardSet.Cards card, int meta) {
        if (meta == 0) {
            GameRegistry.registerItem(setCards, setname + ".cards");
        }

        setCards.addSubItem(meta, card.getCardName(), card.getSprite(), card);
    }

    public static Item getBooster() {

        if (random.nextInt(4) != 0)
            return null;
        
        String set = items[random.nextInt(totalSum)].getSetname();
        return boosterCard.get(set);
    }
}
