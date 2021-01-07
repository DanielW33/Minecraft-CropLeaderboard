package me.MuchDan.CropLeaderboard.Util;

import me.MuchDan.CropLeaderboard.CropLeaderboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class BuildGUI {
    private IO Config;
    private IO data;
    private Player player;

    public BuildGUI(CropLeaderboard plugin, Player player){
        Config = plugin.getConfigFile();
        data = plugin.getData();
        this.player = player;
    }
    public Inventory BuildInventory(){
        List<Item> ItemList = new ArrayList<>();
        getList(ItemList);
        Inventory inv = Bukkit.createInventory(player, Config.getConfig().getInt("gui-size"),
                ChatColor.translateAlternateColorCodes('&',Config.getConfig().getString("gui-name")));
        ItemList.forEach(item ->{
            inv.setItem(item.getPosition(),item.createBlock());
        });
        return inv;
    }
    private void getList(List<Item> ItemList){
        Config.getConfig().getConfigurationSection("gui").getKeys(false).forEach(var ->{
            Item item = new Item();
            item.setPosition(Integer.parseInt(var));
            item.setType(Material.getMaterial(Config.getConfig().getString("gui." + var + ".Material")));
            item.setDisplayName(ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("gui." + var + ".Item_Name")));
            item.setEnchanted(Config.getConfig().getBoolean("gui." + var + ".Enchanted"));
            item.setLore(Config.getConfig().getStringList("gui." + var + ".Item_Lore"));
            ItemList.add(item);
        });
        EnforceColorCodes(ItemList);

    }
    private void EnforceColorCodes(List<Item> ItemList){
        ItemList.forEach(Item->{
            List<String> list = new ArrayList<>();
            Item.getLore().forEach(var->{
                String newvar = ChatColor.translateAlternateColorCodes('&', var);
                if(newvar.contains("%broken%")){
                    String type = Item.getType().toString();
                    if(data.getConfig().contains("Players." + player.getUniqueId().toString() + "." + type)){
                        newvar = newvar.replace("%broken%",
                                String.valueOf(data.getConfig().getInt("Players." + player.getUniqueId().toString() + "." + type)));
                    }
                    else{
                        newvar = newvar.replace("%broken%", "0");
                    }
                }
                list.add(newvar);
            });
            Item.getLore().clear();
            Item.setLore(list);
        });
    }
}
