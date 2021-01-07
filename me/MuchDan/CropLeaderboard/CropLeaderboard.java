package me.MuchDan.CropLeaderboard;

import me.MuchDan.CropLeaderboard.Commands.C_TabCompleater;
import me.MuchDan.CropLeaderboard.Commands.onCommandCropLeaderboard;
import me.MuchDan.CropLeaderboard.Commands.onCommandCrops;
import me.MuchDan.CropLeaderboard.EventListeners.onCropBreak;
import me.MuchDan.CropLeaderboard.EventListeners.onInventoryClick;
import me.MuchDan.CropLeaderboard.Util.IO;
import org.bukkit.plugin.java.JavaPlugin;

public class CropLeaderboard extends JavaPlugin {
    private IO Config;
    private IO data;

    @Override
    public void onEnable(){
        __initConfigs__();

        getCommand("Crops").setExecutor(new onCommandCrops(this));
        getCommand("CropLeaderboard").setExecutor(new onCommandCropLeaderboard(this));
        getCommand("CropLeaderboard").setTabCompleter(new C_TabCompleater());

        getServer().getPluginManager().registerEvents(new onCropBreak(this), this);
        getServer().getPluginManager().registerEvents(new onInventoryClick(this), this);
    }

    @Override
    public void onDisable() {
    }

    private void __initConfigs__(){
        Config = new IO(this, "CropLeaderboardConfig.yml");
        data = new IO(this, "data.yml");

        Config.getConfig().options().copyDefaults(false);
        data.getConfig().options().copyDefaults(false);

        Config.saveDefaultConfig();
        data.saveDefaultConfig();
    }

    public IO getConfigFile(){
        return Config;
    }
    public IO getData(){
        return data;
    }
}
