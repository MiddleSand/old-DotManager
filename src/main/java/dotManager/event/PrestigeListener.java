package dotManager.event;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.djrapitops.vaultevents.events.economy.PlayerWithdrawEvent;

import dotManager.DotManager;

public class PrestigeListener implements Listener
{
	@EventHandler
	public void onPlayerGoesBroke(PlayerWithdrawEvent event) {
	}
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
    	
    }
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event)
    {
    		
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
    		if(event.getEntity().getKiller()!= null) {
    			Player killer = event.getEntity().getKiller();
    			Entity theEntity = event.getEntity();
    			if(theEntity.getType() == EntityType.WITHER) {
    		//		DataManager.adjustPlayerPrestige(killer.getUniqueId(), DotManager.witherPrestige);
    			}
    			if(theEntity.getType() == EntityType.ENDER_DRAGON) {
    			//	DataManager.adjustPlayerPrestige(killer.getUniqueId(), DotManager.dragonPrestige);
    			}
    		}
    }
    @EventHandler
    public void onBan(PlayerQuitEvent event) {
       if (event.getPlayer().isBanned()) {
    	  // DataManager.adjustPlayerPrestige(event.getPlayer().getUniqueId(), DotManager);  
       }
    }

	public static void doPermissionsChecks(float value, Player p) {
		//Ranks
		if(!p.hasPermission("dotmanager.exempt")) {
		if(value >= DotManager.instance().getConfig().getDouble("lighter-prestige")) {
			if(!p.hasPermission(DotManager.instance().getConfig().getString("lighter-permission"))) {
				DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("lighter-permission"));
			}
		} else {
			if(p.hasPermission(DotManager.instance().getConfig().getString("lighter-permission"))) {
				DotManager.permission.playerRemove(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("lighter-permission"));
			}
		}
		if(value >= DotManager.instance().getConfig().getDouble("traveler-prestige")) {
			if(!p.hasPermission(DotManager.instance().getConfig().getString("traveler-permission"))) {
				DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("traveler-permission"));
			}
		} else {
			if(p.hasPermission(DotManager.instance().getConfig().getString("traveler-permission"))) {
				DotManager.permission.playerRemove(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("traveler-permission"));
			}
		}
		if(value >= DotManager.instance().getConfig().getDouble("routier-prestige")) {
			if(!p.hasPermission(DotManager.instance().getConfig().getString("routier-permission"))) {
				DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("routier-permission"));
			}
		} else {
			if(p.hasPermission(DotManager.instance().getConfig().getString("routier-permission"))) {
				DotManager.permission.playerRemove(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("routier-permission"));
			}
		}
		if(value >= DotManager.instance().getConfig().getDouble("dotist-prestige")) {
			if(!p.hasPermission(DotManager.instance().getConfig().getString("dotist-permission"))) {
				DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("dotist-permission"));
			}
		} else {
			if(p.hasPermission(DotManager.instance().getConfig().getString("dotist-permission"))) {
				DotManager.permission.playerRemove(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("dotist-permission"));
			}
		}
		}
		//Tags
		if(value >= 105f) {
			if(!p.hasPermission(DotManager.instance().getConfig().getString("influencial-permission"))) {
				DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
						DotManager.instance().getConfig().getString("influencial-permission"));
			}
		}
	}
}