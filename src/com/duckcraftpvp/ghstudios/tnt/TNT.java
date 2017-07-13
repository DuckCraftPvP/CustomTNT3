package com.duckcraftpvp.ghstudios.tnt;

import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public abstract class TNT implements Listener
{
    public String name;
    public String id;
    protected ArrayList<String> lore;
    protected ArrayList<Entity> entities;
    protected ArrayList<Block> blocks;
    protected JavaPlugin plugin;
    
    public TNT(final JavaPlugin plugin, final String name, final String id) {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
        this.entities = new ArrayList<Entity>();
        this.blocks = new ArrayList<Block>();
        this.name = name;
        this.id = id;
        this.plugin = plugin;
    }
    
    public TNT(final JavaPlugin plugin, final String name, final String id, final ArrayList<String> lore) {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
        this.entities = new ArrayList<Entity>();
        this.blocks = new ArrayList<Block>();
        this.name = name;
        lore.add(0, this.id = id);
        this.lore = lore;
        this.plugin = plugin;
    }
    
    protected abstract boolean onExplode(final Location p0);
    
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public ItemStack getItem(final int amount) {
        final ItemStack is = new ItemStack(Material.TNT, amount);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Custom TNT: " + this.name);
        if (this.lore != null) {
            im.setLore((List)this.lore);
        }
        else {
            im.setLore((List)new ArrayList() {});
        }
        is.setItemMeta(im);
        return is;
    }
    
    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }
    
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }
    
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }
    
    public void removeBlocks(final ArrayList<Block> b) {
        this.blocks.removeAll(b);
    }
    
    @EventHandler
    public void onBlockPlaced(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem() != null && e.getItem().getType() == Material.TNT) {
            final List<String> lore = (List<String>)e.getItem().getItemMeta().getLore();
            if (lore == null) {
                return;
            }
            if (!lore.get(0).equals(this.id)) {
                return;
            }
            this.blocks.add(e.getClickedBlock().getRelative(e.getBlockFace()));
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onExplode(final EntityExplodeEvent e) {
        if (this.entities.contains(e.getEntity())) {
            this.entities.remove(e.getEntity());
            if (this.onExplode(e.getLocation())) {
                e.getEntity().getWorld().createExplosion(e.getLocation(), 0.0f);
            }
            e.setCancelled(true);
        }
    }
    
    protected void destroyBlock(final Location location) {
        this.breakBlock(location.getBlock());
    }
    
    protected void destroyBlock(final Location location, final Material... mat) {
        final Block b = location.getBlock();
        for (final Material m : mat) {
            if (m == b.getType()) {
                this.breakBlock(b);
            }
        }
    }
    
    private void breakBlock(final Block b) {
        if (b.getType() != Material.OBSIDIAN) {
            if (b.getType() == Material.BEDROCK) {
                return;
            }
            b.breakNaturally();
        }
    }
}
