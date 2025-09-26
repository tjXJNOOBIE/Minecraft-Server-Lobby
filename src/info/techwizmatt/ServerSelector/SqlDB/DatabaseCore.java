package info.techwizmatt.ServerSelector.SqlDB;

import java.sql.SQLException;

import org.bukkit.Bukkit;

import info.techwizmatt.ServerSelector.Main;
import net.md_5.bungee.api.ChatColor;

public class DatabaseCore {
	public static int RestartValue = 0;
	
	
	static public void SQLCONNECTIONOPEN(){
		try {
 			if(Main.sqlConnection.isClosed()){
 				 try {
 					 Main.sqlConnection = Main.MySQL.openConnection();

 					 
 				 		} catch (ClassNotFoundException error) {
 								Bukkit.getLogger().warning("MYSQL: ClassNotFoundExpetion - " + error);
 							} catch (SQLException error) {
 								Bukkit.getLogger().warning("MYSQL: SQLException - " + error);
 								String ConnectionRefusedError = "" + error;
 								
 								ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
 								
 								
 								Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
 								
 								if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception"){
 									SQLCONNECTIONOPEN();
 									Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED. CALLING AGAIN...");
 								}
 							}
 						}
				} catch (SQLException error) {
 				Bukkit.getLogger().warning("MYSQL: SQLException - " + error);			
 		}
	}
	
	
	
	static public void SQLSTARTCONNECTION(){
		
		 try {
			 Main.sqlConnection = Main.MySQL.openConnection();
	
		 		} catch (ClassNotFoundException error) {
						Bukkit.getLogger().warning("MYSQL: ClassNotFoundExpetion - " + error);
					} catch (SQLException error) {
						Bukkit.getLogger().warning("MYSQL: SQLException - " + error);
						
						Bukkit.getLogger().info("!------------------------------------!");
						Bukkit.getLogger().severe("ERROR STARTING SQL CONNECTION: Fix: Calling method again. restart tries: " + RestartValue);
						RestartValue = RestartValue + 1;
						Bukkit.getLogger().info("!------------------------------------!");
						Bukkit.getLogger().severe("SQL CONNECTION TRIES:" + RestartValue);
						Bukkit.getLogger().info("!------------------------------------!");
						if(RestartValue >= 6){
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().severe("Main WAS UNABLE TO CONNECT TO DATABASE. SHUTTING DOWN SERVER!");
							Bukkit.getLogger().severe("CHECK THE SQL SERVER OR CHECK Main.");
							Bukkit.getLogger().info("!------------------------------------!");
//							Bukkit.shutdown();
//							Bukkit.shutdown();
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().severe("If the server is not shutting down. force the shutdown now!");
							Bukkit.getLogger().severe("THE SERVER MAY BE FROZEN!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().info("!------------------------------------!");
							return;

							
						}
						try {
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().severe("PAUSING SERVER FOR 5 SECONDS BEFORE NEXT TRY!");
							Bukkit.getLogger().info("!------------------------------------!");
							Thread.sleep(5000);
							Bukkit.getLogger().info("...");Bukkit.getLogger().info("...");Bukkit.getLogger().info("...");
							Bukkit.getLogger().info("!------------------------------------!");
							Bukkit.getLogger().severe("TRYING SQLSTARTCONNECTION AGAIN.");
							Bukkit.getLogger().info("!------------------------------------!");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SQLSTARTCONNECTION();
					}

	}
	
	
	
}


