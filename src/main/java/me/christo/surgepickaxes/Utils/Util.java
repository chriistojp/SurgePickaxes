package me.christo.surgepickaxes.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static String color(String s, boolean usePrefix) {
        String prefix = "&b&lSKYSURGE | ";
        if (usePrefix) {
            return ChatColor.translateAlternateColorCodes('&', prefix + "&7" + s);
        }
        return ChatColor.translateAlternateColorCodes('&', "&7" + s);
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack createItem(Material material, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.color(displayName));
        List<String> loreList = new ArrayList<>();
        for (String s : lore) {
            loreList.add(Util.color(s, false));
        }
        meta.setLore(loreList);
        item.setItemMeta(meta);


        return item;

    }


    public static List<String> formatString(int lineLength, String... inputString) {
        List<String> outputList = new ArrayList<>();
        List<String> words = Arrays.asList(String.join(" ", inputString).split("\\s+"));
        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                // Add any blank lines to the output list
                outputList.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
                outputList.add(""); // Add a blank line to the output list
            } else if (currentLine.length() + word.length() > lineLength) {
                outputList.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
                currentLine.append(word);
            } else {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }
        }
        if (currentLine.length() > 0) {
            outputList.add(currentLine.toString().trim());
        }
        return outputList;
    }






}
