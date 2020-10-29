package ru.LuckyStar.donate.holograms;

import org.bukkit.Bukkit;
import ru.LuckyStar.donate.command.dm;
import ru.LuckyStar.main.Main;

public class holograms {
    public static void holo() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "holo setline top1 2 &e1 &7| &e&l" + dm.getTopName(1) + " &7-> &a&l" + dm.getTopInt(1) + " &fрублей");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "holo setline top1 3 &e1 &7| &e&l" + dm.getTopName(2) + " &7-> &a&l" + dm.getTopInt(2) + " &fрублей");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "holo setline top1 4 &e1 &7| &e&l" + dm.getTopName(3) + " &7-> &a&l" + dm.getTopInt(3) + " &fрублей");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "holo setline top1 5 &e1 &7| &e&l" + dm.getTopName(4) + " &7-> &a&l" + dm.getTopInt(4) + " &fрублей");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "holo setline top1 6 &e1 &7| &e&l" + dm.getTopName(5) + " &7-> &a&l" + dm.getTopInt(5) + " &fрублей");
        }, 1200L, 6000L);
    }
}
