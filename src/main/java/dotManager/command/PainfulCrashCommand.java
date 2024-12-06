package dotManager.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class PainfulCrashCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length != 1) {
            return false;
        }
        for (final Player target : resolvePlayerSelector(sender, args)) {
        	new Thread()
            {
              public void run()
              {
                try
                {
                  ItemStack writtenBook = new ItemStack(Material.WRITABLE_BOOK);
                  BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
                  String title = "Title";
                  String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";

                  bookMeta.setTitle(title);
                  bookMeta.setAuthor("your mom");
                  bookMeta.setPages();
                  writtenBook.setItemMeta(bookMeta);
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }
              }
            }.start();
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