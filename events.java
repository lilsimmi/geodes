package me.simmi.geodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class events implements Listener
{
	String prefix = ChatColor.LIGHT_PURPLE.toString()+ChatColor.BOLD.toString()+"Geodes"
			+ChatColor.DARK_GRAY.toString()+ChatColor.BOLD.toString()+"> ";
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Action a = e.getAction();
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand().hasItemMeta())
		{
			ItemStack inhand = p.getInventory().getItemInMainHand();
			ItemStack offhand = p.getInventory().getItemInOffHand();

			
				if(a == Action.RIGHT_CLICK_AIR)
				{
					if(p.getInventory().getItemInOffHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE.toString()+ChatColor.BOLD.toString()+"Geode Cracker")
							&& inhand.getItemMeta().getDisplayName().equals(ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Advanced Geode"))
					{
						int inhandamt = p.getInventory().getItemInHand().getAmount();
						int crackeramt = p.getInventory().getItemInOffHand().getAmount();
						

						offhand.setAmount(crackeramt - 1);
						inhand.setAmount(inhandamt -1);
						p.sendMessage(prefix+ChatColor.WHITE+"Defiened Geode");
	                      p.playSound(p.getLocation(), Sound.ENTITY_HORSE_ARMOR, 3, 1);
	                      
	                      Random random = new Random();
	      					int r = random.nextInt(3);
	      					
	      					
	                      ItemStack geodeScraps = new ItemStack(Material.NETHERITE_SCRAP, r);
	                      ItemMeta scrapMeta = geodeScraps.getItemMeta();
	                      scrapMeta.setDisplayName(ChatColor.GOLD+"Geode Scrap's");
	                      geodeScraps.setItemMeta(scrapMeta);
	                      
	                     
	      					
	                      p.getInventory().addItem(geodeScraps);
						
					}
					else if (!offhand.getItemMeta().getDisplayName().equals(p.getInventory().getItemInOffHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE.toString()+ChatColor.BOLD.toString()+"Geode Cracker")))
					{
						String offhanditem = p.getInventory().getItemInOffHand().toString();

						
						p.sendMessage(prefix+ChatColor.RED+"Hold cracker in off hand and geode in main hand!");
						

					}
				}
			
		}

		
	}
	@EventHandler
	public void onbreak(BlockBreakEvent e)//fossils
	{
		ItemStack geode = new ItemStack(Material.CHORUS_FRUIT, 1);
		ItemMeta geodeMeta = geode.getItemMeta();
		geodeMeta.setDisplayName(ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Advanced Geode");
	    List<String> geodeLore = new ArrayList<String>();
	    geodeLore.add(ChatColor.WHITE+"Right-Click "+ChatColor.GRAY+"(requires geode cracker in offhand)");
	    geodeMeta.setLore(geodeLore);
	    geode.setItemMeta(geodeMeta);
	    
		Block b = e.getBlock();
		if (b.getType().equals(Material.DIAMOND_ORE) || b.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)
				|| b.getType().equals(Material.GOLD_ORE) || b.getType().equals(Material.DEEPSLATE_GOLD_ORE))		
		{
			Random random = new Random();
			int r = random.nextInt(100);
	        if (r >= 95) //%5
	        {
	        	b.getWorld().dropItemNaturally(b.getLocation(), geode);
	        }
	        
		}
	}
}
