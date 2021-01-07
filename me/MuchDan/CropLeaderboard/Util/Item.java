package me.MuchDan.CropLeaderboard.Util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int Position;
    private Material type;
    private String DisplayName;
    private boolean Enchanted;
    private List<String> lore;

    public Item(){
        lore = new ArrayList<>();
    }

    public ItemStack createBlock(){
        ItemStack itemStack;
        if(type.equals(Material.CARROTS)){
            itemStack = new ItemStack(Material.CARROT, 1);
        }
        else if(type.equals(Material.POTATOES)){
            itemStack = new ItemStack(Material.POTATO, 1);
        }
        else{
            itemStack = new ItemStack(type, 1);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(DisplayName);
        if(Enchanted){
            itemMeta.addEnchant(Enchantment.MENDING, 1, false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public void setPosition(int Position){
        this.Position = Position;
    }
    public void setType(Material type){
        this.type = type;
    }
    public void setDisplayName(String DisplayName){
        this.DisplayName = DisplayName;
    }
    public void setEnchanted(boolean Enchanted){
        this.Enchanted = Enchanted;
    }
    public void setLore(List<String> lore){
        this.lore = lore;
    }
    public int getPosition(){
        return Position;
    }
    public Material getType(){
        return type;
    }
    public String getDisplayName(){
        return DisplayName;
    }
    public boolean getEnchanted(){
        return Enchanted;
    }
    public List<String> getLore(){
        return lore;
    }
}
