package me.simmi.geodes;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.simmi.geodes.files.DataManager;


public class main extends JavaPlugin implements Listener
{
	String prefix = ChatColor.LIGHT_PURPLE.toString()+ChatColor.BOLD.toString()+"Geodes"
			+ChatColor.DARK_GRAY.toString()+ChatColor.BOLD.toString()+"> ";
	FileConfiguration config = this.getConfig();
	public DataManager data;
	@Override
	public void onEnable()
	{
		this.data = new DataManager();
		Bukkit.getServer().getPluginManager().registerEvents(new events(), this);
        Bukkit.addRecipe(crackerRecipe());

	}
	@Override
	public void onDisable()
	{
		
	}
	
	
	ItemStack geode = new ItemStack(Material.CHORUS_FRUIT, 1);
	ItemMeta geodeMeta = geode.getItemMeta();
	
	ItemStack cracker = new ItemStack(Material.POPPED_CHORUS_FRUIT, 1);
	ItemMeta crackerMeta = cracker.getItemMeta();
	
	public ShapedRecipe crackerRecipe()
	{        
		 crackerMeta.setDisplayName(ChatColor.DARK_PURPLE.toString()+ChatColor.BOLD.toString()+"Geode Cracker");
		    List<String> crackerLore = new ArrayList<String>();
		    crackerLore.add(ChatColor.WHITE+"Used to crack "+ChatColor.GRAY+" Geodes");
		    crackerMeta.setLore(crackerLore);
		    cracker.setItemMeta(crackerMeta);
		    
        NamespacedKey key = new NamespacedKey(this, "popped_chorus_fruit");
		ShapedRecipe recipe = new ShapedRecipe(key, cracker);
		recipe.shape("iii","ini","iii");
		recipe.setIngredient('n', Material.POPPED_CHORUS_FRUIT);
		recipe.setIngredient('i', Material.DIAMOND);
		return recipe;
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {

		geodeMeta.setDisplayName(ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Advanced Geode");
	    List<String> geodeLore = new ArrayList<String>();
	    geodeLore.add(ChatColor.WHITE+"Right-Click "+ChatColor.GRAY+"(requires geode cracker in offhand)"
	    		+ "");
	    geodeMeta.setLore(geodeLore);
	    geode.setItemMeta(geodeMeta);
	    
	    crackerMeta.setDisplayName(ChatColor.DARK_PURPLE.toString()+ChatColor.BOLD.toString()+"Geode Cracker");
	    List<String> crackerLore = new ArrayList<String>();
	    crackerLore.add(ChatColor.WHITE+"Used to crack "+ChatColor.GRAY+" Geodes");
	    crackerMeta.setLore(crackerLore);
	    cracker.setItemMeta(crackerMeta);
	    
		if(!(sender instanceof Player))
		{
			sender.sendMessage(prefix+ "only players can use this command");
			return false;
		}
		else
		{
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("givegeode"))
			{
				p.getInventory().addItem(geode);
				p.sendMessage(prefix+ChatColor.WHITE+"Given Geode");
			}
			
			if(cmd.getName().equalsIgnoreCase("giveCracker"))
			{
				p.getInventory().addItem(cracker);
				p.sendMessage(prefix+ChatColor.WHITE+"Given Cracker");
			}
			

		}
		
		return true;
	}
	
	
}
