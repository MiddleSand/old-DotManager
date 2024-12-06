package dotManager.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;

import dotManager.DotManager;
import dotManager.event.QualityListener;
import dotManager.utility.AppraisalResult;

public class AppraiseAllCommand implements CommandExecutor
{
    @SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length != 1 || !sender.isOp()) {
        	Player player = (Player) sender;
        	int itemCount = 0;
            ItemStack[] inv = player.getInventory().getContents();
            //TODO: rewrite this so it doesnt lag everyone out lol
            
            DotManager.instance().getServer().getScheduler().scheduleAsyncDelayedTask(DotManager.instance(), new Runnable() {

				@Override
				public void run() {
					HashMap m = new HashMap();
					try {
		            	for(ItemStack item : inv){
		            		if(item == null) {
		            			//If this works I'll be stunned, but it'd explain some stuff
		            			continue;
		            		}
		            		Material type = item.getType();
		            		if(type == Material.LEATHER || type == Material.IRON_INGOT|| type == Material.GOLD_INGOT || type == Material.DIAMOND || type == Material.NETHERITE_INGOT){
		            			for(int i = item.getAmount(); i > 0; i--) {
		            				AppraisalResult result = QualityListener.applyAppraisal(item);
		            				ItemStack appraised = result.item;
		            				if(appraised == null) {
		            					continue;
		            				}

		            			}               
		            		}
		            	}
		            } catch (NullPointerException e) {
		            	//WHADEVAH, the command works so we dont need to do anything more lol
		            }
					DotManager.instance().getServer().getScheduler().scheduleSyncDelayedTask(DotManager.instance(), new Runnable() {
					
						@SuppressWarnings("unchecked")
						@Override
						public void run() {
						}
						
					});
				}
            	
            });
            
            
            /** 
             player.getInventory().addItem(new ItemStack[] { appraised });
            				player.sendMessage(ChatColor.GRAY + "+ 1 " + result.qualityDescriptor + QualityListener.getStackName(appraised));
            				*/
            player.sendMessage(ChatColor.GRAY + "Appraised all appraisable items.");
            return true;
        }
        
        Player player = Bukkit.getPlayer(args[0]);
        
        if(player == null) {
        	return false;
        }
        int itemCount = 0;
        ItemStack[] inv = player.getInventory().getContents();
        try {
        	for(ItemStack item : inv){
        		if(item == null) {
        			//If this works I'll be stunned, but it'd explain some stuff
        			continue;
        		}
        		Material type = item.getType();
        		if(type == Material.LEATHER || type == Material.IRON_INGOT|| type == Material.GOLD_INGOT || type == Material.DIAMOND || type == Material.NETHERITE_INGOT){
        			for(int i = item.getAmount(); i > 0; i--) {
        				AppraisalResult result = QualityListener.applyAppraisal(item);
        				ItemStack appraised = result.item;
        				if(appraised == null) {
        					continue;
        				}
        				player.getInventory().addItem(new ItemStack[] { appraised });
        				player.sendMessage(ChatColor.GRAY + "+ 1 " + result.qualityDescriptor + QualityListener.getStackName(appraised));
        			}               
        		}
        	}
        } catch (NullPointerException e) {
        	//WHADEVAH, the command works so we dont need to do anything more lol
        }
        player.sendMessage(ChatColor.GRAY + "Appraised all appraisable items.");
        return true;
    }   
}