package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class GiganticTNT extends TNT
{
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public GiganticTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Gigantic TNT", ChatColor.GOLD + "This tnt apon exploding will make a giant hole", new ArrayList() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        if (location.getBlock().getType() == Material.STATIONARY_WATER) {
            return true;
        }
        for (int x = -8; x <= 8; ++x) {
            for (int y = -8; y <= 8; ++y) {
                for (int z = -8; z <= 8; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z));
                }
            }
        }
        return true;
    }
}
