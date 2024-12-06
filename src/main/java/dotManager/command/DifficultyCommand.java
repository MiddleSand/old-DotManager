package dotManager.command;

import com.gmail.nossr50.mcmmo.kyori.adventure.text.ComponentLike;
import dotManager.DotManager;
import dotarch.api.DotAPI;
import me.neznamy.tab.api.TabAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.PlatformAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

import static java.util.Map.entry;

public class DifficultyCommand implements CommandExecutor, TabExecutor
{

    public static final Map difficulties = Map.ofEntries(
            entry("relaxed", 0.5),
            entry("natural", 1),
            entry("rough", 1.33),
            entry("harsh", 1.75),
            entry("brutal", 2),
            entry("ridiculous", 5)
    );

    public static final String[] difficultyOrder = {"relaxed", "natural", "rough","harsh","brutal","ridiculous"};


    public static final Map colors = Map.ofEntries(
            entry("relaxed", "c2ffc2"),
            entry("natural", "55ff55"),
            entry("rough", "ffea00"),
            entry("harsh", "ff9808"),
            entry("brutal", "ff3103"),
            entry("ridiculous", "ff0d8a")
    );

    public static final Map quips = Map.ofEntries(
            entry("relaxed", ". Smooooth sailing!"),
            entry("natural", ". Have fun!"),
            entry("rough", ". Have fun!"),
            entry("harsh", ". Have fun!"),
            entry("brutal", ". Have fun!"),
            entry("ridiculous", "... Best of luck to you.")
    );

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        String rawValue = "";
        if (args.length == 1) {
            rawValue = args[0].toLowerCase();
        }

        // Use board
        if (difficulties.containsKey(rawValue)) {
            DotAPI.getInstance().players().get(player).writeProperty(DotManager.DIFFICULTY_PROPERTY, String.valueOf(difficulties.get(rawValue)), true);
            Component success = DotManager.instance().messages().message("success-action").apply(new String[]{"applied difficulty"});
            Component difficulty = DotManager.instance().messages().message("difficulty-success-action").apply(new String[]{(String) colors.get(rawValue), rawValue.toUpperCase(), (String) quips.get(rawValue)});

            Component parsed = success.append(difficulty);
            player.sendMessage(parsed);
        } else {
            Component list = DotManager.instance().messages().message("list-title").apply(new String[]{"Difficulties"});

            for( String key : difficultyOrder)
            {
                Component entry = DotManager.instance().messages().message("difficulty-list-entry").apply(new String[]{key, colors.get(key).toString()});
                list = list.append(entry);
            }

            Component footnote = DotManager.instance().messages().message("difficulty-list-footnote").apply(new String[]{});
            list = list.append(footnote);

            player.sendMessage(list);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        List<String> ret = new ArrayList<>();
        if (args.length == 1)
        {
            ret.addAll(List.of(difficultyOrder));
        }
        return ret;
    }
}