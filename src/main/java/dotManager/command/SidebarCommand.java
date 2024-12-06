package dotManager.command;

import dotManager.DotManager;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SidebarCommand implements CommandExecutor, TabExecutor
{

    private static final String[] boards = {"default", "compact"};

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        if (!(sender instanceof Player player))
        {
            return false;
        }

        if (args.length != 1) {
            // Toggle
            Objects.requireNonNull(TabAPI.getInstance().getScoreboardManager()).toggleScoreboard(TabAPI.getInstance().getPlayer(player.getUniqueId()), true);
            return true;
        }

        // Use board
        String board = args[0].toLowerCase();
        if (board.equals("?"))
        {
            Component list = DotManager.instance().messages().message("list-title").apply(new String[]{"Sidebars"});

            for(String key : boards)
            {
                Component entry = DotManager.instance().messages().message("sidebar-list-entry").apply(new String[]{key});
                list = list.append(entry);
            }

            Component footnote = DotManager.instance().messages().message("sidebar-list-footnote").apply(new String[]{});
            list = list.append(footnote);

            player.sendMessage(list);
            return true;
        }
        clearSidebarOption(player);DotManager.instance().addPermission(player, "sidebar." + board,true);

        Component success = DotManager.instance().messages().message("success-action").apply(new String[]{"applied sidebar " + board.toUpperCase()});
        player.sendMessage(success);

        return true;
    }

    private void clearSidebarOption(Player player)
    {
        for (String board : boards)
        {
            DotManager.instance().addPermission(player, "sidebar." + board,false);
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        List<String> ret = new ArrayList<>();
        if (args.length == 1)
        {
            ret.addAll(List.of(boards));
        }
        return ret;
    }
}