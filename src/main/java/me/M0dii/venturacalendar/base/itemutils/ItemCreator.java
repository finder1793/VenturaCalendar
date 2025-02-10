package me.m0dii.venturacalendar.base.itemutils;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class ItemCreator {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemCreator(Material material, int amount, String name, List<String> lore) {
        if (material == null) {
            material = XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial();
        }

        if (material == null) {
            material = Material.GLASS_PANE;
        }

        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();

        // Check if the name contains custom model data indicator
        if (name.contains("#")) {
            String[] split = name.split("#");
            name = split[0];

            try {
                // Set custom model data from the value after #
                meta.setCustomModelData(Integer.parseInt(split[1]));
            } catch (NumberFormatException ignored) { }
        }

        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public ItemCreator(Material material, int amount, String name, List<String> lore, String skullOwner) {
        if (material == null) {
            material = XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial();
        }

        if (material == null) {
            material = Material.GLASS_PANE;
        }

        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();

        // Check if the name contains custom model data indicator
        if (name.contains("#")) {
            String[] split = name.split("#");
            name = split[0];

            try {
                // Set custom model data from the value after #
                meta.setCustomModelData(Integer.parseInt(split[1]));
            } catch (NumberFormatException ignored) { }
        }

        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        if (material.equals(XMaterial.PLAYER_HEAD.parseMaterial())) {
            SkullMeta skullMeta = (SkullMeta) meta;

            UUID uuid = UUID.fromString(skullOwner);

            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));

            item.setItemMeta(skullMeta);
        }
    }

    public ItemStack getItem() {
        return item;
    }
}
