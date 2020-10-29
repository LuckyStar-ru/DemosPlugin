package ru.LuckyStar.bonus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class commands implements CommandExecutor {

    public static ArrayList<String> usesIPs = new ArrayList<>();
    public static ArrayList<String> usesNames = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        if (!usesNames.contains(player.getName()) && !usesIPs.contains(player.getAddress().getAddress().toString())) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " group add creative * 172800");
            usesIPs.add(player.getAddress().getAddress().toString());
            usesNames.add(player.getName());
            sender.sendMessage(Main.prefix + " §a§lВы получили креатив на 2 дня.");
        } else {
            sender.sendMessage(Main.prefix + " §c§lВы уже использовали бонус.");
        }
        return true;
    }
}
