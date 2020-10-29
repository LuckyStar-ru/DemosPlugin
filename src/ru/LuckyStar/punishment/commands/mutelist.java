package ru.LuckyStar.punishment.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;

import java.time.Duration;
import java.time.LocalDateTime;

public class mutelist implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("mutelist")) {
            sender.sendMessage(Main.prefix + " §a[Длительность мута] §fЗаблокированный §k§l|§f Инициатор §k§l|§f Причина");
            if (mute.muteList.isEmpty()) {
                sender.sendMessage(Main.prefix + " §cСписок пуст.");
            } else {
                for (String i : mute.muteList.values()) {
                    String[] info = i.split("\\*");
                    long time = Duration.between(LocalDateTime.now(), LocalDateTime.parse(info[2])).toMinutes();
                    if (time < 0) {
                        mute.muteList.remove(info[3]);
                        continue;
                    }
                    sender.sendMessage(Main.prefix + " §a[" + time + " мин] §f" + info[3] + " §k§l|§f " + info[0] + " §k§l|§f " + info[1]);
                }
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
