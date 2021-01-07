package me.MuchDan.CropLeaderboard.EventListeners;

import me.MuchDan.CropLeaderboard.CropLeaderboard;
import me.MuchDan.CropLeaderboard.Util.IO;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class onCropBreak implements Listener {
    private static List<String> Crops;
    private CropLeaderboard plugin;
    private IO Config;
    private IO data;

    public onCropBreak(CropLeaderboard plugin){
        this.plugin = plugin;
        Config = plugin.getConfigFile();
        data = plugin.getData();

        Crops = new ArrayList<>();
        Crops = Config.getConfig().getStringList("TrackedCrops");
    }

    @EventHandler
    public void CropBreak(BlockBreakEvent Event){
        String type = Event.getBlock().getType().toString();
        if(Crops.contains(type)) {
            if (type.equals("SUGAR_CANE") || type.equals("BAMBOO") || type.equals("CACTUS") || type.equals("KELP")) {
                setBlock(Event.getPlayer(), type, MultiLevelBlock(Event.getBlock().getLocation()));
            }
            else {
                setBlock(Event.getPlayer(), type, 1);
            }
        }
    }
    private int MultiLevelBlock(Location loc){
        String type = loc.getBlock().getType().toString();
        int y = (int)Math.round(loc.getY());
        int count = 0;
        while(loc.getBlock().getType().toString().equals(type)){
            count++;
            y += count;
            loc.setY(y);
        }
        return count;
    }
    private void setBlock(Player player, String type, int Count){
        String UUID = player.getUniqueId().toString();

        if(data.getConfig().contains("Players." + UUID + "." + type)){
            Count += data.getConfig().getInt("Players." + UUID + "." + type);
        }
        data.getConfig().set("Players." + UUID + "." + type, Count);
        data.saveConfig();
    }
}
