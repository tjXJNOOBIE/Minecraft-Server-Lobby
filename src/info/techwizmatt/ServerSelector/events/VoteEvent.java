package info.techwizmatt.ServerSelector.events;

import com.vexsoftware.votifier.model.VoteListener;
import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerSelector.Keys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;

import info.techwizmatt.ServerSelector.Main;
import info.techwizmatt.ServerSelector.Servers.getServers;
import net.md_5.bungee.api.ChatColor;

import com.vexsoftware.votifier.model.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class VoteEvent implements Listener{
	
//	Main configLoader;
//	   
//    public VoteEvent(Main plugin) { 
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
//        configLoader = plugin;
//    }
	int coins;
	int keys;
	public static List<String> voted = new ArrayList<>();
	@EventHandler
	public void onVote(VotifierEvent event){
		Bukkit.getLogger().log(Level.INFO,"VOTED");
		Vote CurrentVote = event.getVote();
		String userName = CurrentVote.getUsername();
		String ServiceName = CurrentVote.getServiceName();
		Player user = Bukkit.getPlayer(userName);

		if(user != null){
			Random random = new Random();

			user.sendMessage(ChatColor.GREEN + "Thank you for voting! We are glad you enjoy §5Sonder Network!");
			if(random.nextInt(100)<=70){
				int i = random.nextInt(3);
				if(i == 1){
					coins = 50;
				}
				if(i == 2)
				coins=100;{
				}
				if(i==3){
					coins=150;
				}
				CoinAPI.AddTokens(user,500);
				user.sendMessage("§bYou've been awarded §a"+coins+"§b coins");

			}
			if(random.nextInt(100)<=15){
				keys=3;
				Keys.addKeys(user,keys);
				user.sendMessage("§bYou've been awarded §a3§b keys");
			}
			if(random.nextInt(100)<=3){

				user.sendMessage("§bYou've been awarded the Helix Effect");
			}
			coins = 0;
			keys=0;
			user.sendMessage("§eThe top 3 voters for every month will receive ranks as a reward §a☺");
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + user.getDisplayName() + " §bhas just voted and received §c" + coins + " §bcoins and§c "+keys+"§b keys!");

		}else{
			voted.add(userName);
		}


		
		
	}

	public void voteMade(Vote vote) {
		Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + vote.getUsername() + " has just voted on " + vote.getServiceName() + " !");

	}
}
