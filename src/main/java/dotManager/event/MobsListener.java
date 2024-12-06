package dotManager.event;

import dotarch.api.DotAPI;
import dotManager.DotManager;
import dotManager.utility.Util;
import dotarch.api.data.DotPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;
import java.util.Random;

public class MobsListener implements Listener {

    private static final Random random = new Random();

    // Add mob buffs for MCMMO level and tag for killing warden in 1 hit
    @EventHandler
    public void onEntityDamageEntityEvent(EntityDamageByEntityEvent e)
    {
        if(
                e.getEntity() instanceof Warden warden
                        && e.getDamager() instanceof Player player
                        && warden.getHealth() >= Objects.requireNonNull(warden.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue() && e.getFinalDamage() >= warden.getHealth())
        {
            // Killed in one hit from full health
            if(player.getInventory().getItemInMainHand().getType() == Material.MACE)
            {
                // #Crush
                if(!player.hasPermission("tags.crush"))
                {
                    DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "tags.crush");
                    player.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §2#Crush"));
                }
            }
            else
            {
                // #Overpowered
                if(!player.hasPermission("tags.overpowered"))
                {
                    DotManager.permission.playerAdd(null, DotManager.instance().getServer().getOfflinePlayer(player.getUniqueId()), "tags.overpowered");
                    player.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(" §7You unlocked §da new tag§7, see §d/tags! Tag: §2#Overpowered"));
                }
            }

        }
        else
        {
            if (e.getEntity() instanceof Player player)
            {
                DotPlayer dotPlayer = DotAPI.getInstance().players().get(player);

                var playerDifficulty = Double.parseDouble(dotPlayer.getProperty(DotManager.DIFFICULTY_PROPERTY, "1.0"));

                var playerLocation = player.getLocation();
                var enemyLocation = e.getDamager().getLocation();
                var distance = playerLocation.distance(enemyLocation);
                var powerLevel = Util.getPowerLevel(player);
                var damage = e.getDamage();

                switch(e.getDamager())
                {
                    case Warden ignored:
                        e.setDamage(playerDifficulty * (e.getDamage() + (Util.getPowerLevel(player) / (500 + (25 * distance)))));
                        break;
                    case Wither ignored:
                        int a = random.nextInt(20000);
                        if(a < powerLevel){
                            // Trigger
                            float x = random.nextFloat(5);
                            float healingFactor = ((x * x * x) + 0.01f) / 25;
                            e.setDamage(damage - healingFactor);
                            player.setHealth(player.getHealth() - healingFactor);
                            player.playSound(player, Sound.ENTITY_WITHER_AMBIENT, 1, 2.0f);
                            player.spawnParticle(Particle.DAMAGE_INDICATOR, playerLocation, Math.round(healingFactor * 10));
                        }
                        break;

                    case Skeleton ignored:
                        e.setDamage(playerDifficulty * rangedDamageModifier(damage,powerLevel,distance));
                        break;
                    case Stray ignored:
                        e.setDamage(playerDifficulty * rangedDamageModifier(damage,powerLevel,distance));
                        break;
                    case Bogged ignored:
                        e.setDamage(playerDifficulty * rangedDamageModifier(damage,powerLevel,distance));
                        break;
                    case Pillager ignored:
                        e.setDamage(playerDifficulty * rangedDamageModifier(damage,powerLevel,distance));
                        break;
                    default:
                        e.setDamage(playerDifficulty * defaultDamageModifier(damage, powerLevel, distance));
                }
            }

        }
    }


    private static final float MODIFIER_SCALER = 1000.0f;

    public static double distancePenalty(double distance)
    {
        return distance / 5.7f;
    }

    public static double defaultDamageModifier(double baseDamage, double powerLevel, double distance)
    {
        double calc_distance = 0;
        if (distance < 1)
        {
            calc_distance = 1.0;
        }
        else
        {
            calc_distance = distance;
        }
        return baseDamage + ((((powerLevel / 4)) / (distancePenalty(calc_distance))) / MODIFIER_SCALER);
    }


    public static double distanceReward(double distance)
    {
        return distance / 1.4;
    }

    public static double rangedDamageModifier(double baseDamage, double powerLevel, double distance)
    {
        double calc_distance = 0;
        if (distance < 1)
        {
            calc_distance = 1;
        }
        else
        {
            calc_distance = distance;
        }
        return baseDamage + ((((powerLevel / 4)) * (distanceReward(calc_distance))) / MODIFIER_SCALER);
    }
}
