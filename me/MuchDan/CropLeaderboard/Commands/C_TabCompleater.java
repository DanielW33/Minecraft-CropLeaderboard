package me.MuchDan.CropLeaderboard.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class C_TabCompleater implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender Sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("CropLeaderboard")){
            List<String> list  = new ArrayList<>();
            list.add("Reload");
            return list;
        }
        return null;
    }
}
