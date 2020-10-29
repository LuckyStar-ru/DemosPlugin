package ru.LuckyStar.arrowtrails;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class trails implements Listener {

    @EventHandler
    public void trail(EntityShootBowEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Entity oproj = event.getProjectile();
        if (oproj instanceof Projectile) {
            Projectile proj = (Projectile) oproj;
            if (proj instanceof org.bukkit.entity.SpectralArrow &&
                    livingEntity instanceof Player) {
                trail(proj);
                return;
            }
            if (proj instanceof org.bukkit.entity.Arrow &&
                    livingEntity instanceof Player) {
                trail(proj);
            }
        }
    }
    public static ArrayList<Entity> specArrowList = new ArrayList<>();
    public static ArrayList<Entity> arrowList = new ArrayList<>();
    public void trail(final Projectile proj) {
        if (!proj.isOnGround() && !proj.isDead()) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                Location loc = proj.getLocation();
                proj.getWorld().spawnParticle(Particle.SMOKE_NORMAL, proj.getLocation(), 25);
                proj.getVelocity().multiply(1.5);
                if (proj instanceof SpectralArrow) {
                    specArrowList.add(proj);
                } else {
                    arrowList.add(proj);
                }
            }, 3L);
        }
    }


}
