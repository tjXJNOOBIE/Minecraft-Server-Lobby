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

public class CSG {
	public static Map<String, String> getStats(int ID){
		Map<String, String> stats = new HashMap<String, String>();		
		
		DatabaseCore.SQLCONNECTIONOPEN();
		
		try
	    {
	      Statement statement = Main.sqlConnection.createStatement();
	      ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `clan_stats` WHERE `ID` = '"+ ID + "';");
	      if(ServerSQLRAW.next()) {
	        stats.put("LOSSES","" + ServerSQLRAW.getInt("LOSSES"));
	        stats.put("WINS","" + ServerSQLRAW.getInt("WINS"));
	        stats.put("DEATHS","" + ServerSQLRAW.getInt("DEATHS"));
	        stats.put("KILLS","" + ServerSQLRAW.getInt("KILLS"));
	        stats.put("WINRATE","" + ServerSQLRAW.getInt("WINRATE"));
	        stats.put("WINS","" + ServerSQLRAW.getInt("WINS"));
	        stats.put("KDR","" + ServerSQLRAW.getDouble("KDR"));
	        stats.put("POINTS","" + ServerSQLRAW.getInt("POINTS"));
	        stats.put("DMGTAKEN","" + ServerSQLRAW.getInt("DMGTAKEN"));
	        stats.put("PLAYED", "" + ServerSQLRAW.getInt("PLAYED"));
	        stats.put("DMGGIVEN","" + ServerSQLRAW.getInt("DMGGIVEN"));
	        stats.put("BOWACCURACY","" + ServerSQLRAW.getInt("BOWACCURACY"));
	        stats.put("RANK","" + ServerSQLRAW.getInt("RANK"));
	        
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
	        stats = getStats(ID);
	        
	      }
	    }
		
		
		return stats;
	}
}
