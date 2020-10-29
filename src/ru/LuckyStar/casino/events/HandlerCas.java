package ru.LuckyStar.casino.events;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.LuckyStar.main.Main;

import java.text.DecimalFormat;

public class HandlerCas implements Listener {
    private boolean isNotPlaying_0 = true;//17 15 17
    private boolean isNotPlaying_1 = true;
    private boolean isNotPlaying_2 = true;
    private boolean isNotPlaying_3 = true;
    private boolean isNotPlaying_4 = true;
    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws Exception {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType().equals(Material.NOTE_BLOCK)) {
                double balance, mincost, win, cost;
                Player player = event.getPlayer();
                Location location_block = event.getClickedBlock().getLocation();
                String xyz = location_block.getBlockX() + "|" + location_block.getBlockY() + "|" + location_block.getBlockZ();
                switch (xyz) {
                    case "17|75|17":
                        if (isNotPlaying_0) {
                            balance = Main.getEconomy().getBalance(event.getPlayer());
                            mincost = 500;
                            if (balance >= mincost) {
                                if (balance > 1250000) {
                                    event.getPlayer().sendMessage(Main.prefix + " §cСлишком много денег! §eМаксимум §2§l1.250.000$");
                                    event.setCancelled(true);
                                    return;
                                }
                                cost = balance;
                                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> isNotPlaying_0 = true, 120L);
                                win = 2;
                                isNotPlaying_0 = false;
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            event.getPlayer().sendMessage(Main.prefix + " §cУже кто-то играет на этом автомате!");
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case "9|74|23":
                        if (isNotPlaying_1) {
                            balance = Main.getEconomy().getBalance(event.getPlayer());
                            mincost = 100;
                            if (balance >= mincost) {
                                cost = 100;
                                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> isNotPlaying_1 = true, 120L);
                                win = 1;
                                isNotPlaying_1 = false;
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            event.getPlayer().sendMessage(Main.prefix + " §cУже кто-то играет на этом автомате!");
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case "9|74|19":
                        if (isNotPlaying_2) {
                            balance = Main.getEconomy().getBalance(event.getPlayer());
                            mincost = 500;
                            if (balance >= mincost) {
                                cost = 500;
                                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> isNotPlaying_2 = true, 120L);
                                win = 1;
                                isNotPlaying_2 = false;
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            event.getPlayer().sendMessage(Main.prefix + " §cУже кто-то играет на этом автомате!");
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case "19|74|10":
                        if (isNotPlaying_3) {
                            balance = Main.getEconomy().getBalance(event.getPlayer());
                            mincost = 1000;
                            if (balance >= mincost) {
                                cost = 1000;
                                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> isNotPlaying_3 = true, 120L);
                                win = 1;
                                isNotPlaying_3 = false;
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            event.getPlayer().sendMessage(Main.prefix + " §cУже кто-то играет на этом автомате!");
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    case "23|74|10":
                        if (isNotPlaying_4) {
                            balance = Main.getEconomy().getBalance(event.getPlayer());
                            mincost = 5000;
                            if (balance >= mincost) {
                                cost = 5000;
                                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> isNotPlaying_4 = true, 120L);
                                win = 1;
                                isNotPlaying_4 = false;
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            event.getPlayer().sendMessage(Main.prefix + " §cУже кто-то играет на этом автомате!");
                            event.setCancelled(true);
                            return;
                        }
                        break;
                    default:
                        return;
                }
                if (balance >= mincost) {

                    Main.getEconomy().withdrawPlayer(player, cost);
                    int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () ->
                        manySoundsNEffects(player, event.getClickedBlock().getLocation().add(0.5, 1, 0.5)), 2L, 2L);
                    Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                        Bukkit.getScheduler().cancelTask(id);
                        double random = Math.random();
                        if (random <= 0.70) {
                            if (win == 2) {
                                DecimalFormat f = new DecimalFormat("##.00");
                                player.sendMessage(Main.prefix + " §a§lВы выиграли: §2" + f.format(win * balance) + "§l$§a§l! c;");
                                try { Main.getEconomy().depositPlayer(event.getPlayer(), Double.parseDouble(f.format(balance * 2))); } catch (Exception ignored) {}
                                if (balance > 30000) {
                                    Bukkit.getWorld("world").spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
                                }
                            } else {
                                DecimalFormat f = new DecimalFormat("##.00");
                                player.sendMessage(Main.prefix + " §a§lВы выиграли: §2" + f.format(cost + random * cost) + "§l$§a§l! :)");
                                try { Main.getEconomy().depositPlayer(event.getPlayer(), Double.parseDouble(f.format(cost + random * cost))); } catch (Exception ignored) {}
                            }
                        } else {
                            if (win == 2) {
                                event.getPlayer().sendMessage(Main.prefix + " §c§lВы проиграли в казино все деньги §e§l:с");
                            } else {
                                DecimalFormat f = new DecimalFormat("##.00");
                                player.sendMessage(Main.prefix + " §e§lВы получили: §2" + f.format(cost - random * cost) + "§l$§e§l! §8(§c-" + f.format(cost - (cost - random * cost))  + "§8) §c§l:(");
                                try { Main.getEconomy().depositPlayer(event.getPlayer(), Double.parseDouble(f.format(cost - random * cost))); } catch (Exception ignored) {}
                            }
                        }
                    }, 119L);
                } else {
                    event.getPlayer().sendMessage(Main.prefix + " §cНедостаточно денег! §eМинимум §2§l" + mincost + "$");
                    event.setCancelled(true);
                }
            }
        }
    }

    private void manySoundsNEffects(Player player, Location block) {
        if (player.isOnline()) {
            player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 0.125f, (float) Math.random());
            Bukkit.getWorld("world").playEffect(block, Effect.MAGIC_CRIT, 150, 4);
        }
    }
}
