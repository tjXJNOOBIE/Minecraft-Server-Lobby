package stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import info.techwizmatt.ServerSelector.Main;
import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;

public class FFA {

	public static Map<String, String> getStats(Player user){
		Map<String, String> stats = new HashMap<String, String>();		
		
		DatabaseCore.SQLCONNECTIONOPEN();
		
		try
	    {
	      Statement statement = Main.sqlConnection.createStatement();
	      ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `ffa_stats` WHERE `UUID` = '"+ user.getUniqueId().toString() + "';");
	      if(ServerSQLRAW.next()) {
	    	stats.put("ID","" + ServerSQLRAW.getInt("ID"));
	        stats.put("KILLS","" + ServerSQLRAW.getInt("KILLS"));
	        stats.put("DEATHS","" + ServerSQLRAW.getInt("DEATHS"));
	        stats.put("ASSIST","" + ServerSQLRAW.getInt("ASSIST"));
	        stats.put("DAMAGEGIVEN","" + ServerSQLRAW.getInt("DAMAGEGIVEN"));
	        stats.put("CPS","" + ServerSQLRAW.getInt("CPS"));
	        stats.put("KDR","" + ServerSQLRAW.getDouble("KDR"));
	        
	      }
	    }
	    catch (SQLException error)
	    {
	      Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
	      String ConnectionRefusedError = error.toString();
	      
	      ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
	      
	      Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
	      if (ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception") {
	        Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
	        stats.clear();
	        stats = getStats(user);
	        
	      }
	    }
		
		
		return stats;
	}
	
	
	public static int getElo(Player user){
DatabaseCore.SQLCONNECTIONOPEN();
		int elo = 0;
		try
	    {
	      Statement statement = Main.sqlConnection.createStatement();
	      ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `elo` WHERE `UUID` = '"+ user.getUniqueId().toString() + "';");
	      if(ServerSQLRAW.next()){
	    	 elo = ServerSQLRAW.getInt("RATING");
	      }
	    }
	    catch (SQLException error)
	    {
	      Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
	      String ConnectionRefusedError = error.toString();
	      
	      ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
	      
	      Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
	      if (ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception") {
	        Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
	        elo = getElo(user);
	        
	      }
	    }
		return elo;
	}
	
}
