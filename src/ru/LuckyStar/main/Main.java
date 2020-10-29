package ru.LuckyStar.main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.earth2me.essentials.Essentials;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.LuckyStar.admin.commands.invisible;
import ru.LuckyStar.admin.events.Events;
import ru.LuckyStar.antirelog.HandlerA;
import ru.LuckyStar.arrowtrails.trails;
import ru.LuckyStar.automine.HandlerMine;
import ru.LuckyStar.blocker.HandlerBl;
import ru.LuckyStar.bonus.HandlerB;
import ru.LuckyStar.bonus.commands;
import ru.LuckyStar.broadcast.broadcast;
import ru.LuckyStar.casino.events.HandlerCas;
import ru.LuckyStar.console.command.console;
import ru.LuckyStar.console.events.HandlerC;
import ru.LuckyStar.donate.command.dm;
import ru.LuckyStar.donate.holograms.holograms;
import ru.LuckyStar.glowing.commands.glowing;
import ru.LuckyStar.papi.PlaceHolders;
import ru.LuckyStar.payday.commands.payday;
import ru.LuckyStar.payday.time.timer;
import ru.LuckyStar.punishment.commands.*;
import ru.LuckyStar.punishment.events.Handler;
import ru.LuckyStar.rgprotect.command.rgprotect;
import ru.LuckyStar.rgprotect.event.HandlerRG;
import ru.LuckyStar.somemenus.info;
import ru.LuckyStar.somemenus.rules;
import ru.LuckyStar.sqlite.SQLDataBase;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    public static final String prefix = "§7[§cСервер§7]";//§7[§3§lD§2§lW§7]
    public static Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

    public static WorldGuardPlugin getWG() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return plugin instanceof WorldGuardPlugin ?(WorldGuardPlugin)plugin:null;
    }

    @SuppressWarnings("rawtypes")
    public static Economy getEconomy() throws Exception {
        RegisteredServiceProvider economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
        return (Economy)economyProvider.getProvider();
    }


    public static ProtectedRegion getRegion(Location loc)
    {
        ApplicableRegionSet set = getWG().getRegionManager(loc.getWorld()).getApplicableRegions(loc);
        for (ProtectedRegion each : set) {
            if (each != null) {
                return each;
            }
        }
        return null;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getWG();
        try {
            getEconomy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(new HandlerCas(), this);
        Bukkit.getPluginManager().registerEvents(new Handler(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerC(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerB(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerA(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerMine(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerRG(), this);
        Bukkit.getPluginManager().registerEvents(new HandlerBl(), this);
        Bukkit.getPluginManager().registerEvents(new trails(), this);
        Bukkit.getPluginManager().registerEvents(new Events(), this);

        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.NORMAL, new PacketType[] {PacketType.Play.Server.TAB_COMPLETE}) {
                    @EventHandler(priority = EventPriority.NORMAL)
                    public void onPacketSending(PacketEvent e){
                        if (e.getPacketType() == PacketType.Play.Server.TAB_COMPLETE) {
                            String[] completions = e.getPacket().getStringArrays().read(0); // this is the array of completions that are being sent out to the server
                            ArrayList<String> tab = new ArrayList<>();
                            for (String i : completions.clone()) {
                                if (!i.contains(":") && !i.contains("//") && !i.contains("/sync") && !i.contains("plug")) {
                                    tab.add(i);
                                }
                            }
                            e.getPacket().getStringArrays().write(0, tab.toArray(new String[0])); // using this line, we overwrite the packet information and send an empty list of suggestions back to the server
                        }
                    }
                }
        );

        getCommand("ban").setExecutor(new ban());
        getCommand("kick").setExecutor(new kick());
        getCommand("mute").setExecutor(new mute());
        getCommand("tempban").setExecutor(new tempban());
        getCommand("unban").setExecutor(new unban());
        getCommand("unmute").setExecutor(new unmute());
        getCommand("mutelist").setExecutor(new mutelist());
        getCommand("history").setExecutor(new history());
        getCommand("cc").setExecutor(new clearchat());
        getCommand("fullban").setExecutor(new fullban());
        getCommand("fullbanip").setExecutor(new fullbanip());
        getCommand("unbanfull").setExecutor(new unbanfull());
        getCommand("glowing").setExecutor(new glowing());
        getCommand("console").setExecutor(new console());
        getCommand("dm").setExecutor(new dm());
        getCommand("mybonus").setExecutor(new commands());
        getCommand("rgprotect").setExecutor(new rgprotect());
        getCommand("broadcast").setExecutor(new broadcast());
        getCommand("payday").setExecutor(new payday());
        getCommand("info").setExecutor(new info());
        getCommand("rules").setExecutor(new rules());
        getCommand("invisible").setExecutor(new invisible());
        new SQLDataBase();
        SQLDataBase.getPunish();
        SQLDataBase.getDM();
        SQLDataBase.getRegions();
        SQLDataBase.getUsesIP();
        timer.timer();
        holograms.holo();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceHolders(this).register();
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            if (!trails.arrowList.isEmpty()) {
                for (Entity temp : trails.arrowList) {
                    if (!temp.isOnGround() && !temp.isDead()) {
                        temp.getWorld().spawnParticle(Particle.END_ROD, temp.getLocation(), 60, 0.01, 0.01, 0.01, 0.01);
                    } else {
                        trails.arrowList.remove(temp);
                    }
                }
            }
        }, 3L , 1L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, () -> {
            if (!trails.specArrowList.isEmpty()) {
            for (Entity temp : trails.specArrowList) {
                if (!temp.isOnGround() && !temp.isDead()) {
                    temp.getWorld().spawnParticle(Particle.DRAGON_BREATH, temp.getLocation(), 120, 0.01, 0.01, 0.01, 0.01);
                } else {
                    trails.specArrowList.remove(temp);
                }
            }
        }
        }, 3L , 1L);
    }

    @Override
    public void onDisable() {
        SQLDataBase.savePunish();
        SQLDataBase.saveDM();
        SQLDataBase.saveRegions();
        SQLDataBase.saveUsesIP();
    }

    public static boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
