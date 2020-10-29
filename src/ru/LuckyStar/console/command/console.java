package ru.LuckyStar.console.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class console implements CommandExecutor {
    public static ArrayList<Player> receiver = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("console")) {
            if (args.length == 1) {
                Player pl = Bukkit.getPlayer(sender.getName());
                switch (args[0]) {
                    case "on":
                        if (!receiver.contains(pl)) {
                            receiver.add(pl);
                        }
                        sender.sendMessage(Main.prefix + " §fРежим слежки включен!");
                        break;
                    case "off":
                        receiver.remove(pl);
                        sender.sendMessage(Main.prefix + " §fРежим слежки выключен!");
                        break;
                    case "toggle":
                        if (receiver.contains(pl)) {
                            receiver.remove(pl);
                            sender.sendMessage(Main.prefix + " §fРежим слежки выключен!");
                        } else {
                            receiver.add(pl);
                            sender.sendMessage(Main.prefix + " §fРежим слежки включен!");
                        }
                        break;
                    default:
                        sender.sendMessage(Main.prefix + " §cНеизвестный аргумент.");
                        sender.sendMessage(Main.prefix + " §fИспользование команды:§e /console §l[on / off]");
                        break;
                }
            } else {
                sender.sendMessage(Main.prefix + " §fИспользование команды:§e /console §l[on / off]");
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
