package me.christo.surgepickaxes.Handlers;

import me.christo.surgepickaxes.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class NBTManager {
    private ItemStack item;
    private ItemMeta meta;

    public NBTManager(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public void setNBT(String keyString, PersistentDataType type, Object value) {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), keyString);
        meta.getPersistentDataContainer().set(key, type, value);
        item.setItemMeta(meta);
    }

    public <T> T getNBT(String keyString, PersistentDataType<T, T> type) {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), keyString);
        return meta.getPersistentDataContainer().get(key, type);
    }

    public void updateNBT(String keyString, PersistentDataType type, Object value) {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), keyString);
        if (meta.getPersistentDataContainer().has(key, type)) {
            setNBT(keyString, type, value);
        }
    }
}

