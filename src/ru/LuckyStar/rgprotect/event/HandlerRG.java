package ru.LuckyStar.rgprotect.event;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.*;
import ru.LuckyStar.main.Main;

import java.util.ArrayList;

public class HandlerRG implements Listener {
    public static ArrayList<String> protectedrg = new ArrayList<>();
    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent event) {
        ProtectedRegion region = Main.getRegion(event.getBlock().getLocation());
        if (region != null) {
            if (protectedrg.contains(region.getId())) {
                if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                    event.setCancelled(true);
                    ApplicableRegionSet set = Main.getWG().getRegionManager(event.getBlock().getWorld()).getApplicableRegions(region);
                    if(set.getFlag(DefaultFlag.FALL_DAMAGE) == StateFlag.State.DENY) {
                        return;
                    }
                    event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace(BlockPlaceEvent event) {
        ProtectedRegion region = Main.getRegion(event.getBlock().getLocation());
        if (region != null) {
            if (protectedrg.contains(region.getId())) {
                if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                    event.setCancelled(true);
                    ApplicableRegionSet set = Main.getWG().getRegionManager(event.getBlock().getWorld()).getApplicableRegions(region);
                    if(set.getFlag(DefaultFlag.FALL_DAMAGE) == StateFlag.State.DENY) {
                        return;
                    }
                    event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpawnEntity(EntitySpawnEvent event) {
        if (event.getEntity().getType().equals(EntityType.DROPPED_ITEM)) return;
        ProtectedRegion region = Main.getRegion(event.getLocation());
        if (region != null) {
            if (protectedrg.contains(region.getId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getEntityType().equals(EntityType.ARMOR_STAND) || event.getEntityType().equals(EntityType.ITEM_FRAME) || event.getEntityType().equals(EntityType.MINECART)) {
            ProtectedRegion region = Main.getRegion(event.getEntity().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getDamager().getUniqueId()) && !region.getMembers().contains(event.getDamager().getUniqueId())) {
                        event.setCancelled(true);
                        event.getDamager().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() != null) {
            if (event.getRightClicked().getType().equals(EntityType.PLAYER)) return;
            ProtectedRegion region = Main.getRegion(event.getRightClicked().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND) || event.getRightClicked().getType().equals(EntityType.ITEM_FRAME))
                            event.setCancelled(true);
                        ApplicableRegionSet set = Main.getWG().getRegionManager(event.getRightClicked().getWorld()).getApplicableRegions(region);
                        if (set.getFlag(DefaultFlag.INTERACT) == StateFlag.State.ALLOW) {
                            return;
                        }
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() != null) {
            ProtectedRegion region = Main.getRegion(event.getRightClicked().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND) || event.getRightClicked().getType().equals(EntityType.ITEM_FRAME))
                            event.setCancelled(true);
                        ApplicableRegionSet set = Main.getWG().getRegionManager(event.getRightClicked().getWorld()).getApplicableRegions(region);
                        if (set.getFlag(DefaultFlag.INTERACT) == StateFlag.State.ALLOW) {
                            return;
                        }
                        if (event.getRightClicked().getType().equals(EntityType.PLAYER)) return;
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            ProtectedRegion region = Main.getRegion(event.getClickedBlock().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                        if (event.getItem() != null) {
                            if (event.getItem().getType().equals(Material.ARMOR_STAND) || event.getItem().getType().equals(Material.ITEM_FRAME))
                                event.setCancelled(true);
                        }
                        ApplicableRegionSet set = Main.getWG().getRegionManager(event.getClickedBlock().getWorld()).getApplicableRegions(region);
                        if (set.getFlag(DefaultFlag.USE) == StateFlag.State.ALLOW) {
                            return;
                        }
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBucet(PlayerBucketFillEvent event) {
        if (event.getBlockClicked() != null) {
            ProtectedRegion region = Main.getRegion(event.getBlockClicked().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                        ApplicableRegionSet set = Main.getWG().getRegionManager(event.getBlockClicked().getWorld()).getApplicableRegions(region);
                        if (set.getFlag(DefaultFlag.USE) == StateFlag.State.ALLOW) {
                            return;
                        }
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBuckit(PlayerBucketEmptyEvent event) {
        if (event.getBlockClicked() != null) {
            ProtectedRegion region = Main.getRegion(event.getBlockClicked().getLocation());
            if (region != null) {
                if (protectedrg.contains(region.getId())) {
                    if (!region.getOwners().contains(event.getPlayer().getUniqueId()) && !region.getMembers().contains(event.getPlayer().getUniqueId())) {
                        ApplicableRegionSet set = Main.getWG().getRegionManager(event.getBlockClicked().getWorld()).getApplicableRegions(region);
                        if (set.getFlag(DefaultFlag.USE) == StateFlag.State.ALLOW) {
                            return;
                        }
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Main.prefix + " §fЭтот регион защищен.");
                    }
                }
            }
        }
    }
}
