package dotManager.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dotManager.event.QualityListener;
import dotManager.utility.AppraisalResult;

public class AppraiseCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length != 1 || !sender.isOp()) {
        	Player player = (Player) sender;
        	ItemStack toAppraise = player.getInventory().getItemInMainHand();
            if(toAppraise.getType() == Material.AIR) {
            	player.sendMessage("�cPlease hold an item in your hand to appraise.");
            	return false;
            }
            AppraisalResult result = QualityListener.applyAppraisal(toAppraise);
            ItemStack appraised = result.item;
            if(appraised == null) {
            	player.sendMessage("�cThis item is not appraisable!");
            	return false;
            }
            player.getInventory().addItem(new ItemStack[] { appraised });
            player.sendMessage(ChatColor.GRAY + "+ 1 " + result.qualityDescriptor + QualityListener.getStackName(appraised));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null) {
        	return false;
        }
        ItemStack toAppraise = player.getInventory().getItemInMainHand();
        if(toAppraise.getType() == Material.AIR) {
        	player.sendMessage("�cPlease hold an item in your hand to appraise.");
        	return false;
        }
        AppraisalResult result = QualityListener.applyAppraisal(toAppraise);
        ItemStack appraised = result.item;
        if(appraised == null) {
        	player.sendMessage("�cThis item is not appraisable!");
        	return false;
        }
        player.getInventory().addItem(new ItemStack[] { appraised });
        player.sendMessage(ChatColor.GRAY + "+ 1 " + result.qualityDescriptor + QualityListener.getStackName(appraised));
        return true;
    }   
}