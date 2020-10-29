package ru.LuckyStar.automine;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;
import java.util.List;

public class HandlerMine implements Listener {

    List<Material> materials = new ArrayList<Material>() {{
        add(Material.COAL_ORE);
        add(Material.COAL_ORE);
        add(Material.DIAMOND_ORE);
        add(Material.GOLD_ORE);
        add(Material.GOLD_ORE);
        add(Material.EMERALD_ORE);
        add(Material.GLOWING_REDSTONE_ORE);
        add(Material.GLOWING_REDSTONE_ORE);
        add(Material.IRON_ORE);
        add(Material.IRON_ORE);
    }};

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (materials.contains(event.getBlock().getType())) {
            ProtectedRegion region = Main.getRegion(event.getBlock().getLocation());
            if (region == null) return;
            if (region.getId().equals("spawn")) {
                Item item = null;
                switch (event.getBlock().getType()) {
                    case IRON_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                        break;
                    case GOLD_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                        break;
                    case DIAMOND_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
                        break;
                    case EMERALD_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.EMERALD));
                        break;
                    case GLOWING_REDSTONE_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.REDSTONE_BLOCK));
                        break;
                case COAL_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.COAL_BLOCK));
                        break;
                case LAPIS_ORE:
                        event.setDropItems(false);
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.LAPIS_BLOCK));
                        break;
                    default:
                        item = Bukkit.getWorld("world").dropItem(event.getBlock().getLocation(), new ItemStack(Material.COBBLESTONE));
                }
                item.setPickupDelay(0);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, (float) Math.random());
                event.getPlayer().giveExp(3);
                event.getBlock().setType(Material.AIR);
                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> event.getBlock().setType(Material.STONE), 12L);
                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> event.getBlock().setType(materials.get((int) (Math.random() * 10))), 300L);
            }
        }
    }

}
