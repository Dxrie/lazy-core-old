package lazycore.lazycore.commands;

import lazycore.lazycore.FileConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FileReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {
                FileConfig.reload();
            }
        } else {
            FileConfig.reload();
        }

        return true;
    }
}
