package info.techwizmatt.ServerSelector.commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import info.techwizmatt.ServerSelector.CosmeticAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 3/30/2018.
 */
public class LoadCosmetics implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(Rank.getRankLevel((Player) sender) == 11) {
            CosmeticAPI cosmeticAPI = new CosmeticAPI();

            cosmeticAPI.GetCosmetics();
            cosmeticAPI.GetCosmeticsData();
            sender.sendMessage("done");
            sender.sendMessage(cosmeticAPI.GetPrices() + "" + cosmeticAPI.GetItems() + cosmeticAPI.names + "" );

        }
        return false;
    }
}
