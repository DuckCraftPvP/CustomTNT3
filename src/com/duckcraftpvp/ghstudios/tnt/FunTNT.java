package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.*;
import com.duckcraftpvp.ghstudios.util.*;

public class FunTNT extends TNT
{
    @SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public FunTNT(final JavaPlugin plugin) {
        super(plugin, ChatColor.GREEN + "Fun TNT", ChatColor.GOLD + "This TNT apon exploding will show fireworks and sounds", new ArrayList() {});
    }
    
    @Override
    protected boolean onExplode(final Location location) {
        location.getWorld().playSound(location, Sound.BLOCK_NOTE_CHIME, 10.0f, 10.0f);
        location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_TWINKLE , 10.0f, 10.0f);
        new InstantFirework(InstantFirework.getRandomFirework(), location);
        return false;
    }
}
