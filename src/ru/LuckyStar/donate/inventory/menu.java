package ru.LuckyStar.donate.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class menu {
    List<String> newlore = new ArrayList<>();

    public Inventory getItems(String name) {
        Inventory I = Bukkit.createInventory(Bukkit.getPlayer(name), 18, ("&4&lЗадания &f&k&l||&e&l WilfadCraft").replace("&","\u00a7"));
        ItemStack one = new ItemStack(Material.FIREWORK);
        ItemStack two = new ItemStack(Material.STORAGE_MINECART);
        ItemStack three = new ItemStack(Material.EXPLOSIVE_MINECART);
        ItemStack four = new ItemStack(Material.AIR);
        ItemStack five = new ItemStack(Material.AIR);
        ItemStack six = new ItemStack(Material.AIR);
        ItemStack seven = new ItemStack(Material.COAL_BLOCK);
        ItemStack eight = new ItemStack(Material.AIR);
        ItemStack nine = new ItemStack(Material.PAPER);

        //Edit the Items
        //first
        ItemMeta one_meta = one.getItemMeta();
        one_meta.setDisplayName(("&a&lПройдено!").replace("&","\u00a7"));
        newlore.add(("&a640&f/&a&l640&f&l блоков сломано!").replace("&", "\u00a7"));
        newlore.add("");
        newlore.add(("&a&lПоздравляю!").replace("&", "\u00a7"));
        one_meta.setLore(newlore);
        one.setItemMeta(one_meta);
        newlore.clear();

        //Put the items in the inventory
        ItemStack[] menu_items = {one, two, three, four, five, six, seven, eight, nine};
        I.setContents(menu_items);
        return I;
    }
}
