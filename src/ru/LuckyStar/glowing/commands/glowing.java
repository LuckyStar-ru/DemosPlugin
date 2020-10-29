package ru.LuckyStar.glowing.commands;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.api.INametagApi;
import com.nametagedit.plugin.api.data.Nametag;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class glowing implements CommandExecutor {

    ArrayList<String> name_colors = new ArrayList<>(Arrays.asList("aqua", "black", "blue", "dark_aqua", "dark_blue", "dark_gray",
            "dark_green", "dark_purple", "dark_red", "gold", "gray", "green", "red", "white", "yellow"));
    ArrayList<String> prefix_colors = new ArrayList<>(Arrays.asList("§b", "§0", "§9", "§3", "§1", "§8", "§2", "§5", "§4", "§6", "§7", "§a", "§c", "§f", "§e"));
    HashMap<String, Integer> rainbow = new HashMap<>();
    HashMap<String, Integer> task = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("glowing")) {
            if (args.length == 1) {
                Player player = Bukkit.getPlayer(sender.getName());
                INametagApi nametagApi = NametagEdit.getApi();
                Nametag plinfo = NametagEdit.getApi().getNametag(player);
                nametagApi.setPrefix(player, plinfo.getPrefix().replaceAll("§r§.", ""));
                if (task.get(sender.getName()) != null) {
                    Bukkit.getScheduler().cancelTask(task.get(sender.getName()));
                    task.remove(sender.getName());
                    rainbow.remove(sender.getName());
                }

                switch (args[0]) {
                    case "off":
                        if (player.isGlowing()) {
                            player.setGlowing(false);
                        }
                        sender.sendMessage(Main.prefix + " §fВы §cвыключили§f подсветку.");
                        break;
                    case "on":
                        if (!player.isGlowing()) {
                           player.setGlowing(true);
                        }
                        sender.sendMessage(Main.prefix + " §fВы §aвключили§f подсветку.");
                        break;
                    case "toggle":
                        if (player.isGlowing()) {
                            player.setGlowing(false);
                            sender.sendMessage(Main.prefix + " §fВы §cвыключили§f подсветку.");
                        } else {
                            sender.sendMessage(Main.prefix + " §fВы §aвключили§f подсветку.");
                            player.setGlowing(true);
                        }
                        break;
                    case "rainbow":
                        if (rainbow.get(sender.getName()) == null) {
                            rainbow.put(sender.getName(), 0);
                            if (!player.isGlowing()) {
                                player.setGlowing(true);
                            }
                            sender.sendMessage(Main.prefix + " §fУстановлено свечение: §aR§bA§cI§dN§eB§1O§2W");
                            int idtask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
                                if (player.isOnline()) {
                                    int i = rainbow.get(sender.getName());
                                    nametagApi.setPrefix(player, plinfo.getPrefix().replaceAll("§r§.", ""));
                                    nametagApi.setPrefix(player, plinfo.getPrefix() + "§r" + prefix_colors.get(i));
                                    if (i >= 14) {
                                        rainbow.replace(sender.getName(), 0);
                                    } else {
                                        rainbow.replace(sender.getName(), i + 1);
                                    }
                                }
                            }, 15L, 20L);
                            task.put(player.getName(), idtask);
                        }
                        break;
                    default:
                        if (name_colors.contains(args[0])) {
                            if (!player.isGlowing()) {
                                player.setGlowing(true);
                            }
                            int i = name_colors.indexOf(args[0]);
                            NametagEdit.getApi().setPrefix(player ,plinfo.getPrefix() + "§r" + prefix_colors.get(i));
                            sender.sendMessage(Main.prefix + " §fУстановлено свечение: " + prefix_colors.get(i) + "§l" + name_colors.get(i));
                        } else {
                            sender.sendMessage(Main.prefix + " §cНеизвестный цвет/аргумент.");
                            sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/glowing [COLOR / RAINBOW / TOGGLE]"});
                        }
                }
            } else {
                sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/glowing [COLOR / RAINBOW / TOGGLE]"});
            }
        } else {
            sender.sendMessage(Main.prefix + " §cВы не имеете прав!");
        }
        return true;
    }
}
