package dotManager.utility;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.util.player.UserManager;
import dotManager.DotManager;
import org.bukkit.entity.Player;

public class Util {

	public static Integer getPowerLevel(Player player) {
		final McMMOPlayer user = UserManager.getPlayer(player);
		if (user == null) return 0;
		return user.getPowerLevel();
	}

	public static int getSkillsAt1k(Player player) {
		PrimarySkillType[] skills = PrimarySkillType.values();
		int returns = 0;
		for(int i = skills.length - 1; i >= 0; i--) {
			if(getRank(skills[i], player) >= 1000) {
				returns++;
			}
		}
		return returns;
	}

	public static Integer getRank(PrimarySkillType skill, Player player) {
		try {
			return ExperienceAPI.getLevel(player, skill);
		} catch (Exception ex) {
			return 0;
		}
	}
}
