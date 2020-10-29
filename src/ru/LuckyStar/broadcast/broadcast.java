package ru.LuckyStar.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.cooldowns.Cooldown;
import ru.LuckyStar.main.Main;

import java.time.LocalTime;

public class broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("broadcast")) {
            if (args.length == 0) {
                sender.sendMessage(Main.prefix + " §fИспользование команды: §6/bc §l[сообщение]");
            } else {
                Cooldown cooldown = Cooldown.getCD(sender.getName(), 'c');
                if (cooldown == null) {
                    Bukkit.broadcastMessage(Main.prefix + " " + ChatColor.translateAlternateColorCodes('&', String.join("", args)).replace("§k", "") + " §8(§7Объявил §a" + sender.getName() + "§8)");
                    Cooldown.putCD(new Cooldown(sender.getName(), 'c', LocalTime.now().plusMinutes(3)));
                } else {
                    if (Math.random() > 0.5) {
                        sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                    } else {
                        sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                    }
                }
            }
        } else {
            sender.sendMessage(Main.prefix + " §cНедостаточно прав.");
        }
        return true;
    }
}
