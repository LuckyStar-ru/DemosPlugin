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
import java.util.HashMap;

public class mute implements CommandExecutor {
    public static HashMap<String, String> muteList = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("mute")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsMuted = muteList.get(args[0]) != null;
                if (args[0].equalsIgnoreCase("LuckyStar")) {
                    sender.sendMessage(Main.prefix + " §cНевозможно использовать на администратора.");
                } else if (plIsMuted) {
                    sender.sendMessage(Main.prefix + " §cУ игрока уже заблокирован чат.");
                } else {
                    Cooldown cooldown = Cooldown.getCD(sender.getName(), 'm');
                    if (cooldown == null || sender.hasPermission("mute.breakcd")) {
                        Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fзаблокировал чат §8(на 1 час)§f игроку §l" + args[0] + " §fпо причине:§l " + reason);
                        history.history.add(LocalDateTime.now() + "*mute*" + sender.getName() + "*" + args[0] + "*" + reason);
                        muteList.put(args[0], sender.getName() + "*" + reason + "*" + LocalDateTime.now().plusMinutes(60) + "*" + args[0]);
                        Cooldown.putCD(new Cooldown(sender.getName(), 'm', LocalTime.now().plusMinutes(15)));
                    } else {
                        if (Math.random() > 0.5) {
                            sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        } else {
                            sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        }
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/mute §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f. Время блокировки чата - §l1 §fчас.", Main.prefix + " §fВы обязаны иметь доказательства на каждую блокировку чата."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
