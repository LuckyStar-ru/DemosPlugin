package ru.LuckyStar.restart;

import org.bukkit.Bukkit;
import ru.LuckyStar.main.Main;

public class restart {
    public static void restart() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            Bukkit.broadcastMessage(Main.prefix + " §4§lСЕРВЕР ПЕРЕЗАГРУЗИТЬСЯ В ТЕЧЕНИЕ МИНУТЫ");
        }, 20L, 20L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            Bukkit.broadcastMessage(Main.prefix + " §4§lЗАХОДИТЕ ЧЕРЕЗ 3 МИНУТЫ");
        }, 40L, 40L);
    }
}
