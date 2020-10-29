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

public class kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("kick")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));

                Player plToKick = Bukkit.getPlayer(args[0]);
                if (args[0].equalsIgnoreCase("LuckyStar")) {
                    sender.sendMessage(Main.prefix + " §cНевозможно использовать на администратора.");
                } else if (plToKick != null) {
                    Cooldown cooldown = Cooldown.getCD(sender.getName(), 'k');
                    if (cooldown == null || sender.hasPermission("kick.breakcd")) {
                        plToKick.kickPlayer(Main.prefix + " §fВас кикнул §l" + sender.getName() + "§f по причине:§l " + reason);
                        Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fкикнул игрока §l" + args[0] + " §fпо причине:§l " + reason);
                        history.history.add(LocalDateTime.now() + "*kick*" + sender.getName() + "*" + args[0] + "*" + reason);
                        Cooldown.putCD(new Cooldown(sender.getName(), 'k', LocalTime.now().plusMinutes(5)));
                    } else {
                        if (Math.random() > 0.5) {
                            sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        } else {
                            sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        }
                    }
                } else  {
                    sender.sendMessage(Main.prefix + " §cИгрока нет на сервере!");
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/kick §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f.", Main.prefix + " §fВы обязаны иметь доказательства на каждый кик."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
