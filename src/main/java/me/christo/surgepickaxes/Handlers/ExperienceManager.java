package me.christo.surgepickaxes.Handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public enum ExperienceManager {

    STONE(1),
    COAL_ORE(5),
    IRON_ORE(10),
    GOLD_ORE(20),
    DIAMOND_ORE(50),
    EMERALD_ORE(100),
    IRON_BLOCK(125),
    GOLD_BLOCK(150),
    DIAMOND_BLOCK(200),
    EMERALD_BLOCK(300);

    private int experience;

    ExperienceManager(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }
    public static int getExperience(Material material) {
        switch (material) {
            case STONE:
                return ExperienceManager.STONE.getExperience();
            case COAL_ORE:
                return ExperienceManager.COAL_ORE.getExperience();
            case IRON_ORE:
                return ExperienceManager.IRON_ORE.getExperience();
            case GOLD_ORE:
                return ExperienceManager.GOLD_ORE.getExperience();
            case DIAMOND_ORE:
                return ExperienceManager.DIAMOND_ORE.getExperience();
            case EMERALD_ORE:
                return ExperienceManager.EMERALD_ORE.getExperience();
            case IRON_BLOCK:
                return ExperienceManager.IRON_BLOCK.getExperience();
            case GOLD_BLOCK:
                return ExperienceManager.GOLD_BLOCK.getExperience();
            case DIAMOND_BLOCK:
                return ExperienceManager.DIAMOND_BLOCK.getExperience();
            case EMERALD_BLOCK:
                return ExperienceManager.EMERALD_BLOCK.getExperience();
            default:
                return 0;
        }
    }

    public static void increaseExperience(Player player, int xp, NBTManager manager) {

        manager.setNBT("xp", PersistentDataType.INTEGER, manager.getNBT("xp", PersistentDataType.INTEGER) + xp);

        if(hasEnoughExperience(manager.getNBT("level", PersistentDataType.INTEGER), manager.getNBT("xp", PersistentDataType.INTEGER))) {

            int level = manager.getNBT("level", PersistentDataType.INTEGER);
            manager.setNBT("level", PersistentDataType.INTEGER, level + 1);
            manager.setNBT("xp", PersistentDataType.INTEGER, 0);

            MongoHandler.setValue(player, "level", level + 1);
            player.sendMessage("you levelled up");

        }

    }



    public static boolean hasEnoughExperience(int level, int pickaxeExperience) {
        switch (level) {
            case 1:
                return pickaxeExperience >= 500;
            case 2:
                return pickaxeExperience >= 1000;
            case 3:
                return pickaxeExperience >= 2500;
            case 4:
                return pickaxeExperience >= 5000;
            case 5:
                return pickaxeExperience >= 10000;
            default:
                return false;
        }
    }








}
