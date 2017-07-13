package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class SandStorm extends TNT
{
    @SuppressWarnings("serial")
	public SandStorm(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Sand Storm", ChatColor.GOLD + "This TNT will destroy only sand, Good for ruined cannons", new ArrayList<String>() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z), Material.SAND);
                }
            }
        }
        return true;
    }
}
