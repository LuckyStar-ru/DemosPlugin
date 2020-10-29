package ru.LuckyStar.punishment.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class history implements CommandExecutor {
    public static ArrayList<String> history = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("history")) {
            if (history.isEmpty()) {
                sender.sendMessage(Main.prefix + " §cИстория пустая.");
                return true;
            }
            sender.sendMessage(Main.prefix + " §fИстория:");
            if (args.length == 0) {
                for (String i : history) {
                    String[] info = i.split("\\*");
                    long time = Duration.between(LocalDateTime.parse(info[0]), LocalDateTime.now()).toMinutes();
                    if (time > 60) break;
                    sender.sendMessage(message(info, time));
                }
            } else {
                for (String i : history) {
                    if (i.toLowerCase().contains(args[0].toLowerCase())) {
                        String[] info = i.split("\\*");
                        long time = Duration.between(LocalDateTime.parse(info[0]), LocalDateTime.now()).toMinutes();
                        sender.sendMessage(message(info, time));
                    }
                }
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }

    private String message(String[] info, long time) {
        String historyline = "§a§l" + time + " мин назад§8:\n ";
        switch (info[1]) {
            case "ban":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f забанил игрока §l" + info[3] + " §fпо причине: " + info[4]);
                break;
            case "kick":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f кикнул игрока §l" + info[3] + " §fпо причине: " + info[4]);
                break;
            case "unban":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f разбанил игрока §l" + info[3] + " §fпо причине: " + info[4]);
                break;
            case "tempban":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f временно забанил игрока §l" + info[3] + " §fпо причине: " + info[4]);
                break;
            case "mute":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f заблокировал чат игроку §l" + info[3] + " §fпо причине: " + info[4]);
                break;
            case "unmute":
                historyline = historyline.concat("§fИгрок §l" + info[2] + "§f разблокировал чат игроку §l" + info[3] + " §fпо причине: " + info[4]);
                break;
        }
        return historyline;
    }
}
