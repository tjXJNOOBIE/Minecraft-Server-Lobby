package info.techwizmatt.ServerSelector;

import info.techwizmatt.ServerCore.API.CoinAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

/**
 * Created by TJ on 11/25/2017.
 */
public class CustomBoard {


    public static org.bukkit.scoreboard.Scoreboard setScoreboard(Player p){
         Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("SonderNetwork", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score fillerOne = obj.getScore("");
        fillerOne.setScore(16);

        Team you = board.registerNewTeam("you");
        you.addEntry(ChatColor.DARK_GRAY.toString());
        you.setPrefix("»"+ ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " You ");
        obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(15);

        Score name = obj.getScore( p.getName());
        name.setScore(14);

        Score fillerTwo = obj.getScore(" ");
        fillerTwo.setScore(13);

        Team rank = board.registerNewTeam("rank");
        rank.addEntry(ChatColor.DARK_GRAY.toString() + " ");
        rank.setPrefix( "»"+ ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " Rank");
        obj.getScore(ChatColor.DARK_GRAY.toString() + " ").setScore(12);

        Score playerRank = obj.getScore(Utils.getRank(p));
        playerRank.setScore(11);

        Score fillerThree = obj.getScore("  ");
        fillerThree.setScore(10);

        Team players = board.registerNewTeam("players");
        players.addEntry(" " + ChatColor.DARK_GRAY.toString());
        players.setPrefix("»"+ ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " Players");
        obj.getScore(" " + ChatColor.DARK_GRAY.toString()).setScore(9);

        Team online = board.registerNewTeam("online");
        online.addEntry("§f");
        online.setSuffix("§f"+Main.playercount.get("ALL"));
        obj.getScore("§f").setScore(8);

        Score fillerFour = obj.getScore("   ");
        fillerFour.setScore(7);

        Team coins = board.registerNewTeam("coins");
        coins.addEntry( "»"+ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + " Coins" + ChatColor.WHITE.toString() + "");
        obj.getScore("»"+ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + " Coins" + ChatColor.WHITE.toString() + "").setScore(6);

        Score fillerFive = obj.getScore("§a"+CoinAPI.GetTokens(p));
        fillerFive.setScore(5);

        Score fillerSix = obj.getScore("    ");
        fillerSix.setScore(4);

        Score ip = obj.getScore("§5§lsonder.§8rip");
        ip.setScore(1);
		/*
		Team timer = board.registerNewTeam("timer");
		timer.addEntry(ChatColor.RED.toString());
		timer.setPrefix("Timer: ");
		timer.setSuffix("0");
		obj.getScore(ChatColor.RED.toString()).setScore(0);

		board.getTeam("timer").setSuffix("1");
		*/
		/*
		Team timer = board.registerNewTeam("timer");
		timer.addEntry(ChatColor.RED.toString());
		timer.setPrefix("Timer: ");
		timer.setSuffix("0");
		obj.getScore(ChatColor.RED.toString()).setScore(0);

		board.getTeam("timer").setSuffix("1");
		*/
        p.setScoreboard(board);
        new BukkitRunnable(){
            @Override
            public void run() {
                online.setSuffix("§a"+ Main.playercount.get("ALL"));
            }
        }.runTaskTimer(Main.getPlugin(),20*5,20*5);
        new BukkitRunnable()
        {
            Scroller scroller = new Scroller("&5&lSonder &8&lNetwork", 14, 2, '&');

            public void run()
            {
           obj.setDisplayName(scroller.next());
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 3L); // runs every 3 ticks

        return board;
    }
}
