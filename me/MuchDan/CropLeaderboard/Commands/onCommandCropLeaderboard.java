package me.MuchDan.CropLeaderboard.Commands;

import me.MuchDan.CropLeaderboard.CropLeaderboard;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class onCommandCropLeaderboard implements CommandExecutor {
    private CropLeaderboard plugin;

    public onCommandCropLeaderboard(CropLeaderboard plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("CropLeaderboard") && args.length == 0){
            Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&m========== &r&bCropLeaderboard &m&f=========="));
            Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"/CropLeaderboard &creload  &r|  Refreshes all config and data files"));
            Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"/Crops  |  Opens GUI to view tracked Crops"));
            Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&m====================================="));
        }
        else if(args[0].equalsIgnoreCase("Reload")){
            plugin.getConfigFile().reloadConfig();
            plugin.getData().reloadConfig();
            Sender.sendMessage(ChatColor.GREEN + "Config Files have been reloaded Successfully.");
        }
        return false;
    }

}
