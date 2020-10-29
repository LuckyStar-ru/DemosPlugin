package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class fullban implements CommandExecutor {

    public static ArrayList<String> fullBanList_names = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("fban")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsBanned = fullBanList_names.contains(args[0]);
                if (plIsBanned) {
                    sender.sendMessage(Main.prefix + " §cИгрок уже в бане.");
                } else {
                    Bukkit.getServer().broadcastMessage(Main.prefix + " §4Администратор §l" + sender.getName() + " §4забанил навсегда игрока §l" + args[0] + " §4по причине:§l " + reason);
                    fullBanList_names.add(args[0]);
                }
                Player plToBan = Bukkit.getPlayer(args[0]);
                if (plToBan != null) {
                    if (plToBan.getName().equals(args[0])) {
                        plToBan.kickPlayer(Main.prefix + " §fВас забанил §l" + sender.getName() + "§f по причине:§l " + reason);
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/fban §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
