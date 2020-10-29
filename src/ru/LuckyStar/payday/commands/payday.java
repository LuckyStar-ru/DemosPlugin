package ru.LuckyStar.payday.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;
import ru.LuckyStar.payday.time.timer;

import java.util.ArrayList;

public class payday implements CommandExecutor {
    ArrayList<String> alreadyUse = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("payday")) {
            if (!alreadyUse.contains(sender.getName())) {
                timer.payday(false, Bukkit.getPlayer(sender.getName()), 1);
                alreadyUse.add(sender.getName());
            } else {
                sender.sendMessage(Main.prefix + " §cВы уже использовали сегодня эту команду.");
            }
        } else {
            sender.sendMessage(Main.prefix + " §cНедостаточно прав.");
        }
        return true;
    }
}
