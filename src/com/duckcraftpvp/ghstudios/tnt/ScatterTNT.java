package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

public class ScatterTNT extends TNT
{
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public ScatterTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Scatter TNT", ChatColor.GOLD + "This tnt apon exploding will spawn 4 aditional TNT", new ArrayList() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        location.getWorld().createExplosion(location, 4.0f);
        new BukkitRunnable() {
            public void run() {
                for (int i = 0; i < 4; ++i) {
                    final TNTPrimed tnt = (TNTPrimed)location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                    tnt.setFuseTicks(100);
                }
            }
        }.runTaskLater((Plugin)this.plugin, 20L);
        return true;
    }
}
