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

public class unmute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("unmute")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsMuted = mute.muteList.get(args[0]) == null;
                if (plIsMuted) {
                    sender.sendMessage(Main.prefix + " §cУ игрока не заблокирован чат.");
                } else {
                    Cooldown cooldown = Cooldown.getCD(sender.getName(), 'q');
                    if (cooldown == null || sender.hasPermission("unmute.breakcd")) {
                        Cooldown.putCD(new Cooldown(sender.getName(), 'q', LocalTime.now().plusMinutes(15)));
                        Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fразблокировал чат игроку §l" + args[0] + " §fпо причине:§l " + reason);
                        history.history.add(LocalDateTime.now() + "*unmute*" + sender.getName() + "*" + args[0] + "*" + reason);
                        mute.muteList.remove(args[0]);
                    } else {
                        if (Math.random() > 0.5) {
                            sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        } else {
                            sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        }
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/unmute §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
