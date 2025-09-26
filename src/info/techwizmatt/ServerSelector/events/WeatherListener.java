package info.techwizmatt.ServerSelector.events;

import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import info.techwizmatt.ServerSelector.Main;

public class WeatherListener implements Listener{

//    Main configLoader;
//    
//    public WeatherListener(Main plugin) { 
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
//        configLoader = plugin;
//    }
    
    public void WeatherChangeEvent(WeatherChangeEvent weather){
    	if(weather.getWorld().isThundering()){
    		weather.setCancelled(true);
    	}

    }
	
	
}