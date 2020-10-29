package ru.LuckyStar.blocker;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.world.WorldLoadEvent;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class HandlerBl implements Listener {

    @EventHandler
    public void BlockCMD(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/ver") || event.getMessage().startsWith("/auth") || event.getMessage().startsWith("/sync") || event.getMessage().startsWith("/plug") || event.getMessage().startsWith("/op") || event.getMessage().startsWith("/pl") || event.getMessage().startsWith("/help") || event.getMessage().startsWith("/worldedit") || event.getMessage().contains(":") || event.getMessage().startsWith("/;")) {
            if (!event.getPlayer().getName().equals("LuckyStar")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Main.prefix + " §cКоманда запрещена. Если вы уверены в том, что это ошибка, сообщите в группу ВК.");
            }
        }
    }

    @EventHandler
    public void BlockBedrock(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.BEDROCK)) {
            event.getPlayer().sendMessage(Main.prefix + " §cНевозможно сломать бедрок.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlaceBedrock(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.BEDROCK) || event.getBlock().getType().equals(Material.BARRIER)) {
            event.getPlayer().sendMessage(Main.prefix + " §cЭтот блок запрещен.");
            event.setCancelled(true);
        } else if (event.getBlock().getType().equals(Material.OBSIDIAN) && event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.getPlayer().sendMessage(Main.prefix + " §cЭтот блок запрещён для размещения в креативе. Попробуйте в режиме выживания.");
            event.setCancelled(true);
        }
    }

    public static ArrayList<String> buketCD = new ArrayList<>();
    @EventHandler
    public void BucketPlace(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().hasPermission("bypass.lavagm") && event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            if (!buketCD.contains(event.getPlayer().getName())) {
                buketCD.add(event.getPlayer().getName());
                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> buketCD.remove(event.getPlayer().getName()), 600L);
            } else {
                event.getPlayer().sendMessage(Main.prefix + " §cРазливать любую жидкость в креативе можно только раз в 30 секунд.");
                event.setCancelled(true);
            }
        }
        if (!event.getPlayer().hasPermission("bypass.lavasurv") && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            if (!buketCD.contains(event.getPlayer().getName())) {
                buketCD.add(event.getPlayer().getName());
                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> buketCD.remove(event.getPlayer().getName()), 120L);
            } else {
                event.getPlayer().sendMessage(Main.prefix + " §cРазливать любую жидкость в режиме выживания можно только раз в 6 секунд.");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        event.getWorld().setGameRuleValue("announceAdvancements", "false");
    }
}
