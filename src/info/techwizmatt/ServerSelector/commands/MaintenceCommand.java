package info.techwizmatt.ServerSelector.commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import info.techwizmatt.ServerCore.Rank.Rank;
import info.techwizmatt.ServerSelector.Main;
import net.md_5.bungee.api.ChatColor;


public class MaintenceCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player user = (Player) sender;

	        int RankLevel = 1;
	        
	        if(Rank.QuedRankLevel.get(user) != null){
	        	RankLevel = Rank.QuedRankLevel.get(user);
	        }else{
	        	RankLevel = Rank.getRankLevel(user);
	        }
	        
			if(RankLevel >= Rank.ConvertRankNameToInt("Developer")){
				if(Main.MaintenceMode){
					Bukkit.getServer().setWhitelist(false);
					Main.MaintenceMode = false;
					user.sendMessage(ChatColor.GREEN + "Maintence Mode has been disabled");
				}else{
					Bukkit.getServer().setWhitelist(true);
					Main.MaintenceMode = true;
					user.sendMessage(ChatColor.GREEN + "Maintence Mode has been enabled.");
				}
			}else{
				user.sendMessage("Unknown command. Type \"/help\" for help.");
			}
		}
		
		
		return false;
	}


}
