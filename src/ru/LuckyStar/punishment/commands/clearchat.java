package ru.LuckyStar.punishment.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;

public class clearchat implements CommandExecutor {
    boolean canclear = true;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("clearchat")) {
            if (args.length != 1) {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/cc §l[причина]", Main.prefix + " §fПричину указывать §lобязательно§f.", Main.prefix + " §fВы обязаны иметь доказательства на причину очистки чата."});
                return true;
            }
            if (canclear) {
                canclear = false;
                for (int i = 0; i < 32; i++) {
                    Bukkit.getServer().broadcastMessage(Main.prefix + " §8Очистка чата §7§o(" + i + "/31)");
                }
                Bukkit.getServer().broadcastMessage(Main.prefix + " §fЧат успешно очищен игроком §l" + sender.getName() + "§f по причине:§l " + StringUtils.join(args, " "));
                Bukkit.getScheduler().runTaskLater(Main.plugin, () -> canclear = true, 120L);
            } else {
                sender.sendMessage(Main.prefix + " §cЧат разрешено очищать раз в 6 секунд!");
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
