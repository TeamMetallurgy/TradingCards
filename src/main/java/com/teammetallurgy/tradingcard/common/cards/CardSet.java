package com.teammetallurgy.tradingcard.common.cards;

import java.util.ArrayList;

public class CardSet {
    private String setname;
    private int dropweight;
    private String boosterSprite;
    private int boosterCards;
    private Chance typeChance;
    private Guarantees guarantees;
    private ArrayList<Cards> cards;

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public int getDropweight() {
        return dropweight;
    }

    public void setDropweight(int dropweight) {
        this.dropweight = dropweight;
    }

    public String getBoosterSprite() {
        return boosterSprite;
    }

    public void setBoosterSprite(String boosterSprite) {
        this.boosterSprite = boosterSprite;
    }

    public int getBoosterCards() {
        return boosterCards;
    }

    public void setBoosterCards(int boosterCards) {
        this.boosterCards = boosterCards;
    }

    public Chance getTypeChance() {
        return typeChance;
    }

    public void setTypeChance(Chance typeChance) {
        this.typeChance = typeChance;
    }

    public Guarantees getGuarantees() {
        return guarantees;
    }

    public void setGuarantees(Guarantees guarantees) {
        this.guarantees = guarantees;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }


    class Chance {
        private float uncommon;
        private float rare;
        private float superRare;
        private float ultraRare;
        private float legendary;
        private float foil;
        private float gold;
        private float foilGod;


        public float getUncommon() {
            return uncommon;
        }

        public void setUncommon(float uncommon) {
            this.uncommon = uncommon;
        }

        public float getRare() {
            return rare;
        }

        public void setRare(float rare) {
            this.rare = rare;
        }

        public float getSuperRare() {
            return superRare;
        }

        public void setSuperRare(float superRare) {
            this.superRare = superRare;
        }

        public float getUltraRare() {
            return ultraRare;
        }

        public void setUltraRare(float ultraRare) {
            this.ultraRare = ultraRare;
        }

        public float getLegendary() {
            return legendary;
        }

        public void setLegendary(float legendary) {
            this.legendary = legendary;
        }

        public float getFoil() {
            return foil;
        }

        public void setFoil(float foil) {
            this.foil = foil;
        }

        public float getGold() {
            return gold;
        }

        public void setGold(float gold) {
            this.gold = gold;
        }

        public float getFoilGod() {
            return foilGod;
        }

        public void setFoilGod(float foilGod) {
            this.foilGod = foilGod;
        }

    }

    class Guarantees {
        private float common;
        private float uncommon;
        private float uncommonBetter;
        private float rare;
        private float rareBetter;
        private float superRare;
        private float superRareBetter;
        private float ultraRare;
        private float ultraRareBetter;
        private float legendary;
        private float foil;
        private float gold;

    }

    class Cards {
        private String cardName;
        private String rarity;
        private String sprite;
        private String flavortext;
        private boolean alwayFoil;
        private boolean alwayGold;

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getRarity() {
            return rarity;
        }

        public void setRarity(String rarity) {
            this.rarity = rarity;
        }

        public String getSprite() {
            return sprite;
        }

        public void setSprite(String sprite) {
            this.sprite = sprite;
        }

        public String getFlavortext() {
            return flavortext;
        }

        public void setFlavortext(String flavortext) {
            this.flavortext = flavortext;
        }

        public boolean isAlwayFoil() {
            return alwayFoil;
        }

        public void setAlwayFoil(boolean alwayFoil) {
            this.alwayFoil = alwayFoil;
        }

        public boolean isAlwayGold() {
            return alwayGold;
        }

        public void setAlwayGold(boolean alwayGold) {
            this.alwayGold = alwayGold;
        }
    }

}
