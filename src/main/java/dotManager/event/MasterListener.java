package dotManager.event;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import com.djrapitops.vaultevents.events.economy.PlayerWithdrawEvent;

import dotManager.DotManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class MasterListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		//try {DataManager.generateCacheForPlayer(player);} catch (Exception e) {//do nothing
		//}
		//TODO: setup key system
	}
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    }
	@EventHandler
	public void handleEconomisery(PlayerWithdrawEvent e) {
		if(e.getResponse().balance <= 0.01d)
		{
			if(!Bukkit.getPlayer(e.getOfflinePlayer().getUniqueId()).hasPermission("tags.economisery")) {
				DotManager.permission.playerAdd(null, e.getOfflinePlayer(), "tags.economisery");
			}
			
		}
	}

	@EventHandler
	public void stopAppraisedBlockCrafting(CraftItemEvent e) {
		if(e.getRecipe().getResult().getType() == Material.DIAMOND_BLOCK || e.getRecipe().getResult().getType() == Material.GOLD_BLOCK || e.getRecipe().getResult().getType() == Material.NETHERITE_BLOCK ||
			e.getRecipe().getResult().getType() == Material.IRON_BLOCK) {
			//It's a compressed material we're trying to craft. Check if it contains appraised materials, and if it does then we cancel the event.
			ItemStack[] stacks = e.getInventory().getStorageContents();
			for(int i = 0; i < stacks.length; i++) {
				if(stacks[i].hasItemMeta() && (stacks[i].getItemMeta().hasLore() || stacks[i].getItemMeta().hasEnchants())) {
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void handleStatTags(PlayerStatisticIncrementEvent e) {
		if(e.getMaterial() != null) {
			Material m = e.getMaterial();
			if(e.getNewValue() >= 10000) {
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("treeyeeter-permission")) && (m == Material.OAK_LOG || m == Material.SPRUCE_LOG || m == Material.BIRCH_LOG || m == Material.JUNGLE_LOG || m == Material.DARK_OAK_LOG 
						|| m == Material.ACACIA_LOG || m == Material.CRIMSON_STEM || m == Material.WARPED_STEM)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("treeyeeter-permission"));
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §2#TreeYeeter"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("earthmover-permission")) && (m == Material.GRAVEL || m == Material.DIRT || m == Material.GRASS_BLOCK || m == Material.COARSE_DIRT || m == Material.SAND
						|| m == Material.SOUL_SAND || m == Material.SOUL_SOIL || m == Material.WARPED_STEM || m == Material.PODZOL || m == Material.MYCELIUM)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("earthmover-permission"));	
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §c#EarthMover"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("drill-permission")) && (m == Material.STONE || m == Material.GRANITE || m == Material.DIORITE || m == Material.ANDESITE || m == Material.NETHERRACK
						|| m == Material.BLACKSTONE || m == Material.BASALT || m == Material.END_STONE)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("drill-permission"));
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §a#Drill"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
			} else
			if(e.getNewValue() >= 1000) {
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("woodcutter-permission")) && (m == Material.OAK_LOG || m == Material.SPRUCE_LOG || m == Material.BIRCH_LOG || m == Material.JUNGLE_LOG || m == Material.DARK_OAK_LOG 
						|| m == Material.ACACIA_LOG || m == Material.CRIMSON_STEM || m == Material.WARPED_STEM)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("woodcutter-permission"));
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §2#Woodcutter"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("excavator-permission")) && (m == Material.GRAVEL || m == Material.DIRT || m == Material.GRASS_BLOCK || m == Material.COARSE_DIRT || m == Material.SAND
						|| m == Material.SOUL_SAND || m == Material.SOUL_SOIL || m == Material.WARPED_STEM || m == Material.PODZOL || m == Material.MYCELIUM)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("excavator-permission"));	
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §c#Excavator"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
				if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("miner-permission")) && (m == Material.STONE || m == Material.GRANITE || m == Material.DIORITE || m == Material.ANDESITE || m == Material.NETHERRACK
						|| m == Material.BLACKSTONE || m == Material.BASALT || m == Material.END_STONE)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							DotManager.instance().getConfig().getString("miner-permission"));
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §a#Miner"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
			}
		}
		if(e.getEntityType() != null) {
			if(e.getNewValue() >= 100) {
				if(!e.getPlayer().hasPermission("tags.bossremover") && (e.getEntityType() == EntityType.WITHER)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							"tags.bossremover");
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							"tags.bossslayer");
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §4#BossRemover"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
			} else if(e.getNewValue() >= 50) {
				if(!e.getPlayer().hasPermission("tags.bossslayer") && (e.getEntityType() == EntityType.WITHER)) {
					DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
							"tags.bossslayer");
					e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §c#BossSlayer"));
					//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
				}
			}
		}
	}
	@EventHandler
	public void handleChatTags(AsyncPlayerChatEvent e) {
		if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("pepsi-permission")) && ((e.getMessage().toLowerCase().contains("i love") || e.getMessage().toLowerCase().contains("i like")) && (e.getMessage().toLowerCase().contains("pepsi")))) {
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
					DotManager.instance().getConfig().getString("pepsi-permission"));
			e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §f#§bPepsiEnthusiast"));
			//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
		}
		if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("coke-permission")) && ((e.getMessage().toLowerCase().contains("i love") || e.getMessage().toLowerCase().contains("i like")) && (e.getMessage().toLowerCase().contains("coca cola")))) {
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
					DotManager.instance().getConfig().getString("coke-permission"));
			e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §f#§cCocaColaEnthusiast"));
			//DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
		}
		if(!e.getPlayer().hasPermission(DotManager.instance().getConfig().getString("self-permission")) && e.getPlayer().getName().equals(e.getMessage()))
		{
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), DotManager.instance().getConfig().getString("self-permission"));
		}
	//TODO: Not yet!!	//if(!e.getPlayer().hasPermission("tags.pizzaconsumer") && (e.getMessage().toLowerCase().contains("pizza"))) {
		//	DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getPlayer().getUniqueId()), 
		//			"tags.pizzaconsumer");
		//	e.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §f#§b§lPizzaConsumer"));
		//	DataManager.adjustPlayerPrestige(e.getPlayer().getUniqueId(), 0.5f);
		//}
	}
	
	@EventHandler
	public void handleDeaths(PlayerDeathEvent e) {
	//	DataManager.adjustPlayerPrestige(e.getEntity().getUniqueId(), (float) DotManager.instance().getConfig().getDouble("prestige-values.death-prestige"));
		
		if(e.getEntity().getLastDamage() >= 20.00 && e.getEntity().getKiller() != null && !e.getEntity().getKiller().hasPermission(DotManager.instance().getConfig().getString("dunkedon-permission"))) {
			Player p = (Player) e.getEntity().getKiller();
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(p.getUniqueId()), 
					DotManager.instance().getConfig().getString("dunkedon-permission"));
			((Player) e.getEntity().getKiller()).spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §f#DunkedOn"));
			//DataManager.adjustPlayerPrestige(p.getUniqueId(), 0.5f);
		}
		
		ItemStack helm = e.getEntity().getInventory().getHelmet();
		ItemStack chest = e.getEntity().getInventory().getChestplate();
		ItemStack legs = e.getEntity().getInventory().getLeggings();
		ItemStack boots = e.getEntity().getInventory().getBoots();
		
		if(e.getEntity().getLastDamageCause().getCause() == DamageCause.LAVA && !e.getEntity().hasPermission(DotManager.instance().getConfig().getString("noob-permission")) && !helm.getEnchantments().isEmpty() && !chest.getEnchantments().isEmpty()
				&& !legs.getEnchantments().isEmpty() && !boots.getEnchantments().isEmpty()) {
			DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(e.getEntity().getUniqueId()), 
					DotManager.instance().getConfig().getString("noob-permission"));
			e.getEntity().spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §f#§5NOOB"));
			//DataManager.adjustPlayerPrestige(e.getEntity().getUniqueId(), 0.5f);
		}
	}
	
}