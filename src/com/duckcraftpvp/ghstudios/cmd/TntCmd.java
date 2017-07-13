package com.duckcraftpvp.ghstudios.cmd;

import com.duckcraftpvp.ghstudios.*;
import org.bukkit.command.*;
import org.bukkit.*;
import com.duckcraftpvp.ghstudios.tnt.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;

public class TntCmd extends Command
{
    private CustomTNT plugin;
    
    public TntCmd(final CustomTNT plugin) {
        super("customtnt");
        this.plugin = plugin;
    }
    
    public boolean execute(final CommandSender commandSender, final String s, final String[] args) {
        if (args.length < 3 || !args[0].equalsIgnoreCase("give")) {
            return true;
        }
        if (args.length >= 3 && !commandSender.hasPermission("customtnt.command.give")) {
            commandSender.sendMessage(ChatColor.RED + "No Permission");
            return true;
        }
        commandSender.sendMessage("Usage: /CustomTNT give <player> <level> [amount]");
        final Player p = Bukkit.getPlayer(args[1]);
        if (p == null) {
            commandSender.sendMessage("Error: Player not found");
            return true;
        }
        final int level = Integer.parseInt(args[2]);
        if (level < 1 || level > this.plugin.tnt.size()) {
            commandSender.sendMessage("Error: Invalid level");
            return true;
        }
        final int amount = (args.length > 3) ? Integer.parseInt(args[3]) : 1;
        if (amount < 1) {
            commandSender.sendMessage("Error: Invalid amount");
            return true;
        }
        final TNT tnt = this.plugin.tnt.get(level - 1);
        p.getInventory().addItem(new ItemStack[] { tnt.getItem(amount) });
        p.updateInventory();
        commandSender.sendMessage(ChatColor.BLUE + "CustomTnT: Gave " + amount + " " + tnt.name + " to " + p.getDisplayName());
        return true;
    }
}
