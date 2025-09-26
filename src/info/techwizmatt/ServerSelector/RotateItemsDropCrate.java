package info.techwizmatt.ServerSelector;

import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerSelector.events.PlayerListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RotateItemsDropCrate implements Runnable{

	Player currentPlayer = null;
	int RoatationTimes = 0;
	int count = 0;
	boolean SlowDownRest;
	
	public RotateItemsDropCrate(Player user, int RotationTimes, boolean SlowDown) {
		currentPlayer = user;		
		RoatationTimes = RotationTimes;
		
		SlowDownRest = SlowDown;
	}
	
	
	@Override
	public void run() {
		InventoryView inv = currentPlayer.getOpenInventory();
		
		ItemStack LastItem = inv.getItem(26);
		
		currentPlayer.getOpenInventory().setItem(26, inv.getItem(25));
		currentPlayer.getOpenInventory().setItem(25, inv.getItem(24));
		currentPlayer.getOpenInventory().setItem(24, inv.getItem(23));
		currentPlayer.getOpenInventory().setItem(23, inv.getItem(22));
		currentPlayer.getOpenInventory().setItem(22, inv.getItem(21));
		currentPlayer.getOpenInventory().setItem(21, inv.getItem(20));	
		currentPlayer.getOpenInventory().setItem(20, inv.getItem(19));
		currentPlayer.getOpenInventory().setItem(19, inv.getItem(18));
		currentPlayer.getOpenInventory().setItem(18, LastItem);
		
		currentPlayer.playSound(currentPlayer.getLocation(), Sound.CLICK, 1, 1);
		
		count++;


		if(!SlowDownRest){
			int checker = count + 5;
			if(checker >= RoatationTimes){
			Bukkit.getScheduler().cancelTask(GUI.ItemRotateTaskIds.get(currentPlayer));
			GUI.ItemRotateTaskIds.remove(currentPlayer);
			GUI.RotateItems(currentPlayer, 5, 5, true);
			}
		}
		
		
		if(count >= RoatationTimes){

			currentPlayer.playSound(currentPlayer.getLocation(), Sound.ARROW_HIT, 1, 1);
			ItemStack rewardItem = currentPlayer.getOpenInventory().getItem(22);
			if (ChatColor.stripColor(rewardItem.getItemMeta().getDisplayName().toLowerCase()).contains("nothing")) {
				currentPlayer.sendMessage(ChatColor.GREEN + "You Received: " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Nothing, Better luck next time!");
			} else {
				currentPlayer.sendMessage(ChatColor.GREEN + "You Received: " + rewardItem.getItemMeta().getDisplayName());
				currentPlayer.playSound(currentPlayer.getLocation(), Sound.LEVEL_UP,0.5f,0.5f);

				//Give Items...
				String ItemName = ChatColor.stripColor(rewardItem.getItemMeta().getDisplayName()).toLowerCase();
				//Tokens
				if (ItemName.contains("coins")) {
					if (ItemName.contains("+5")) {
						CoinAPI.AddTokens(currentPlayer, 5);
					} else if (ItemName.contains("+10")) {
						CoinAPI.AddTokens(currentPlayer, 10);
					} else if (ItemName.contains("+25")) {
						CoinAPI.AddTokens(currentPlayer, 25);
					} else if (ItemName.contains("+75")) {
						CoinAPI.AddTokens(currentPlayer, 75);
					}
				} else if (ItemName.contains("+175")) {
					CoinAPI.AddTokens(currentPlayer, 175);
				}
				if(ItemName.contains("crate")){
					if(ItemName.contains("+2")){
						Keys.addKeys(currentPlayer,2);
					}else if(ItemName.contains("+5")){
						Keys.addKeys(currentPlayer,5);
					}
				}
			}
			PlayerListener.cooldown.add(currentPlayer.getName());
			new BukkitRunnable(){
				@Override
				public void run() {
					PlayerListener.cooldown.remove(currentPlayer.getName());
				}
			}.runTaskLater(Main.CorePluginCall,20*5);
			new BukkitRunnable(){
				@Override
				public void run() {
					if(GUI.ItemRotateTaskIds.containsKey(currentPlayer)) {
						currentPlayer.getOpenInventory().close();
					}else{
						cancel();
					}
				}
			}.runTaskLater(Main.CorePluginCall,20*4);
			Bukkit.getScheduler().cancelTask(GUI.ItemRotateTaskIds.get(currentPlayer));
			GUI.ItemRotateTaskIds.remove(currentPlayer);
			count = 0;
		}
	}

}
