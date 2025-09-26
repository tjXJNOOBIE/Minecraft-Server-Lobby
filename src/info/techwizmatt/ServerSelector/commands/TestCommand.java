package info.techwizmatt.ServerSelector.commands;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import info.techwizmatt.ServerSelector.GUI;
import info.techwizmatt.ServerSelector.WallStat.Stat;
import net.md_5.bungee.api.ChatColor;

public class TestCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player user = (Player) sender;
			if(user.isOp()){

				
				
			}else{
				user.sendMessage(ChatColor.RED + "NO :|");
			}
		}
		return false;
	}

}
