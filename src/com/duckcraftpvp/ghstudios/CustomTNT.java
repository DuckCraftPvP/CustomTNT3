package com.duckcraftpvp.ghstudios;

import org.bukkit.plugin.java.*;
import com.duckcraftpvp.ghstudios.tnt.*;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.scheduler.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.plugin.*;
import com.duckcraftpvp.ghstudios.cmd.*;
import org.bukkit.command.*;
import java.lang.reflect.*;

public class CustomTNT extends JavaPlugin
{
    private static CustomTNT plugin;
    public ArrayList<TNT> tnt;
    
    public static CustomTNT getPlugin() {
        return CustomTNT.plugin;
    }
    
    public void onEnable() {
        (this.tnt = new ArrayList<TNT>()).add(new FunTNT(this));
        this.tnt.add(new RadiusTNT(this));
        this.tnt.add(new GiganticTNT(this));
        this.tnt.add(new CircuitTNT(this));
        this.tnt.add(new ScatterTNT(this));
        this.tnt.add(new FrozenTNT(this));
        this.tnt.add(new SandStorm(this));
        this.tnt.add(new TacticalTNT(this));
        new BukkitRunnable() {
            private Object b;
            
            public void run() {
                final ArrayList<Block> remove = new ArrayList<Block>();
                for (final TNT t : CustomTNT.this.tnt) {
                    @SuppressWarnings("rawtypes")
					final Iterator localIterator2 = t.getBlocks().iterator();
                    while (localIterator2.hasNext()) {
                        this.b = localIterator2.next();
                        for (final Entity e : ((Block) this.b).getWorld().getEntities()) {
                            if (e.getType() == EntityType.PRIMED_TNT && e.getLocation().distance(((Block) this.b).getLocation()) <= 1.0 && !t.getEntities().contains(e)) {
                                t.addEntity(e);
                                remove.add((Block) this.b);
                            }
                        }
                    }
                    t.removeBlocks(remove);
                }
            }
        }.runTaskTimer((Plugin)this, 0L, 1L);
        try {
            final Field bukkitCommandMap = this.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            final CommandMap commandMap = (CommandMap)bukkitCommandMap.get(this.getServer());
            commandMap.register("customtnt", (Command)new TntCmd(this));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(ChatColor.GREEN + "[CustomTNT]" + ChatColor.GOLD + " CustomTNT has been Enabled!");
        System.out.println(ChatColor.GREEN + "[CustomTNT]" + ChatColor.GOLD + " Made by: GHStudios");
        System.out.println(ChatColor.GREEN + "[CustomTNT]" + ChatColor.GOLD + " Check out our custom server: DuckCraftPvP.com");
    }
    
    public void onDisable() {
        System.out.println(ChatColor.RED + "[CustomTNT]" + ChatColor.GOLD + " CustomTNT has been DISABLED!");
        System.out.println(ChatColor.RED + "[CustomTNT]" + ChatColor.GOLD + " Made by: GHStudios");
        System.out.println(ChatColor.RED + "[CustomTNT]" + ChatColor.GOLD + " Check out our custom server: DuckCraftPvP.com");
    }
}
