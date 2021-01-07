package me.MuchDan.CropLeaderboard.Commands;

import me.MuchDan.CropLeaderboard.CropLeaderboard;
import me.MuchDan.CropLeaderboard.Util.BuildGUI;
import me.MuchDan.CropLeaderboard.Util.IO;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class onCommandCrops implements CommandExecutor {
    private CropLeaderboard plugin;
    private IO Config;
    private IO data;

    public onCommandCrops(CropLeaderboard plugin){
        this.plugin = plugin;
        this.Config = plugin.getConfigFile();
        this.data = plugin.getData();
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
        if(!label.equalsIgnoreCase("Crops")){
            return false;
        }
        if(!(Sender instanceof Player)){
            Sender.sendMessage(ChatColor.RED + "You Must be a player to view crop statistics.");
            return false;
        }
        Player player = (Player) Sender;
        BuildGUI gui = new BuildGUI(plugin, player);
        player.openInventory(gui.BuildInventory());
        return false;
    }
}
