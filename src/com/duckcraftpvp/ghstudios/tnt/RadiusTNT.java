package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class RadiusTNT extends TNT
{
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public RadiusTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Radius TNT", ChatColor.GOLD + "This tnt apon Exploding will make a square hole", new ArrayList() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        if (location.getBlock().getType() == Material.STATIONARY_WATER) {
            return true;
        }
        for (int x = -2; x <= 1; ++x) {
            for (int y = -2; y <= 1; ++y) {
                for (int z = -2; z <= 1; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z));
                }
            }
        }
        return true;
    }
}
