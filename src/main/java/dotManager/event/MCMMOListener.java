package dotManager.event;

import com.bencodez.votingplugin.VotingPluginHooks;
import com.bencodez.votingplugin.advancedcore.api.time.TimeType;
import com.bencodez.votingplugin.topvoter.TopVoter;
import dotManager.utility.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;

import dotManager.DotManager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MCMMOListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(player.getStatistic(Statistic.PLAY_ONE_MINUTE) >= 576000 && !player.hasPermission("group.settler") && player.hasPermission("dotmanager.firstsettlernotif")) {
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "dotmanager.firstsettlernotif");
			player.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText("§f§lQuick reminder: You're eligible for §a§lSettler Rank§f! §7§oJoin §b/discord §7§oand fill out a quick survey to claim!"));
		}
	}

	@EventHandler
	  public void onPlayerLevelUp(McMMOPlayerXpGainEvent event) {
	      Player player = event.getPlayer();      
	      if(player.getStatistic(Statistic.PLAY_ONE_MINUTE) >= 576000 && !player.hasPermission("group.settler") && !player.hasPermission("group.settler")) {
			  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()),
					  "group.settler");
			  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lSettler Rank! Congratulations!";
			  Bukkit.dispatchCommand(console, command);
		  }
	      if(Util.getSkillsAt1k(player) >= 0 && Util.getPowerLevel(player) >= 1000 && DotManager.econ.has(player, 5000) && !player.hasPermission("group.lighter")) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), 
	    			  "group.lighter");
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lLighter Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	      if(Util.getSkillsAt1k(player) >= 1 && Util.getPowerLevel(player) >= 2500 && DotManager.econ.has(player, 10000) && !player.hasPermission(DotManager.instance().getConfig().getString("traveler-permission"))) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), 
						DotManager.instance().getConfig().getString("traveler-permission"));
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lTraveler Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	      if(Util.getSkillsAt1k(player) >= 2 && Util.getPowerLevel(player) >= 5000 && DotManager.econ.has(player, 50000) && !player.hasPermission(DotManager.instance().getConfig().getString("routier-permission"))) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), 
						DotManager.instance().getConfig().getString("routier-permission"));
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lRoutier Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	      if(Util.getSkillsAt1k(player) >= 5 && Util.getPowerLevel(player) >= 9500 && DotManager.econ.has(player, 100000) && !player.hasPermission("group.dotist")) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "group.dotist");
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lDotist Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	      if(Util.getSkillsAt1k(player) >= 10 && Util.getPowerLevel(player) >= 13000 && DotManager.econ.has(player, 250000) && !player.hasPermission("group.legend")) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "group.legend");
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lLegend Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	      if(Util.getSkillsAt1k(player) >= 15 && DotManager.econ.has(player, 1000000) && !player.hasPermission("group.deity")) {
	    	  DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "group.deity");
	    	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    	  String command = "vbroadcast " + player.getDisplayName() + " has achieved &f&lDeity Rank! Congratulations!";
	    	  Bukkit.dispatchCommand(console, command);
	      }
	  }//15 skills

}
