package ru.LuckyStar.payday.time;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.LuckyStar.main.Main;
import ru.LuckyStar.restart.restart;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class timer {
    public static OffsetDateTime MinNow;

    public static void timer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            MinNow = OffsetDateTime.now(ZoneId.of("Europe/Moscow"));
            if (MinNow.getMinute() == 0 || MinNow.getMinute() == 30) {
                if (MinNow.getDayOfWeek().equals(DayOfWeek.SATURDAY) || MinNow.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    payday(true, null, 1);
                } else {
                    payday(true, null, 2);
                }
            }
            if (MinNow.getHour() == 3 && MinNow.getMinute() == 58) {
                restart.restart();
            }
        }, 1200L, 1200L);
    }

    public static void payday(boolean bool, Player player, int multiply) {
        int duration = getDuration();
        String prefix = "Донатер";
        if (!bool) {
            prefix = ChatColor.translateAlternateColorCodes('&',PermissionsEx.getUser(player).getPrefix());
        }
        for (Player pl : Bukkit.getOnlinePlayers()) {
            int pay = getPay(pl);
            try {
                Main.getEconomy().depositPlayer(pl, pay * multiply);
                if (bool) {
                    pl.sendMessage(new String[]{Main.prefix + " §f§fВы получили зарплату §a" + pay * multiply + "$", Main.prefix + " §fСледующая зарплата через §e" + duration + " мин§f.", Main.prefix + " §7По будням зарплата увеличивается в два раза!"});
                } else {
                    pl.sendMessage(new String[]{Main.prefix + " §f§fВы получили зарплату §a" + pay + "$", Main.prefix + " §fСледующая зарплата через §e" + duration + " мин§f.", Main.prefix + " §7По будням зарплата увеличивается в два раза!", Main.prefix + " §fАктивировал §aпреждевременную§f зарплату §a§l⤵\n " + prefix + " " + player.getName()});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getPay(Player name) {
        if (PermissionsEx.getUser(name).inGroup("demokrat")) {
            return 75000;
        } else if (PermissionsEx.getUser(name).inGroup("yprav")) {
            return 50000;
        } else if (PermissionsEx.getUser(name).inGroup("head")) {
            return 45000;
        } else if (PermissionsEx.getUser(name).inGroup("osnovatel")) {
            return 40000;
        } else if (PermissionsEx.getUser(name).inGroup("admin")) {
            return 35000;
        } else if (PermissionsEx.getUser(name).inGroup("moderator")) {
            return 25000;
        } else if (PermissionsEx.getUser(name).inGroup("creative")) {
            return 20000;
        } else if (PermissionsEx.getUser(name).inGroup("premium")){
            return 15000;
        } else if (PermissionsEx.getUser(name).inGroup("vip")) {
            return 12500;
        }
        return 10000;
    }

    public static Integer getDuration() {
        if (MinNow.getMinute() - 30 >= 0)
            return 60 - MinNow.getMinute();
        return 30 - MinNow.getMinute();
    }

}
