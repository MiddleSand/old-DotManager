package dotManager.event;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dotManager.DotManager;
import dotManager.utility.AppraisalResult;

public class QualityListener implements Listener
{	
	static Random randalTheRandom = new Random();
	/** Takes one item from given itemstack, adds appraisal, and returns the item.
	 * 
	 * Appraised items will have lore - 
	 "Poor Quality" - Lowest tier. Debuffs. 
	 "Average Quality" - Normal tier. No effect.
	 "Good Quality" - Slightly higher tier. Small buffs, i.e. Sharp 1 on swords, Eff 1 on picks, Prot 1 on armor, etc.
	 "Great Quality" - Higher tier. Bigger but still small buffs. Sharp 3 on swords, eff 3 on picks, prot 3 on armor, etc.
	 "Excellent Quality" - Second-highest tier. Moderate buffs. Sharp 5 + fire on swords, eff 5 + fortune on picks, max prot + one other type of prot on armor, etc.
	 "Flawless" - Highest tier. Large buffs, as in max enchant+ without mending, unbreak, etc. */
	public static AppraisalResult applyAppraisal(ItemStack in) {
		if(in.getItemMeta().hasLore()) {
			return new AppraisalResult();
		}
		if(in.getType() != Material.DIAMOND && in.getType() != Material.GOLD_INGOT && in.getType() != Material.IRON_INGOT && in.getType() != Material.NETHERITE_INGOT && in.getType() != Material.LEATHER) {
			return new AppraisalResult();
		}
		ItemStack out = in.clone();
		ItemMeta im = in.getItemMeta();
		in.setAmount(in.getAmount()-1);
		out.setAmount(1);
		AppraisalResult result = new AppraisalResult();
		//*deeep inhale*
		float fchance = (randalTheRandom.nextFloat() * 100);
		float echance = (randalTheRandom.nextFloat() * 100);		
		float ggchance = (randalTheRandom.nextFloat() * 100);
		float gchance = (randalTheRandom.nextFloat() * 100);
		float pchance = (randalTheRandom.nextFloat() * 100);
		List<String> lore = new ArrayList<>();
		if (DotManager.flawlessChance >= fchance) {
			lore.add("Flawless");
			lore.add("Crafts Flawless items.");	
			im.addEnchant(Enchantment.LOYALTY, 4, true);
			im.setDisplayName("§5Flawless " + getStackName(out));
			im.setLocalizedName("§5Flawless " + getStackName(out));
			result.qualityDescriptor = "§5Flawless ";
		} else if (DotManager.excellentQualityChance >= echance) {
			lore.add("Excellent Quality");
			lore.add("Crafts Excellent items.");	
			im.addEnchant(Enchantment.LOYALTY, 3, true);
			im.setDisplayName("§bExcellent " + getStackName(out));
			im.setLocalizedName("§bExcellent " + getStackName(out));
			result.qualityDescriptor = "§bExcellent ";
		} else if (DotManager.greatQualityChance >= ggchance) {
			lore.add("Great Quality");
			lore.add("Crafts Great items.");	
			im.addEnchant(Enchantment.LOYALTY, 2, true);
			im.setDisplayName("§2Great " + getStackName(out));
			im.setLocalizedName("§2Great " + getStackName(out));
			result.qualityDescriptor = "§2Great ";
		} else if (DotManager.goodQualityChance >= gchance) {
			lore.add("Good Quality");
			lore.add("Crafts Good items.");	
			im.addEnchant(Enchantment.LOYALTY, 1, true);
			im.setDisplayName("§aGood " + getStackName(out));
			im.setLocalizedName("§aGood " + getStackName(out));
			result.qualityDescriptor = "§aGood ";
		} else if (DotManager.poorQualityChance >= pchance) {
			lore.add("Poor Quality");
			lore.add("Crafts Poor items.");	
			im.addEnchant(Enchantment.LOYALTY, 1, true);
			im.setDisplayName("§7Poor " + getStackName(out));
			im.setLocalizedName("§7Poor " + getStackName(out));
			result.qualityDescriptor = "§7Poor ";
		} else {
			//Default to average if poor chance fails	
			lore.add("Average Quality");
			lore.add("Crafts normal items.");		
			im.setLocalizedName("§fAverage " + getStackName(out));
			im.setDisplayName("§fAverage " + getStackName(out));
			result.qualityDescriptor = "§fAverage ";
		}
		
		im.setLore(lore);
		out.setItemMeta(im);
		result.item = out;
		return result;
	}
	public static String getStackName(ItemStack is) {
		return toTitleCase(is.getType().name().replace("_", " ").toLowerCase());
	}
	public static String toTitleCase(String givenString) {
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}  
}