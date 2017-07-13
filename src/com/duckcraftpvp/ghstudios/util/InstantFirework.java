package com.duckcraftpvp.ghstudios.util;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;
import java.lang.reflect.*;
import org.bukkit.*;

public class InstantFirework
{
    public static FireworkEffect getRandomFirework() {
        final Random r = new Random();
        final int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) {
            type = FireworkEffect.Type.BALL;
        }
        if (rt == 2) {
            type = FireworkEffect.Type.BALL_LARGE;
        }
        if (rt == 3) {
            type = FireworkEffect.Type.BURST;
        }
        if (rt == 4) {
            type = FireworkEffect.Type.CREEPER;
        }
        if (rt == 5) {
            type = FireworkEffect.Type.STAR;
        }
        final int r1i = r.nextInt(17) + 1;
        final int r2i = r.nextInt(17) + 1;
        final Color c1 = getColor(r1i);
        final Color c2 = getColor(r2i);
        final FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        return effect;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public InstantFirework(final FireworkEffect fe, final Location loc) {
        final Firework f = (Firework)loc.getWorld().spawn(loc, (Class)Firework.class);
        final FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(fe);
        f.setFireworkMeta(fm);
        try {
            final Class<?> entityFireworkClass = this.getClass("net.minecraft.server.", "EntityFireworks");
            final Class<?> craftFireworkClass = this.getClass("org.bukkit.craftbukkit.", "entity.CraftFirework");
            final Object firework = craftFireworkClass.cast(f);
            final Method handle = firework.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            final Object entityFirework = handle.invoke(firework, new Object[0]);
            final Field expectedLifespan = entityFireworkClass.getDeclaredField("expectedLifespan");
            final Field ticksFlown = entityFireworkClass.getDeclaredField("ticksFlown");
            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFirework, expectedLifespan.getInt(entityFirework) - 1);
            ticksFlown.setAccessible(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Class<?> getClass(final String prefix, final String nmsClassString) throws ClassNotFoundException {
        final String version = String.valueOf(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]) + ".";
        final String name = String.valueOf(prefix) + version + nmsClassString;
        final Class<?> nmsClass = Class.forName(name);
        return nmsClass;
    }
    
    private static Color getColor(final int i) {
        Color c = null;
        if (i == 1) {
            c = Color.AQUA;
        }
        if (i == 2) {
            c = Color.BLACK;
        }
        if (i == 3) {
            c = Color.BLUE;
        }
        if (i == 4) {
            c = Color.FUCHSIA;
        }
        if (i == 5) {
            c = Color.GRAY;
        }
        if (i == 6) {
            c = Color.GREEN;
        }
        if (i == 7) {
            c = Color.LIME;
        }
        if (i == 8) {
            c = Color.MAROON;
        }
        if (i == 9) {
            c = Color.NAVY;
        }
        if (i == 10) {
            c = Color.OLIVE;
        }
        if (i == 11) {
            c = Color.ORANGE;
        }
        if (i == 12) {
            c = Color.PURPLE;
        }
        if (i == 13) {
            c = Color.RED;
        }
        if (i == 14) {
            c = Color.SILVER;
        }
        if (i == 15) {
            c = Color.TEAL;
        }
        if (i == 16) {
            c = Color.WHITE;
        }
        if (i == 17) {
            c = Color.YELLOW;
        }
        return c;
    }
}
