package me.christo.surgepickaxes.Handlers;

import org.bukkit.entity.Player;

public class Pickaxe {

    //    int efficiencyLevel;
//    int fortuneLevel;
//    int gemFinderLevel;
//    int rampageLevel;
//    int greedLevel;
//    int jackPotLevel;
//    int shatterProofLevel;
    Player player;

    public Pickaxe(Player player) {
        this.player = player;
    }

    public int getPickaxeLevel() {

        return 0;
    }

    public int getEnchantmentLevel(String enchantment) {

        return 0;
    }
    public int addEnchantmentLevel(String enchantment) {

        return 0;
    }

    public boolean isEligibleForEnchantment(String enchantment) {

        //if pickaxe level > enchantment level [available]



        return false;
    }

    public boolean hasPurchasedEnchantment(String enchantment) {

        //if pickaxe level == enchantment level

        return false;
    }

}
