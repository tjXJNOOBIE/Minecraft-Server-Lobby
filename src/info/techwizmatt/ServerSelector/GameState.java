package info.techwizmatt.ServerSelector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;
import net.md_5.bungee.api.ChatColor;


public class GameState {

	public final static String CurrentGameState(String ServerName){
		DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

			Statement statement = Main.sqlConnection.createStatement();
			ResultSet GameStateSQLRAW = statement.executeQuery("SELECT * FROM `gamestate` WHERE `SERVERID`='"+ServerName+"';");
			GameStateSQLRAW.next();
		 	Short CurrentGameState = GameStateSQLRAW.getShort("GAMESTATE");
		 	if(CurrentGameState == 0){
		 		return ChatColor.GREEN + "Lobby";
		 	}
		 	if(CurrentGameState == 1){
		 		return ChatColor.YELLOW + "IN-GAME";
		 	}
		 	if(CurrentGameState == 2){
				return ChatColor.RED + "RESTARTING";
			}
			if(CurrentGameState == 3){
				return ChatColor.GREEN + "Open";
			}
		 	
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				
				}
		}
		return ChatColor.RED + "" + ChatColor.BOLD + "Unconfigured, Please tell an admin.";
	}
	
	public final static Material CurrentGameStateMaterial(String ServerName){
		DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

			Statement statement = Main.sqlConnection.createStatement();
			ResultSet GameStateSQLRAW = statement.executeQuery("SELECT * FROM `gamestate` WHERE `SERVERID`='"+ServerName+"';");
			GameStateSQLRAW.next();
		 	Short CurrentGameState = GameStateSQLRAW.getShort("GAMESTATE");
		 	if(CurrentGameState == 0){
		 		return Material.EMERALD_BLOCK;
		 	}
		 	if(CurrentGameState == 1){
		 		return Material.GOLD_BLOCK;
		 	}
		 	if(CurrentGameState == 2){
		 		return Material.REDSTONE_BLOCK;
		 	}
		 	
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return Material.WOOL;
	}
	
}
