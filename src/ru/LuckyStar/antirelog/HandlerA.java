package ru.LuckyStar.antirelog;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.LuckyStar.main.Main;

import java.util.HashMap;

public class HandlerA implements Listener {

    HashMap<String, Integer> inBattle = new HashMap<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        int id;
        Object d = event.getDamager();
        if(d instanceof Arrow) {
            d = ((Arrow)d).getShooter();
        } else if(d instanceof ThrownPotion) {
            d = ((ThrownPotion)d).getShooter();
        }
        if ((d instanceof Player && event.getEntity() instanceof Player)) {
            Player damager = (Player) d;
            Player entity = (Player) event.getEntity();
            if (damager.getName().equals(entity.getName())) {
                return;
            }
            ApplicableRegionSet set = Main.getWG().getRegionManager(damager.getWorld()).getApplicableRegions(damager.getLocation());
            if(set.getFlag(DefaultFlag.PVP) == StateFlag.State.DENY) {
                return;
            }
            if (entity.getGameMode().equals(GameMode.SURVIVAL)) {
                if (damager.getGameMode().equals(GameMode.CREATIVE)) {
                    damager.setGameMode(GameMode.SURVIVAL);
                }
                if (inBattle.get(damager.getName()) != null) {
                    Bukkit.getScheduler().cancelTask(inBattle.get(damager.getName()));
                } else {
                    damager.sendMessage(Main.prefix + " §fВы вступили в битву с §l" + entity.getName() + "§f!");
                    damager.sendMessage(Main.prefix + " §c§lОсторожно: 15 секунд блокировки команд; при выходе с сервера Вы потеряете все вещи!");
                }
                id = Bukkit.getScheduler().runTaskLater(Main.plugin, () -> inBattle.remove(damager.getName()), 300L).getTaskId();
                inBattle.put(damager.getName(), id);

                if (inBattle.get(entity.getName()) != null) {
                    Bukkit.getScheduler().cancelTask(inBattle.get(entity.getName()));
                } else {
                    entity.sendMessage(Main.prefix + " §fВы вступили в битву с §l" + damager.getName() + "§f!");
                    entity.sendMessage(Main.prefix + " §c§lОсторожно: 15 секунд блокировки команд; при выходе с сервера Вы потеряете все вещи!");
                }
                id = Bukkit.getScheduler().runTaskLater(Main.plugin, () -> inBattle.remove(entity.getName()), 300L).getTaskId();
                inBattle.put(entity.getName(), id);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (inBattle.containsKey(event.getPlayer().getName())) {
            event.getPlayer().setHealth(0);
        }
    }

    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent event) {
        if (inBattle.containsKey(event.getPlayer().getName()) && !event.getMessage().equalsIgnoreCase("/ec")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Main.prefix + " §cИспользовать команды во время битвы запрещено! §7(кроме §o/ec§7)");
        }
    }
}
