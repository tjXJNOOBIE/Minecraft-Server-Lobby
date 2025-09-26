package info.techwizmatt.ServerSelector.Servers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import info.techwizmatt.ServerSelector.Main;
import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;
import net.md_5.bungee.api.ChatColor;

public class getServers {
	Main plugin;
	public getServers(Main p){
		plugin = p;

	}	
	
	public static int GetOnlineServerListPerType(int ServerTypeID){
		List<String> EntireServerListPerType = GetOnlineServerLists(ServerTypeID);
		int PlayerCount = 0 ;

		for(String Server : EntireServerListPerType){
			List<String> OnlineCount = getServers.GetOnlinePlayerListServer(Server);
			PlayerCount = OnlineCount.size() + PlayerCount;
		}
		
		return PlayerCount;
	}

//This gets all the Servers connected to BUNGEE and finds there port to find which ones are Online.	
	public static List<String> GetOnlineServerLists(int ServerTypeID) {
		
		List<String> ServerList = new LinkedList<String>(); 

		DatabaseCore.SQLCONNECTIONOPEN();
		
		String Country = DetectCountry();
		
		try {
			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM `Servers` ORDER BY `Servers`.`ServerName` ASC;");
			while (ServerListSQLRAW.next()) {
				  String ServerName = ServerListSQLRAW.getString("ServerName");
				  int ServerPort = ServerListSQLRAW.getInt("ServerPort");
				  
				  if(Country == "eu"){
					  if(ServerName.contains("EU")){

						  if(ServerOnline("eu.sonderpvp.net", ServerPort)){
							  if(ServerTypeID == 0){
							  if(ServerName.contains("SG")){
								  if(ServerName.contains("CSG")){
									  //Do nothing because it thinks is CSG.
								  }else if(ServerName.contains("UHC")){
									  //Do nothing because it thinks its UHCSG
								  }else{
									  ServerList.add(ServerName);
								  }
							  }
							  }
							  if(ServerTypeID == 1)
							  if(ServerName.contains("FFA")){
								  if(ServerName.contains("UHCFFA")){
									  
								  }else{
									  ServerList.add(ServerName);
								  }
							  }
							  if(ServerTypeID == 2){
							  if(ServerName.contains("CSG")){
								  ServerList.add(ServerName);
							  }
							  }
							  if(ServerTypeID == 3){
								  if(ServerName.contains("UHC")){
									  if(ServerName.contains("UHCM")){
										  
									  }else if(ServerName.contains("SG")){
										  
									  }else{
										  ServerList.add(ServerName);
									  }
								  }
							  }
							  if(ServerTypeID == 4){
								  if(ServerName.contains("UHC")){
									  if(ServerName.contains("UHCM")){
										  ServerList.add(ServerName);
									  }
								  }
							  }
							  if(ServerTypeID == 5){
								  if(ServerName.contains("ARENA")){
									  ServerList.add(ServerName);
								  }
							  }
							  if(ServerTypeID == 6){
								  if(ServerName.contains("UHCFFA")){
									  ServerList.add(ServerName);
								  }
							  }
							  if(ServerTypeID == 7){
								  if(ServerName.contains("UHCSG")){
									  ServerList.add(ServerName);
								  }
							  }
							  
							  if(ServerTypeID == 20){
								ServerList.add(ServerName);
							  }
							  
							  
						  }
					  }else{
				
					  }
				  }else if(Country == "na"){
					  if(ServerName.contains("EU")){
						
					  }else{
						

				  if(ServerOnline("sonder.network", ServerPort)){
					  
					  if(ServerTypeID == 0){
					  if(ServerName.contains("SG")){
						  if(ServerName.contains("CSG")){
							  //Do nothing because it thinks is CSG.
						  }else{
							  ServerList.add(ServerName);
						  }
					  }
					  }
					  if(ServerTypeID == 1)
					  if(ServerName.contains("FFA")){
						  ServerList.add(ServerName);
					  }
					  if(ServerTypeID == 2){
					  if(ServerName.contains("CSG")){
						  ServerList.add(ServerName);
					  }
					  }
					  if(ServerTypeID == 3){
						  if(ServerName.contains("UHC")){
							  if(ServerName.contains("UHCM")){
								  
							  }else{
								  ServerList.add(ServerName);
							  }
						  }
					  }
					  if(ServerTypeID == 4){
						  if(ServerName.contains("UHC")){
							  if(ServerName.contains("UHCM")){
								  ServerList.add(ServerName);
							  }
						  }
					  }
					  if(ServerTypeID == 5){
						  if(ServerName.contains("ARENA")){
							  ServerList.add(ServerName);
						  }
					  }
					  if(ServerTypeID == 7){
						  if(ServerName.contains("UHCSG")){
							  ServerList.add(ServerName);
						  }
					  }
					  if(ServerTypeID == 20){
						ServerList.add(ServerName);
					  }
					  
					  
				  }
					  
				  }
				  }
				  
				}
				
			return ServerList;
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug - " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				ServerList.add("DB ERROR - getServers.GetOnlineServerLists() - DEV DATA:" + ConnectionRefusedError);

				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					
				}
		}
		return ServerList;
		
		
			
		
	}

	
	public static List<String> GetOnlinePlayerListServer(String ServerName){
		int ServerPort = ServerNameToServerPort(ServerName);
		List<String> OnlinePlayers = new LinkedList<String>();
		if(ServerPort == 25565){
			
		}else{
			DatabaseCore.SQLCONNECTIONOPEN();
			
			try {

				Statement statement = Main.sqlConnection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `currentServer`= '"+ServerPort+"';");
				while(ServerSQLRAW.next()){
					OnlinePlayers.add(ServerSQLRAW.getString("last_username"));
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
			
		}
		
		return OnlinePlayers;
	}
	
	public static List<String> GetOnlinePlayerList(){
		List<String> OnlinePlayers = new LinkedList<String>();

			DatabaseCore.SQLCONNECTIONOPEN();
			
			try {

				Statement statement = Main.sqlConnection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `online`= '1';");
				while(ServerSQLRAW.next()){
					OnlinePlayers.add(ServerSQLRAW.getString("last_username"));
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
			
		
		
		return OnlinePlayers;
	}
	
	public static int ServerNameToServerPort(String ServerName) {
		
		DatabaseCore.SQLCONNECTIONOPEN();
		
		try {
			
			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerListSQLRAW = statement.executeQuery("SELECT * FROM `Servers` WHERE `ServerName`='"+ServerName+"';");
			ServerListSQLRAW.next();
			 return ServerListSQLRAW.getInt("ServerPort");
			 

					  
				  
				
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){

				}
		}
		
		return 25565;
			
		
	}
	
	
	public static int SelectedServerPlayerCount(int ServerPort, Boolean Block){
		
DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `currentServer`= '"+ServerPort+"';");
			int ServerPlayerPlayer = 0;
			while(ServerSQLRAW.next()){
				ServerPlayerPlayer++;
			}
			if(Block){
				if(ServerPlayerPlayer == 0){
					return 1;
				}
			}
			
			
			return ServerPlayerPlayer;
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return 1;
	}
	public static int ServerPlayerCount(String ServerName, Boolean block){
		int ServerNametoPort = ServerNameToServerPort(ServerName);
		return SelectedServerPlayerCount(ServerNametoPort, block);
	}
	
	public static  List<String> PlayerStatsOnServer(String user , int ServerTypeID){
		
DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

		  	if(ServerTypeID == 0){
			  //sg
		  		Statement statement = Main.sqlConnection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `stats` WHERE `USERNAME`= '"+user+"';");
				ServerSQLRAW.next();
				return Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "Kills: " + ServerSQLRAW.getString("KILLS") ,ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ServerSQLRAW.getString("DEATHS") ,ChatColor.RED + "" + ChatColor.BOLD + "KDR: " + ServerSQLRAW.getString("KDR"));
			  	}
			if(ServerTypeID == 1){	
			//faa	
				Statement statement = Main.sqlConnection.createStatement();
				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `ffa_stats` WHERE `USERNAME`= '"+user+"';");
				ServerSQLRAW.next();
				return Arrays.asList(ChatColor.RED + "" + ChatColor.BOLD + "Kills: " + ServerSQLRAW.getString("KILLS") ,ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ServerSQLRAW.getString("DEATHS") ,ChatColor.RED + "" + ChatColor.BOLD + "KDR: " + ServerSQLRAW.getString("KDR"));
			  	
				}
			
			if(ServerTypeID == 2){
//				Statement statement = Main.sqlConnection.createStatement();
//				ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `currentServer`= '"+user+"';");
//			//csg
			  	}
			
			
			
			int ServerPlayerPlayer = 0;
			
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}
		return null;
	}
		
	public static String PlayerTime(String user){
		
DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `last_username`= '"+user+"';");
			ServerSQLRAW.next();
			int seconds = ServerSQLRAW.getInt("playtime");
			int day = (int)TimeUnit.SECONDS.toDays(seconds);        
			 long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
			 long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
			 long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

			 return day + " : " + hours + " : " + minute + " : " + second;
			 
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}

		
		return "";
	}
	
	
	public static List<String> Top3SGPlayers()
	  {
	    DatabaseCore.SQLCONNECTIONOPEN();
	    List<String> top3 = new LinkedList();
	    try
	    {
	      Statement statement = Main.sqlConnection.createStatement();
	      ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `stats` ORDER BY `WINS` DESC LIMIT 3;");
	      while (ServerSQLRAW.next()) {
	        top3.add(ServerSQLRAW.getString("USERNAME"));
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
	      }
	    }
	    return top3;
	  }
	
	public static List<String> Top3UHCPlayers()
	  {
	    DatabaseCore.SQLCONNECTIONOPEN();
	    List<String> top3 = new LinkedList();
	    try
	    {
	      Statement statement = Main.sqlConnection.createStatement();
	      ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `player_data` ORDER BY `wins` DESC LIMIT 3;");
	      while (ServerSQLRAW.next()) {
	        top3.add(UsernameLookUp(ServerSQLRAW.getString("player")));
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
	      }
	    }
	    return top3;
	  }
	
	
	public static int PlayerTokens(String user){
		
DatabaseCore.SQLCONNECTIONOPEN();
		
		try {

			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `last_username`= '"+user+"';");
			ServerSQLRAW.next();
			int seconds = ServerSQLRAW.getInt("tokens");
			 
			
		} catch (SQLException error) {
			Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = "" + error;
				
				ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
				
				
				Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
				
				if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
					Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				}
		}

		
		return 0;
	}
	
	public static String UsernameLookUp(String uuid){
		
		DatabaseCore.SQLCONNECTIONOPEN();
				
				try {

					Statement statement = Main.sqlConnection.createStatement();
					ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `players_data` WHERE `UUID`= '"+uuid+"';");
					if(ServerSQLRAW.next()){
					String LastUsername = ServerSQLRAW.getString("last_username");
					return LastUsername;
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
				return null;

				
				
			}
		
	
	
	
	public static boolean ServerOnline(String ServerIP, int ServerPort){
		if(ServerIP == null){
			ServerIP = "blimpsg.net";
		}
	SocketAddress sockaddr = new InetSocketAddress(ServerIP, ServerPort);
	// Create your socket
	Socket socket = new Socket();
	boolean online = true;
	// Connect with 10 s timeout
	try {
	    socket.connect(sockaddr, 500);
	} catch (SocketTimeoutException stex) {
	    // treating timeout errors separately from other io exceptions
	    // may make sense
	    online=false;
	} catch (IOException iOException) {
	    online = false;    
	} finally {
	    // As the close() operation can also throw an IOException
	    // it must caught here
	    try {
	        socket.close();
	    } catch (IOException ex) {
	        // feel free to do something moderately useful here, eg log the event
	    }

	}
	// Now, in your initial version all kinds of exceptions were swallowed by
	// that "catch (Exception e)".  You also need to handle the IOException
	// exec() could throw:
	if(!online){
		return false; // FALSE!@!!!
	}else{      
		
	}
	return true;
	}
	
	public static String DetectCountry(){
		int ServerPort = Bukkit.getServer().getPort();
		if(ServerOnline("sonder.network", ServerPort)){
			
			return "na";
		}else if(ServerOnline("eu.sonder.network", ServerPort)){
			

			return "eu";
		}else{
			return "na";
		}
	}
	
}
