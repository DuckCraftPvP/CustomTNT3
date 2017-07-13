package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.scheduler.*;
import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class TacticalTNT extends TNT
{
    @SuppressWarnings("unused")
	private static Map<Location, Integer> blocks;
    @SuppressWarnings("unused")
	private static BukkitTask task;
    
    @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public TacticalTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Tactical TNT", ChatColor.GOLD + "This tnt apon exploding will only kill Mob Spawners", new ArrayList() {});
        TacticalTNT.blocks = new HashMap<Location, Integer>();
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z), Material.MOB_SPAWNER);
                }
            }
        }
        return true;
    }
}
