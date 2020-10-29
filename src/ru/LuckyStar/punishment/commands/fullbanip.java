package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class fullbanip implements CommandExecutor {

    public static ArrayList<String> fullBanList_ips = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("fbanip")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsBanned = fullBanList_ips.contains(args[0]);
                if (plIsBanned) {
                    sender.sendMessage(Main.prefix + " §cIP уже в бане.");
                } else {
                    Bukkit.getServer().broadcastMessage(Main.prefix + " §4Администратор §l" + sender.getName() + " §4заблокировал IP §l" + args[0] + " §4по причине:§l " + reason);
                    fullBanList_ips.add(args[0]);
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/fbanip §l[ник] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
