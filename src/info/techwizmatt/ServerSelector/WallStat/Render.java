package info.techwizmatt.ServerSelector.WallStat;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import info.techwizmatt.ServerSelector.Main;
import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;


public class Render{
	
	public static String UrlLoc = "";
	public static boolean AddLocs = false;
	public static LinkedList<Location> locations = new LinkedList<Location>();
	public static Player addLocPlayer = null;
	
	public static HashMap<Player, Boolean> CurrentlyRendering = new HashMap<Player, Boolean>();
	
	
    public static BufferedImage[] Split(Image image, int rows, int cols) throws IOException {
    	
        int chunks = rows * cols;

        int chunkWidth = image.getWidth(null) / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight(null) / rows;
        
        
        
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, ((BufferedImage) image).getType());

                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
		return imgs;

    }
    
	public static Image LoadImage(String Url){
		try {
			BufferedImage image = ImageIO.read(new URL(Url));
			Image loadedImage = new ImageIcon(image).getImage();
			
			
			return loadedImage;
		} catch (Exception e) {
			e.printStackTrace();
			
			
			Bukkit.getLogger().severe("IMAGE URL IS DEAD! : " + Url);
			
		}
		return null; 
	}
	
	
	public static void InsertWall(String name, String Location,String imageUrl){

		DatabaseCore.SQLCONNECTIONOPEN();
		
		try {
			
			Statement statement = Main.sqlConnection.createStatement();
			int ServerListSQLRAW = statement.executeUpdate("INSERT INTO `WallStats`(`NAME`, `Locations`, `ImageUrl`) VALUES ('"+ name +"','"+ Location +"','"+ imageUrl +"')");
			Bukkit.getLogger().info("");

					  
				  
				
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){

				}
		}
		

			
		
	}
	
	public static void setAllWalls(){

		

		DatabaseCore.SQLCONNECTIONOPEN();
		LinkedList<Location> Locations = new LinkedList<Location>();
		
		
		Bukkit.getLogger().info("Reloading All Image Walls");
		
		World world = null;
		
		try {
			
			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM `WallStats`");
			while(ServerListSQLRAW.next()){
				
				for(String LocationData: ServerListSQLRAW.getString("Locations").split("#")){
					String[] LocSet = LocationData.split(",");
					if(LocSet.length >= 4){
						Location setLoc = new Location(Bukkit.getWorld(LocSet[0]), Double.parseDouble(LocSet[1]), Double.parseDouble(LocSet[2]), Double.parseDouble(LocSet[3]), 0, 0);
						Locations.add(setLoc);
					}
				
				}
				
				Location[] locArray = new Location[Locations.size()];
				int i=0;
				for(Location loc: Locations){
					locArray[i] = loc;
					i++;
				}
				world = locArray[0].getWorld();
				for(Entity ent: locArray[0].getWorld().getEntitiesByClass(ItemFrame.class)){
					ent.remove();
				}
				
				Stat.PlaceWallOfImages(locArray, Render.LoadImage(ServerListSQLRAW.getString("ImageUrl")));
				Locations.clear();
			}

			File Directory = Main.CorePluginCall.getServer().getWorldContainer();
			File Plugins = new File(Directory.getAbsolutePath().toString() + "/" + world.getName() + "/data");
			for(File SelectedFile: Plugins.listFiles()){
				if(SelectedFile.getName().toLowerCase().contains("map") || SelectedFile.getName().toLowerCase().contains("idcounts")){
					SelectedFile.delete();
					Bukkit.getLogger().info("Removed " + SelectedFile.getName());
				}
			}
					  
				  
				
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){

				}
		}
		

			
		
	}
	
	public static boolean lookingAtItemFrame(Location LookingAtBlock) {
		
		int range = 1;
		 
		for (Entity entity : LookingAtBlock.getWorld().getEntities()) {
			if (isInBorder(LookingAtBlock, entity.getLocation(), range)) {
				if(entity instanceof ItemFrame){
					return true;
				}
				if(entity.getType().equals(EntityType.ITEM_FRAME)){
					return true;
				}
			}
		}
		return false;
		}
	
	public static boolean isInBorder(Location center, Location notCenter, int range) {
		int x = center.getBlockX(), z = center.getBlockZ();
		int x1 = notCenter.getBlockX(), z1 = notCenter.getBlockZ();
		 
		if (x1 >= (x + range) || z1 >= (z + range) || x1 <= (x - range) || z1 <= (z - range)) {
		return false;
		}
		return true;
		}

	
}
