package info.techwizmatt.ServerSelector;

//2017 COPYWRIGHT - TECHWIZMATT IN OFFLICATION WITH BLIMP NETWORK.

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.common.io.ByteArrayDataInput;
import info.techwizmatt.ServerSelector.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import info.techwizmatt.ServerSelector.Servers.getServers;
import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;
import info.techwizmatt.ServerSelector.SqlDB.mysql.MySQL;
import info.techwizmatt.ServerSelector.WallStat.Render;
//import info.techwizmatt.ServerSelector.commands.NpcStat;
import info.techwizmatt.ServerSelector.events.ChestListener;
import info.techwizmatt.ServerSelector.events.PlayerListener;
import info.techwizmatt.ServerSelector.events.VoteEvent;
import info.techwizmatt.ServerSelector.events.WeatherListener;
import info.techwizmatt.ServerSelector.events.WorldProtection;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements PluginMessageListener{

	static Plugin plugin;
	public void Plugin() {
		plugin = this;
	}
	public static Main CorePluginCall;
	public static Connection sqlConnection = null;
	public static Boolean MaintenceMode = false;
	public static Map<String, Integer> playercount = new HashMap<String, Integer>();
	public static String Country = "";
	FileConfiguration config = getConfig();

	@Override
	public void onEnable(){
		plugin = this;
		DatabaseCore.SQLSTARTCONNECTION();
		CorePluginCall = this;
		info.techwizmatt.ServerCore.Main.CustomChatDisplay = true;
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), Main.CorePluginCall);
		Bukkit.getPluginManager().registerEvents(new WorldProtection(), Main.CorePluginCall);
		Bukkit.getPluginManager().registerEvents(new WeatherListener(), Main.CorePluginCall);
		Bukkit.getPluginManager().registerEvents(new VoteEvent(), Main.CorePluginCall);
		Bukkit.getPluginManager().registerEvents(new ChestListener(), Main.CorePluginCall);

		for(World SelectedWorld: Bukkit.getWorlds()){
			SelectedWorld.setTime(1000000);
		}


		this.getCommand("loadcosmetics").setExecutor(new LoadCosmetics());

		this.getCommand("maintence").setExecutor(new MaintenceCommand());
		this.getCommand("unix").setExecutor(new UnixTime());
		this.getCommand("test").setExecutor(new TestCommand());
		this.getCommand("statwall").setExecutor(new WallStat());
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		this.playercount.put("ALL", 0);

		Render.setAllWalls();


		new BukkitRunnable(){
			@Override
			public void run() {
				CosmeticAPI cosmeticAPI = new CosmeticAPI();

				cosmeticAPI.GetCosmetics();
				cosmeticAPI.GetCosmeticsData();
			}
		}.runTaskLater(CorePluginCall,20*10);
		new BukkitRunnable(){
			@Override
			public void run() {
				List<World> worlds = Bukkit.getWorlds();
				int i = worlds.size();
				if(i>0){
					i = worlds.size();
				}
				i--;
				for(World allw : worlds) {

				}
				List<Entity> entity = worlds.get(i).getEntities();

			for(Entity alle : entity){

				if(alle.getType() == EntityType.PLAYER){
					Bukkit.getLogger().log(Level.INFO,"PLAYERS WILL NOT GET REMOVED");
				}else{
					Bukkit.getLogger().log(Level.INFO,"REMOVED ENTITIES FROM WORLDS " + worlds.toString());
					Bukkit.getLogger().log(Level.INFO,"...REMOVED " + entity.size() + " entities from " + worlds.size() + " world(s)");
					alle.remove();
					for(Entity ap : Bukkit.getOnlinePlayers()){
					}
				}

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"kill @e[type=!Player]");



			}
			}
		}.runTaskTimer(CorePluginCall,20*60,20*60);
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(CorePluginCall, new Runnable() {
			
	    	
	    	
			@Override
			public void run() {
				Render.setAllWalls();
			}
		}, 10000L, 10000L);
	    
	    

	}
	
	@Override
	public void onDisable(){
		
	}
	
	static public void SendToServer(String PlayerName , String ServerName, Player SendingUser){
		CorePluginCall.getServer().getScheduler().scheduleSyncDelayedTask(CorePluginCall, new Runnable() {
			public void run() {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("ConnectOther");
				out.writeUTF(PlayerName);
				out.writeUTF(ServerName);
				SendingUser.sendPluginMessage(CorePluginCall, "BungeeCord", out.toByteArray());
				Bukkit.getLogger().info(ChatColor.GREEN + PlayerName + "CONNECTING TO " + ServerName);
			}
			}, 20L);

		
	}
	public static void getAllOnline() {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PlayerCount");
			out.writeUTF("ALL");
			Bukkit.getServer().sendPluginMessage(Main.getPlugin(), "BungeeCord", b.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Plugin getPlugin() {
		return plugin;
	}
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();


		if (subchannel.equals("PlayerCount")) {
			String server = in.readUTF();
			int online = in.readInt();

			playercount.put(server, online);
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerCount");
			out.writeUTF("ALL");
			player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
		}
	}
	}

