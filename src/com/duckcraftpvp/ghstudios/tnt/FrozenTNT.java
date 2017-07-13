package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class FrozenTNT extends TNT
{
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public FrozenTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Frozen TNT", ChatColor.GOLD + "This tnt apon exploding will freeze all water", new ArrayList() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    if (location.clone().add((double)x, (double)y, (double)z).getBlock().isLiquid()) {
                        location.clone().add((double)x, (double)y, (double)z).getBlock().setType(Material.PACKED_ICE);
                    }
                }
            }
        }
        return true;
    }
}
