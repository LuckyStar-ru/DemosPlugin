package ru.LuckyStar.cooldowns;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cooldown {

    private final String nick;
    private final char action;
    private LocalTime time;
    public static HashMap<String, List<Cooldown>> cooldownHashMap = new HashMap<>();

    public Cooldown(String nick, char action, LocalTime time) {
        this.nick = nick;
        this.action = action;
        this.time = time;
    }

    public static Cooldown getCD(String nick, char action) {
        if (cooldownHashMap.get(nick) != null) {
            for (int i = 0; cooldownHashMap.get(nick).size() > i; i++) {
                Cooldown cdtemp = cooldownHashMap.get(nick).get(i);
                if (cdtemp.getAction() == action) {
                    if (getBeetweenTime(cdtemp.getTime()) <= 0) {
                        cooldownHashMap.get(nick).remove(cdtemp);
                    } else {
                        return cdtemp;
                    }
                }
            }

        }
        return null;
    }

    public static void putCD(Cooldown cooldown) {
        List<Cooldown> cooldowns = cooldownHashMap.get(cooldown.getNick());
        if (cooldowns != null) {
            if (getCD(cooldown.getNick(), cooldown.getAction()) == null) {
                cooldowns.add(cooldown);
                cooldownHashMap.replace(cooldown.getNick(), cooldowns);
            }
        } else {
            cooldownHashMap.put(cooldown.getNick(), new ArrayList<Cooldown>() {{add(cooldown);}});
        }
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public char getAction() {
        return action;
    }

    public String getNick() {
        return nick;
    }

    public static long getBeetweenTime(LocalTime time) {
        return Duration.between(LocalTime.now(), time).getSeconds();
    }
}
