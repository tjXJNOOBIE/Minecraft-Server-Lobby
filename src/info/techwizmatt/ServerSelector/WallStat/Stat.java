package info.techwizmatt.ServerSelector.WallStat;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;

import info.techwizmatt.ServerCore.Main;

public class Stat {

	public static int ErrorCount = 0;
	
	public static HashMap<Integer, Image> Images = new HashMap<Integer, Image>();
	
	@SuppressWarnings("deprecation")
	public static void PlaceImage(Location loc, Image img){
		World currentWorld = loc.getWorld();
		
		loc.getBlock().setType(Material.AIR);
		

		
		MapView map = Bukkit.getServer().createMap(currentWorld);
		map.getRenderers().clear();
//		Bukkit.getLogger().info("Placing Map - " + map.getId());
		
		Images.put((int) map.getId(), img);
	
		map.addRenderer(new CustomMapRender());
		
		try {
			ItemFrame frame = (ItemFrame) currentWorld.spawn(loc, ItemFrame.class);
			frame.setRotation(Rotation.NONE);
			frame.setItem(new ItemStack(Material.MAP, 1, map.getId()));
		} catch (Exception e) {
			if(e.getMessage().toLowerCase().contains("Cannot spawn hanging entity")){
				
				Bukkit.getLogger().info("Error Spawning. (Hanging Entity) ['Trying Again']");
				
				ErrorCount++;
				if(ErrorCount >= 5){
					Bukkit.getLogger().info("Failed Trying Image Spawn. Now Cancelling..");
				}else{
					PlaceImage(loc, img);
				}
			}
		}
		
		
		
		
		

	}
	public static void removeImage(Location loc){
		
	}
	
	
	public static void PlaceWallOfImages(Location[] locs, Image fullImg){
		
		World currentWorld = locs[0].getWorld();
		
		Bukkit.getLogger().info("Placing Image " + fullImg.hashCode());
	
		Bukkit.getScheduler().scheduleSyncDelayedTask(info.techwizmatt.ServerSelector.Main.CorePluginCall, new Runnable() {
			
			@Override
			public void run() {
				BufferedImage[] images = null;
				try {
					images = Render.Split(fullImg, 3, 5);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				


				   for(int i=0; i<=14; i++){
					   Image image = (Image) images[i];
					   if(!image.equals(null)){
						   Location setLoc = locs[i];

						   Stat.PlaceImage(setLoc, image);
						   Bukkit.getLogger().info("-- Tile [" + i + "]" + fullImg.hashCode() + " placed.");
					   }
			         }
			}
		}, 20L);
		
		
		
	}
	

	
}
