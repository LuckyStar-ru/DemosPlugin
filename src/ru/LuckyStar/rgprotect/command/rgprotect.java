package ru.LuckyStar.rgprotect.command;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;
import ru.LuckyStar.rgprotect.event.HandlerRG;

public class rgprotect implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("rgprotect.protect")) {
            Player player = Bukkit.getPlayer(sender.getName());
            if (args.length != 1) {
                sender.sendMessage(new String[] {Main.prefix + " §fИспользование команды: §6/rgprotect §l[регион]", Main.prefix + " §fКоманду, по правилам, разрешено использовать только на §l§oСВОИ§f регионы.", Main.prefix + " §c§lЗлоупотребление командой грозит баном."});
            } else {
                ProtectedRegion region = Main.getWG().getRegionManager(player.getWorld()).getRegion(args[0]);
                if (region == null) {
                    sender.sendMessage(Main.prefix + " §cРегион не найден!");
                } else {
                    if (!HandlerRG.protectedrg.contains(region.getId())) {
                        HandlerRG.protectedrg.add(region.getId());
                    }
                    sender.sendMessage(Main.prefix + " §fУстановлена защита на регион §l" + region.getId());
                }
            }

            if(args.length == 2) {
                if (args[0].equals("UnProTecT") && sender.hasPermission("rgprotect.unprotect")) {
                    HandlerRG.protectedrg.remove(args[1]);
                }
            }
        } else {
            sender.sendMessage(Main.prefix + " §cНедостаточно прав!");
        }
        return true;
    }
}
