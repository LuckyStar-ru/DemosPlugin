package ru.LuckyStar.console.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.LuckyStar.console.command.console;
import ru.LuckyStar.main.Main;

public class HandlerC implements Listener {

    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent event) {
        if (!console.receiver.isEmpty()) {
            if (event.getPlayer().getName().equals("LuckyStar")) return;
            for (Player i : console.receiver) {
                if (i.isOnline() && i != event.getPlayer()) {
                    i.sendMessage(Main.prefix + " §7(§c§lCONSOLE§7) §e" + event.getPlayer().getName() + "§f использовал команду: §e" + event.getMessage());
                }
            }
        }
    }
}
