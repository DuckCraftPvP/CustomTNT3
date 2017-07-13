package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.scheduler.*;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;

public class CircuitTNT extends TNT
{
	@SuppressWarnings("unused")
	private static Map<Location, Integer> blocks;
	@SuppressWarnings("unused")
	private static BukkitTask task;
    
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public CircuitTNT(JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Circuit TNT", ChatColor.GOLD + "This tnt apon Exploding will only destroy redstone" ,  new ArrayList() {});
        CircuitTNT.blocks = new HashMap<Location, Integer>();
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    this.destroyBlock(location.clone().add((double)x, (double)y, (double)z), Material.REDSTONE_WIRE);
                }
            }
        }
        return true;
    }
}
