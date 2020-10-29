package ru.LuckyStar.admin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.admin.events.Events;
import ru.LuckyStar.main.Main;

public class invisible implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("admin.hide")) {
            Player player = Bukkit.getPlayer(sender.getName());
            if (Events.vanished.contains(player)) {
                Events.vanished.remove(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.showPlayer(Main.plugin, player);
                }
                sender.sendMessage(Main.prefix + " §fВы вышли из режима невидимости.");
            } else {
                Events.vanished.add(player);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.hidePlayer(Main.plugin, player);
                }
                sender.sendMessage(Main.prefix + " §fВы вошли в режим невидимости.");
            }
        } else {
            sender.sendMessage(Main.prefix + "§cНедостаточно прав.");
        }
        return true;
    }

}
