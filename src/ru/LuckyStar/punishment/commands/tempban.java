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
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;

public class tempban implements CommandExecutor {
    public static HashMap<String, String> tempbanList = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("tempban")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsTempbanned = tempbanList.get(args[0]) != null;
                if (plIsTempbanned) {
                    sender.sendMessage(Main.prefix + " §cИгрок уже временно заблокирован.");
                } else {
                    Cooldown cooldown = Cooldown.getCD(sender.getName(), 't');
                    if (cooldown == null) {
                        Bukkit.getServer().broadcastMessage(Main.prefix + " §fИгрок §l" + sender.getName() + " §fвременно §8(на 30 минут)§f забанил игрока §l" + args[0] + " §fпо причине:§l " + reason);
                        history.history.add(LocalDateTime.now() + "*tempban*" + sender.getName() + "*" + args[0] + "*" + reason);
                        tempbanList.put(args[0], sender.getName() + "*" + reason + "*" + LocalDateTime.now().plusMinutes(30) + "*" + args[0]);
                        Player plToBan = Bukkit.getPlayer(args[0]);
                        if (plToBan != null) {
                            if (plToBan.getName().equals(args[0])) {
                                plToBan.kickPlayer(Main.prefix + " §fВас временно забанил игрок §l" + sender.getName() + "\n§fпо причине:§l " + reason + "\n§cОсталось §l30§c минут");
                            }
                        }
                        Cooldown.putCD(new Cooldown(sender.getName(), 't', LocalTime.now().plusMinutes(10)));
                    } else {
                        if (Math.random() > 0.5) {
                            sender.sendMessage(Main.prefix + " §cПомедленнее! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        } else {
                            sender.sendMessage(Main.prefix + " §cНе торопитесь! Вы можете использовать эту команду только через §l" + Cooldown.getBeetweenTime(cooldown.getTime()) + "§c сек.");
                        }
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/tempban §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f. Время бана - §l30§f минут.", Main.prefix + " §fВы обязаны иметь доказательства на каждую блокировку чата."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
