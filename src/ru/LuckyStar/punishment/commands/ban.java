package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.cooldowns.Cooldown;
import ru.LuckyStar.main.Main;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class ban implements CommandExecutor {

    public static HashMap<String, String> banList = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("ban")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsBanned = banList.get(args[0]) != null;
                Cooldown cooldown = Cooldown.getCD(sender.getName(), 'b');
                if (args[0].equalsIgnoreCase("LuckyStar")) {
                    sender.sendMessage(Main.prefix + " §cНевозможно использовать на администратора.");
                } else if (plIsBanned) {
                    sender.sendMessage(Main.prefix + " §cИгрок уже в бане.");
                } else if (cooldown != null && !sender.hasPermission("ban.breakcd")) {
                    if (Math.random() > 0.5) {
                        sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                    } else {
                        sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                    }
                } else {
                    Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fзабанил игрока §l" + args[0] + " §fпо причине:§l " + reason);
                    history.history.add(LocalDateTime.now() + "*ban*" + sender.getName() + "*" + args[0] + "*" + reason);
                    banList.put(args[0], sender.getName() + "*" + reason);
                    Cooldown.putCD(new Cooldown(sender.getName(), 'b', LocalTime.now().plusMinutes(15)));
                    Player plToBan = Bukkit.getPlayer(args[0]);
                    if (plToBan != null) {
                        if (plToBan.getName().equals(args[0])) {
                            plToBan.kickPlayer(Main.prefix + " §fВас забанил §l" + sender.getName() + "§f по причине:§l " + reason);
                        }
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/ban §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f.", Main.prefix + " §fВы обязаны иметь доказательства на каждый бан."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
