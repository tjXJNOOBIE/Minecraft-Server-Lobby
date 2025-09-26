package info.techwizmatt.ServerSelector;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
//»

public class Cosmetics {
	
	public static HashMap<Player, Boolean> HeartColor = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> oneHeart = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> xpRemove = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> Flight = new HashMap<Player, Boolean>();
	public static HashMap<Player, Boolean> HelixEffect = new HashMap<Player, Boolean>();

	public static void ToggleHeartColor(Player user){
		if(HeartColor.containsKey(user)) {
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + ChatColor.RESET + "" + ChatColor.GRAY + " Heart Color Changed.");
			user.removePotionEffect(PotionEffectType.POISON);
			HeartColor.remove(user);
			return;
		}else{
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + ChatColor.RESET + "" + ChatColor.GRAY + " Heart Color Changed.");
			user.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000000, 2));
			HeartColor.put(user, true);
			return;
		}
	}
	
	public static void ToggleOneHeart(Player user){
		if(oneHeart.containsKey(user)) {
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Hearts Toggled.");
			user.setMaxHealth(20);
			user.setHealth(20);
			oneHeart.remove(user);
			return;
		}else{
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Hearts Toggled.");
			user.setMaxHealth(2);
			oneHeart.put(user, true);
			return;
		}
	}
	
	public static void toggleXpBar(Player user){
		if( xpRemove.containsKey(user)) {
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Xp Bar Toggled.");
			user.setLevel(0);
			 xpRemove.remove(user);
			return;
		}else{
			user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Xp Bar Toggled.");
			user.setLevel(-100);
			 xpRemove.put(user, true);
			return;
		}
		
	}
	
	public static void toggleFlight(Player user){
		if(Flight.containsKey(user)) {
			 user.setAllowFlight(true);
			 user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Flight toggled.");
			 Flight.remove(user);
			return;
		}else{
			 user.setAllowFlight(false);
			 user.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + info.techwizmatt.ServerCore.Main.ShortName + " »" + org.bukkit.ChatColor.RESET + "" + ChatColor.GRAY + " Flight toggled.");
			 Flight.put(user, true);
			 return;
		}
	}
	
	public static void toggleHelixEffect(Player user){
		ParticleEffect effect = new ParticleEffect(ParticleEffect.ParticleType.FLAME, 0.1, 25, 0.0002);
		effect.sendToLocation(user.getLocation());


		if(HelixEffect.containsKey(user)){
			HelixEffect.remove(user);
		}else{
			HelixEffect.put(user, true);
			new BukkitRunnable(){
				@Override
				public void run() {
					effect.sendToLocation(user.getLocation());

				}
			}.runTaskTimer(Main.getPlugin(),0,20);
		}
	}


	
 

}
