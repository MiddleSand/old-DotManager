package dotManager.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class SmiteCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length != 1) {
            return false;
        }
        for (final Player target : resolvePlayerSelector(sender, args)) {
           for(int i = 0; i < 1000; i++) {
        	   target.getWorld().strikeLightning(target.getEyeLocation());
           }           
           target.setHealth(0);
        }
        return true;
    }
    
    private static List<Player> resolvePlayerSelector(final CommandSender sender, final String[] args) {
        final List<Player> players = new ArrayList<Player>();
        if (args[0].startsWith("@")) {
            List<Entity> entities;
            try {
                entities = (List<Entity>)Bukkit.selectEntities(sender, args[0]);
            }
            catch (IllegalArgumentException e) {
                sender.sendMessage("Target selector malformed!");
                return players;
            }
            for (final Entity entity : entities) {
                if (entity instanceof Player) {
                    players.add((Player)entity);
                }
            }
        }
        else {
            final Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                players.add(target);
            }
            else {
                sender.sendMessage(ChatColor.RED + "The specified player is not online!");
            }
        }
        return players;
    }
}