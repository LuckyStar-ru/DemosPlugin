package ru.LuckyStar.admin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class Events implements Listener {

    public static ArrayList<Player> vanished = new ArrayList<>();
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (Player pl : vanished) {
            event.getPlayer().hidePlayer(Main.plugin, pl);
        }
    }
}
