package com.duckcraftpvp.ghstudios.tnt;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class CircuitTNT extends TNT
{
	public CircuitTNT(JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Circuit TNT", ChatColor.GOLD + "This tnt apon Exploding will only destroy redstone" ,  new ArrayList<String>() {
			private static final long serialVersionUID = 1L;});
        new HashMap<Location, Integer>();
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z), Material.REDSTONE_WIRE, Material.REDSTONE_COMPARATOR, Material.REDSTONE_COMPARATOR_ON, Material.REDSTONE_COMPARATOR_OFF, Material.REDSTONE_TORCH_ON, Material.REDSTONE_TORCH_OFF, Material.DIODE, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_ON);
                }
            }
        }
        return true;
    }
}
