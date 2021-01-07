package me.MuchDan.CropLeaderboard.EventListeners;

import me.MuchDan.CropLeaderboard.CropLeaderboard;
import me.MuchDan.CropLeaderboard.Util.Leaderboard;
import me.MuchDan.CropLeaderboard.Util.IO;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class onInventoryClick implements Listener {
    private CropLeaderboard plugin;
    private IO Config;
    private IO data;
    private String InventoryName;

    public onInventoryClick(CropLeaderboard plugin){
        this.plugin = plugin;
        Config = plugin.getConfigFile();
        data = plugin.getData();
        InventoryName = Config.getConfig().getString("gui-name");
    }

    @EventHandler
    public void ClickGui(InventoryClickEvent Event) {
        if (Event.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase(InventoryName)) {
            Event.setCancelled(true);

            if (data.getConfig().contains("Players")) {
                int slot = Event.getSlot();
                String type = SlotInfo(slot);
                List<Leaderboard> list = BuildList(type);
                BubbleSort(list);
                PrintLeaderBoard(list, type, (Player) Event.getWhoClicked());
                if(Config.getConfig().getStringList("TrackedCrops").contains(type))
                    Event.getWhoClicked().closeInventory();
            }
        }
    }
    private String SlotInfo(int slot){
        return Config.getConfig().getString("gui." + slot + ".Material");
    }
    private List<Leaderboard> BuildList(String type){
        List<Leaderboard> list = new ArrayList<>();
        data.getConfig().getConfigurationSection(".Players").getKeys(false).forEach(UUID ->{
            if(data.getConfig().contains("Players." + UUID + "." + type)) {
                Leaderboard pos = new Leaderboard();
                pos.setUUID(UUID);
                pos.setValue(data.getConfig().getInt("Players." + UUID + "." + type));
                list.add(pos);
            }
        });

        return list;
    }
    private void BubbleSort(List<Leaderboard> list){
        boolean swap;
        Leaderboard temp;

        do{
            swap = false;

            for(int i = 0; i < list.size() -1; i++){
                if(list.get(i).getValue() < list.get(i + 1).getValue()){
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i+1, temp);
                    swap = true;
                }
            }
        }while(swap);
    }
    private void PrintLeaderBoard(List<Leaderboard> list, String Type, Player Sender) {
        if (Config.getConfig().getStringList("TrackedCrops").contains(Type)) {
            List<String> Leaderboard = new ArrayList<>();
            String Headerfooter = Config.getConfig().getString("leaderboard-headerfooter");
            String Title = Config.getConfig().getString("leaderboard-title");

            Leaderboard.add(ChatColor.translateAlternateColorCodes('&', Headerfooter));
            Leaderboard.add(" ");
            Leaderboard.add(ChatColor.translateAlternateColorCodes('&', Title + "&r&7: " + Type));
            Leaderboard.add(" ");
            int count = 1;
            for (int i = 0; i < list.size(); i++) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(list.get(i).getUUID()));
                Leaderboard.add(ChatColor.translateAlternateColorCodes('&', "&l&e#" + count + " &r&7" + player.getName()
                        + "&r&8 (" + list.get(i).getValue() + ")"));
                if (count == 10) {
                    break;
                }
                count++;
            }
            Leaderboard.add(" ");

            Leaderboard.add(ChatColor.translateAlternateColorCodes('&', Headerfooter));

            for (String line : Leaderboard) {
                Sender.sendMessage(line);
            }
        }
    }
}
