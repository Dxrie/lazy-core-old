package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCreative implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (FileConfig.checkStatus(player)) {
                if (args.length == 0) {
                    player.setGameMode(GameMode.CREATIVE);
                } else if (args.length == 1) {
                    Player gmsPlayer = Bukkit.getPlayerExact(args[0]);

                    if (gmsPlayer instanceof Player) {
                        gmsPlayer.setGameMode(GameMode.CREATIVE);
                    } else {
                        sender.sendMessage(ChatColor.RED + args[0] + " isn't a valid player");
                    }
                }
            }
        } else {
            if (args.length == 1) {
                Player gmsPlayer = Bukkit.getPlayerExact(args[0]);

                if (gmsPlayer instanceof Player) {
                    gmsPlayer.setGameMode(GameMode.CREATIVE);
                } else {
                    sender.sendMessage(args[0] + " isn't a valid player");
                }
            }
        }

        return true;
    }
}
