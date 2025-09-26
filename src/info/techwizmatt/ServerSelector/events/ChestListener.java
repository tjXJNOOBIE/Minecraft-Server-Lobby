package info.techwizmatt.ServerSelector.events;

import java.util.Arrays;

import info.techwizmatt.ServerCore.Main;
import info.techwizmatt.ServerSelector.Keys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.API.Players;
import info.techwizmatt.ServerSelector.GUI;
import net.md_5.bungee.api.ChatColor;




public class ChestListener implements Listener{


		@EventHandler
		public void InventoryCloseEvent(org.bukkit.event.inventory.InventoryCloseEvent event){
			
			
			
			
			String title = ChatColor.stripColor(event.getInventory().getTitle());
			if(title.toLowerCase().contains("crate")) {
				if (GUI.ItemRotateTaskIds.containsKey((Player) event.getPlayer())) {
					Bukkit.getScheduler().cancelTask(GUI.ItemRotateTaskIds.get((Player) event.getPlayer()));
					GUI.ItemRotateTaskIds.remove((Player) event.getPlayer());
				} else {
					Player user = (Player) event.getPlayer();
					ItemStack rewardItem = event.getInventory().getItem(22);

					Players.SubtractCrateKey(user.getUniqueId().toString());

					user.getInventory().remove(0);

					int NumberOfCrateKeys = Players.GetNumberOfCrateKeys(user.getUniqueId().toString());
					ItemStack CrateKey = new ItemStack(Material.TRIPWIRE_HOOK, NumberOfCrateKeys);
					ItemMeta CrateKeyMeta = CrateKey.getItemMeta();
					CrateKeyMeta.setDisplayName(ChatColor.DARK_PURPLE + "Crate Key");
					CrateKeyMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click me on a Drop Crate", ChatColor.GRAY + "You have " + ChatColor.GOLD + NumberOfCrateKeys + ChatColor.GRAY + " Crate Keys left."));
					CrateKey.setItemMeta(CrateKeyMeta);


					user.getInventory().setItem(0, CrateKey);



					}
				}
			}
		}
		
		
		
		
	
	

