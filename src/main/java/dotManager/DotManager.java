package dotManager;
import java.io.File;
import java.util.logging.Level;

import dotarch.api.DotPlugin;
import dotarch.api.data.DotPlayer;
import net.kyori.adventure.platform.AudienceProvider;
import dotManager.command.*;
import dotManager.event.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import dotManager.utility.DotExpansion;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class DotManager extends DotPlugin
{
	private static DotManager self;
	public static double endClaimTax = 0;
	public static double bankBalanceCache = 0;
	protected boolean isMasterEnabled;
	protected boolean isClaimsEnabled;
	protected boolean isPrestigeEnabled;
	protected boolean isQualityEnabled;
	protected boolean isValueEnabled;
	public static float defaultPrestige;
	//Prestige values
	public static float dragonPrestige;
	public static float firstDragonPrestige;
	public static float witherPrestige;
	public static float banPrestige;
	// Quality percentages
	public static float poorQualityChance;
	public static float goodQualityChance;
	public static float greatQualityChance;
	public static float excellentQualityChance;
	public static float flawlessChance;
	public static Permission permission = null;
	public static Economy econ;
	public static MiniMessage mm;
	public static final String DIFFICULTY_PROPERTY = "player_difficulty";
	public static final double DEFAULT_DIFFICULTY = 1.0;
	public static LuckPerms luckperms;

	@Override
	public void onEnable() {
		super.onEnable();
		self=this;

		mm = MiniMessage.miniMessage();
		this.saveDefaultConfig();
		//Modules
		endClaimTax = this.getConfig().getDouble("end-tax-rate");
		isMasterEnabled = this.getConfig().getBoolean("modules.master");
		isPrestigeEnabled = this.getConfig().getBoolean("modules.prestige");
		isQualityEnabled = this.getConfig().getBoolean("modules.quality");
		isValueEnabled = this.getConfig().getBoolean("modules.value");
		getServer().getPluginManager().registerEvents(new MasterListener(), this);
		getServer().getPluginManager().registerEvents(new PrestigeListener(), this);
		getServer().getPluginManager().registerEvents(new QualityListener(), this);
		getServer().getPluginManager().registerEvents(new MCMMOListener(), this);
		getServer().getPluginManager().registerEvents(new MobsListener(), this);
		//PAPI
		 if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
             new DotExpansion(this).register();
		 }
		 //perms
		 setupPermissions();
		//Prestige values
		        
		defaultPrestige = (float) this.getConfig().getDouble("prestige-values.default-prestige");
		dragonPrestige = (float) this.getConfig().getDouble("prestige-values.dragon-prestige");
		witherPrestige = (float) this.getConfig().getDouble("prestige-values.wither-prestige");
		banPrestige = (float) this.getConfig().getDouble("prestige-values.ban-prestige");
		//Economic values
		//ValueCalculator.values = getConfig().getConfigurationSection("economy-values").getValues(false);
		//Quality chances
		poorQualityChance = (float) this.getConfig().getDouble("quality-chances.poor");
		goodQualityChance = (float) this.getConfig().getDouble("quality-chances.good");
		greatQualityChance = (float) this.getConfig().getDouble("quality-chances.great");
		excellentQualityChance = (float) this.getConfig().getDouble("quality-chances.excellent");
		flawlessChance = (float) this.getConfig().getDouble("quality-chances.flawless");

		//Commands
		this.getCommand("appraise").setExecutor(new AppraiseCommand());
		this.getCommand("appraiseall").setExecutor(new AppraiseAllCommand());
		this.getCommand("supersmite").setExecutor(new SmiteCommand());
		this.getCommand("bite").setExecutor(new BiteCommand());
		this.getCommand("fish").setExecutor(new FishCommand());

		var sidebar = new SidebarCommand();
		this.getCommand("sidebar").setExecutor(sidebar);
		this.getCommand("sidebar").setTabCompleter(sidebar);

		var difficulty = new DifficultyCommand();
		this.getCommand("difficulty").setExecutor(difficulty);
		this.getCommand("difficulty").setTabCompleter(difficulty);
		setupEconomy();
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
			luckperms = provider.getProvider();
		}
		getLogger().info("DotManager enabled");
		
	}
	
	public static DotManager instance()
	{
		return self;
	}

	private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	@Override
	public void onDisable()
	{
		getLogger().info("DotManager disabled");
	}

	@Override
	public void handleLoadedPlayer(DotPlayer dotPlayer)
	{
		// Add difficulty property
		dotPlayer.initializeProperty(DotManager.DIFFICULTY_PROPERTY, String.valueOf(DotManager.DEFAULT_DIFFICULTY), true);
	}

	 private boolean setupEconomy()
	 {
		if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	 }

	    public Economy getEconomy() {
	        return econ;
	    }

	public void addPermission(Player player, String permission, boolean value) {
		User user = luckperms.getPlayerAdapter(Player.class).getUser(player);
		// Add the permission
		user.data().add(Node.builder(permission)
				.value(value).build());

		// Now we need to save changes.
		luckperms.getUserManager().saveUser(user);
	}
}
