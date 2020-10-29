package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;

import java.time.LocalDateTime;

public class unbanfull implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("funban")) {
            if (args.length >= 2) {
                String reason = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ").replaceFirst(args[0] + " ", ""));
                boolean plIsBanned = fullban.fullBanList_names.contains(args[0]) || fullbanip.fullBanList_ips.contains(args[0]);
                if (!plIsBanned) {
                    sender.sendMessage(Main.prefix + " §cИгрок/IP не в бане.");
                } else {
                    Bukkit.getServer().broadcastMessage(Main.prefix + " §4Администратор §l" + sender.getName() + " §4разбанил §l" + args[0] + " §4по причине:§l " + reason);
                    if (fullban.fullBanList_names.contains(args[0])) {
                        fullban.fullBanList_names.remove(args[0]);
                    }
                    if (fullbanip.fullBanList_ips.contains(args[0])) {
                        fullbanip.fullBanList_ips.remove(args[0]);
                    }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/funban §l[ник|IP] [причина]", Main.prefix + " §fПричину указывать §lобязательно§f.", Main.prefix + " §fРазбанивает как обычный, так и IP бан."});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
