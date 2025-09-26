package info.techwizmatt.ServerSelector.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import info.techwizmatt.ServerSelector.WallStat.Render;
import net.md_5.bungee.api.ChatColor;

public class WallStat implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player user = (Player) sender;
			if(user.isOp()){
			Render.addLocPlayer = user;
			if(Render.AddLocs){
				Render.AddLocs = false;
				if(args.length >= 1){
					
					if(Render.locations.size() >= 15){
						String locData = "";
						for(Location loc: Render.locations){
							locData = locData + "#" + loc.getWorld().getName()+  ","+loc.getX() + "," + loc.getY() + "," + loc.getZ(); 
						}
						//locData = SQL data
//						Render.UrlLoc
						Render.InsertWall(args[0], locData, Render.UrlLoc);
						user.sendMessage(ChatColor.GREEN + "Player Stat Wall has been saved as \"" + args[0] +" \"");
						user.sendMessage(ChatColor.GREEN + "THE SERVER WILL AUTOMATICALLY ADD THE IMAGE.");
						//AfterComplete reset data;
						Render.locations.clear();
						Render.addLocPlayer = null;
					}else{
						user.sendMessage(ChatColor.RED + "There needs to be 15 blocks clicked to upload...");
						Render.locations.clear();
						Render.addLocPlayer = null;
					}
				}
			}else{
				Render.AddLocs = true;
				if(args.length >= 1){
					Render.UrlLoc = args[0];
					user.sendMessage(ChatColor.GREEN + "Please click a min of 15 blocks on a 3 by 5 wall from left to right going down. (Reading style) / After you are done type /statwall NameOfWall");
					return true;
				}
				user.sendMessage(ChatColor.RED + "/statwall (URL OF IMAGE)");
				return true;
			}
			}
		}
		return false;
	}

}
