package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.cooldowns.Cooldown;
import ru.LuckyStar.main.Main;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class unban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("unban")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsBanned = ban.banList.get(args[0]) != null || tempban.tempbanList.get(args[0]) != null;
                if (!plIsBanned) {
                    sender.sendMessage(Main.prefix + " §cИгрок не в бане.");
                } else {
                    Cooldown cooldown = Cooldown.getCD(sender.getName(), 'u');
                    if (cooldown == null || sender.hasPermission("unban.breakcd")) {
                        Cooldown.putCD(new Cooldown(sender.getName(), 'u', LocalTime.now().plusMinutes(20)));
                        Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fразбанил игрока §l" + args[0] + " §fпо причине:§l " + reason);
                        history.history.add(LocalDateTime.now() + "*unban*" + sender.getName() + "*" + args[0] + "*" + reason);
                        if (ban.banList.get(args[0]) != null) {
                            ban.banList.remove(args[0]);
                        }
                        if (tempban.tempbanList.get(args[0]) != null) {
                            tempban.tempbanList.remove(args[0]);
                        }
                    } else {
                        if (Math.random() > 0.5) {
                            sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        } else {
                            sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        }
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/unban §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f.", Main.prefix + " §fРазбанивает как обычный, так и временный бан."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
