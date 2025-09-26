package info.techwizmatt.ServerSelector.commands;



import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class UnixTime implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		long timeStamp = new Date().getTime();
		sender.sendMessage(ChatColor.GREEN + "The current UNIX time is " + timeStamp /1000 + " .");
		return false;
	}

}
