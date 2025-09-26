package info.techwizmatt.ServerSelector.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import info.techwizmatt.ServerSelector.Main;


public class WorldProtection implements Listener{
	
//	Main configLoader;
//	
//	public WorldProtection(Main plugin) {
//		plugin.getServer().getPluginManager().registerEvents(this, plugin);
//		configLoader = plugin;
//	}
	

	@EventHandler
	public void onTNTPRIME(ExplosionPrimeEvent event){
		event.setCancelled(true);
	}
	
//	@EventHandler
//	public void onEntity(EntityInteractEvent event){
//		event.setCancelled(true);
//	}
	@EventHandler
	public void onAnyExplosion(EntityExplodeEvent event){
		event.setCancelled(true);
	}
//	@EventHandler
//	public void onPlayerInteract(PlayerInteractEvent event){
//		event.setCancelled(true);
//	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		event.setKeepInventory(true);
		event.setDeathMessage("");
	}
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		if(!(event.getEntity() instanceof Player)) {
		    return;
		}
		event.setCancelled(true);

	}
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event){
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		event.setCancelled(true);

	}
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		if(event.getTo().getWorld().getName().toLowerCase().contains("the_end") || event.getTo().getWorld().getName().toLowerCase().contains("nether")){
			event.setCancelled(true);
		}
	}
}
