package ru.LuckyStar.punishment.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import ru.LuckyStar.main.Main;
import ru.LuckyStar.punishment.commands.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class Handler implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("LuckyStar")) {
            return;
        }
        boolean fban = fullban.fullBanList_names.contains(event.getPlayer().getName());
        if (fban) {
            event.setKickMessage(Main.prefix + " §4§lВы забанены администратором проекта навсегда.");
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            return;
        }
        String baninfo = ban.banList.get(event.getPlayer().getName());
        if (baninfo != null) {
            event.setKickMessage(Main.prefix + " §fВас забанил §l" + baninfo.split("\\*")[0] + "\n§f по причине:§l " + baninfo.split("\\*")[1]);
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            return;
        }
        String tempbaninfo = tempban.tempbanList.get(event.getPlayer().getName());
        if (tempbaninfo != null) {
            long time = Duration.between(LocalDateTime.now(), LocalDateTime.parse(tempbaninfo.split("\\*")[2])).toMinutes();
            if (time >= 0) {
                event.setKickMessage(Main.prefix + " §fВас временно забанил игрок §l" + tempbaninfo.split("\\*")[0] + "\n§fпо причине:§l " + tempbaninfo.split("\\*")[1] + "\n§c(Осталось §l" + time + "§c минут)");
                event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            } else {
                tempban.tempbanList.remove(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void checkIP(PlayerJoinEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("LuckyStar")) {
            return;
        }
        if (fullbanip.fullBanList_ips.contains(event.getPlayer().getAddress().toString())) {
            event.getPlayer().kickPlayer(Main.prefix + " §4§lВы забанены администратором проекта навсегда.");
        }
    }

    @EventHandler
    public void onMute(AsyncPlayerChatEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("LuckyStar")) {
            return;
        }
        String muteinfo = mute.muteList.get(event.getPlayer().getName());
        if (muteinfo != null) {
            long time = Duration.between(LocalDateTime.now(), LocalDateTime.parse(muteinfo.split("\\*")[2])).toMinutes();
            if (time >= 0) {
                event.getPlayer().sendMessage(Main.prefix + " §fВаш чат §cзаблокирован§f игроком §l" + muteinfo.split("\\*")[0] + " §fпо причине: §l" + muteinfo.split("\\*")[1]);
                event.getPlayer().sendMessage(Main.prefix + " §fОсталось: §a§l" + time + " минут");
                event.setCancelled(true);
            } else {
                mute.muteList.remove(event.getPlayer().getName());
            }
        }
    }
}
