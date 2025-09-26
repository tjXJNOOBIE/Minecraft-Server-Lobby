package info.techwizmatt.ServerSelector.events;
 
import java.sql.ResultSet;
import java.sql.SQLException;

//2017 COPYWRIGHT - TECHWIZMATT IN OFFLICATION WITH BLIMP NETWORK.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import info.techwizmatt.ServerCore.API.*;
import info.techwizmatt.ServerSelector.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import info.techwizmatt.ServerCore.Rank.Rank;
import info.techwizmatt.ServerSelector.Servers.getServers;
import info.techwizmatt.ServerSelector.SqlDB.DatabaseCore;
import info.techwizmatt.ServerSelector.WallStat.Render;
import io.netty.util.internal.ThreadLocalRandom;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;
import stats.UHCGames;


public class PlayerListener implements Listener{
 
//    Main configLoader;
//   
//    public PlayerListener(Main plugin) { 
//        plugin.getServer().getPluginManager().registerEvents(this, plugin);
//        configLoader = plugin;
//    }
    public static List<String> cooldown = new ArrayList<>();

    @EventHandler //Compass Clicking
    public void onCompassClick(PlayerInteractEvent event){
        Action action = event.getAction();
        ItemStack currentItem = event.getItem();
        Block clickedBlock = event.getClickedBlock();
        Player user = event.getPlayer();

    
        
     
        
        
        
        if(action == Action.PHYSICAL || currentItem == null || currentItem.getType() == Material.AIR){
       if(clickedBlock != null){
    	   if(clickedBlock.getType().equals(Material.ENDER_CHEST)){
             	user.sendMessage(ChatColor.RED + "You need a Crate Key to open this Drop Crate.");
             	event.setCancelled(true);
             	user.closeInventory();
     			return;
             }  
       }
        	 
            return;
        }
        
        
       
        
        if(currentItem.getType() == Material.COMPASS){
            GUI.OpenServerCompassGUI(event.getPlayer());
            return;
        }
        if(currentItem.getType() == Material.CHEST){
            GUI.OpenServerPackageGUI(event.getPlayer());
            return;
        }
        if(currentItem.getType() == Material.SKULL_ITEM){
            GUI.OpenUserProfileGUI(event.getPlayer());
            return;
        }
        if(currentItem.getType() == Material.ENDER_PEARL){
            GUI.OpenCountryGUI(event.getPlayer());
            return;
        }
  
        
        
        if(currentItem.getType() == Material.TRIPWIRE_HOOK && currentItem.getItemMeta().getDisplayName().toLowerCase().contains("crate")){
        	if(clickedBlock == null){
        		user.sendMessage(ChatColor.RED + "Bring me to a Drop Crate to receive a reward ☺");
    			return;
        	}
        		if(event.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
        			
        			if(Players.GetNumberOfCrateKeys(user.getUniqueId().toString()) > 0) {
                        if(!cooldown.contains(user.getName())){
                            event.setCancelled(true);
                        user.closeInventory();
                        user.openInventory(GUI.DropCrateInv());
                        user.getInventory().remove(user.getInventory().getHeldItemSlot());

                        int RandomNumber = ThreadLocalRandom.current().nextInt(20, 50);


                        GUI.RotateItems(user, RandomNumber, 2, false);


                    }else{
                            event.setCancelled(true);
                            user.sendMessage("§cPlease wait at least 5 seconds in between openings");
                        user.closeInventory();
                        return;

                        }
            			return;
        			}else{
        				user.sendMessage(ChatColor.RED + "You have 0 Crate Keys Left.");
        			}
        			
        			
        		}else{
        			user.sendMessage(ChatColor.RED + "Bring me to a Drop Crate to receive a reward ☺");
        			return;
        		}
        	
        }
        if(clickedBlock != null){
        	if(clickedBlock.getType().equals(Material.ENDER_CHEST)){
            	
               	user.sendMessage(ChatColor.RED + "You need a Crate Key to open this Drop Crate.");
               	event.setCancelled(true);
               	user.closeInventory();
       			return;
               
            }
        }
        
       
       
    }
    
    

   
    @EventHandler //PlayerJoining
    public void onPlayerJoin(PlayerJoinEvent joinEvent){
        Player user = joinEvent.getPlayer();
        Vector vector = new Vector(user.getLocation().getX(),user.getLocation().getY(),user.getLocation().getZ());
        user.teleport(new Location(user.getWorld(),617.43,33.5,161.42,-90,0));
        Main.getAllOnline();
        user.setFlySpeed(0.2F);
        user.setWalkSpeed(0.2F);
        CustomBoard.setScoreboard(user);
        final int[] RankLevel = {1};
        new BukkitRunnable(){
            @Override
            public void run() {
                if(Rank.QuedRankLevel.get(user) != null){
                    RankLevel[0] = Rank.QuedRankLevel.get(user);
                }else{
                    RankLevel[0] = Rank.getRankLevel(user);
                }
            }
        }.runTaskLater(Main.getPlugin(),20*3);

        
        
        if(Main.MaintenceMode){
        	if(Rank.getRankLevel(user) >= Rank.ConvertRankNameToInt("Mod")){
        		user.setWhitelisted(true);
        	}else{
        		
        		String Announcement = "";
        		
        		ResultSet Data = info.techwizmatt.ServerCore.SqlDB.DatabaseCore.executeQuery("SELECT * FROM `Announcements` WHERE `active` = 1");
        		try {
					if(Data.next()){
						Announcement = "\n Announcement: " + Data.getString("Announcement");
						
					}
				} catch (Exception e) {
					
				}
        		
        		
        		user.kickPlayer(ChatColor.RED + "Server is in Maintenance Mode. Please Check back later! " + Announcement);
        	}
        }
        
        
        joinEvent.setJoinMessage("");
        user.setFoodLevel(20);
        for(int i = 1; i <= 30; i++)
        user.sendMessage("");
        user.sendMessage("§7§m§l--------------------------------------------");
        user.sendMessage(" ");
        user.sendMessage("§f Welcome to the §5§l" + info.techwizmatt.ServerCore.Main.FullName);
        user.sendMessage(" ");
        user.sendMessage("» §5§lWebsite: §f" + info.techwizmatt.ServerCore.Main.Website);
        user.sendMessage("» §5§lTwitter: §fhttps://twitter.com/SonderNetwork");
        user.sendMessage("» §5§lTeamspeak: §fts." + info.techwizmatt.ServerCore.Main.MainIP);
        user.sendMessage("» §5§lStore: store." + info.techwizmatt.ServerCore.Main.MainIP);
        user.sendMessage(" ");
        user.sendMessage("§7§m§l--------------------------------------------");
        
        user.getInventory().clear();//Clear Inventory before join.

        //Give Player the Server Selector
        ItemStack ServerChooser = new ItemStack(Material.COMPASS); 
        ItemMeta ServerChooserMeta = ServerChooser.getItemMeta();
        ServerChooserMeta.setDisplayName("§7» §5§lServer Selector §7«");
        ServerChooserMeta.setLore(Arrays.asList(ChatColor.GREEN + user.getName() + ", use this to select a game."));
        ServerChooser.setItemMeta(ServerChooserMeta);
       
        ItemStack PackageChooser = new ItemStack(Material.CHEST);
        ItemMeta PackageChooserMeta = PackageChooser.getItemMeta();
        PackageChooserMeta.setDisplayName(" §7» §5§lExtras §7«");
        PackageChooserMeta.setLore(Arrays.asList(ChatColor.GREEN + user.getName() + ", use this to view Extra Options."));
        PackageChooser.setItemMeta(PackageChooserMeta);
       
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(user.getName());
        meta.setDisplayName("§7» §5§lProfile §7«");
        meta.setLore(Arrays.asList(ChatColor.GREEN + user.getName() + ", use this to view your stats, clan, and, tokens"));
        skull.setItemMeta(meta);
        
		ItemStack HandBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta HandBookMeta = (BookMeta) HandBook.getItemMeta();
		HandBookMeta.setDisplayName("§7» §5§lHandbook §7«");
		HandBookMeta.setLore(Arrays.asList(ChatColor.GREEN + "This is your Handbook. Read this" , ChatColor.GREEN + "to get the best experience on the " + info.techwizmatt.ServerCore.Main.FullName));
		HandBookMeta.setTitle("§8» §5§lHandbook §8«");
		HandBookMeta.setAuthor(ChatColor.BLUE + info.techwizmatt.ServerCore.Main.FullName);
		

		//get the pages

		
		TextComponent WebsiteUrl = new TextComponent(info.techwizmatt.ServerCore.Main.MainIP);
		WebsiteUrl.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, info.techwizmatt.ServerCore.Main.Website));
		WebsiteUrl.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "This is the " + info.techwizmatt.ServerCore.Main.FullName + ". Check it out to see what you can do.").create()));


		
		ItemStack Country = new ItemStack(Material.ENDER_PEARL);
		ItemMeta CountryMeta =  Country.getItemMeta();
		CountryMeta.setDisplayName("§6Country §8» §9Right Click §8«");
		CountryMeta.setLore(Arrays.asList(ChatColor.GREEN + user.getName() + ", use this to Join a Country selected Server-."));
		Country.setItemMeta(CountryMeta);
		

		
		
		
		
		
//		
//
//		pages.add(page);
//
//
//		
//		
//		// "  » " + ChatColor.GOLD + "Sonder Handbook " + ChatColor.RESET + "« \n" + ChatColor.BLUE + ChatColor.UNDERLINE + "       "
//		
//		
//		
//		
//		pages.add(IChatBaseComponent.ChatSerializer.a("  » " + ChatColor.GOLD + "Sonder Handbook " + ChatColor.RESET + "« \n" + ChatColor.BLUE + ChatColor.UNDERLINE + "       ") +WebsiteTest );
//		pages.add(WebsiteTest);
//		
//		HandBookMeta.setPages(pages);
		HandBook.setItemMeta(HandBookMeta);
		
        user.getInventory().setItem(4, ServerChooser); //Compass
//        user.getInventory().setItem(0, Country);
        user.getInventory().setItem(2, skull); //Profile
        user.getInventory().setItem(6, PackageChooser); //Chest
        if(user.isOp()){
        user.getInventory().setItem(8, HandBook); //Chest
        }
    }
    
   @EventHandler
   public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
   }
   @EventHandler
   public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        e.setFormat(p.getDisplayName() + " §f» " +Rank.getChatColor(Rank.getRankLevel(p))+ e.getMessage());
   }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
    	
        Player user = (Player) event.getWhoClicked();
        
        InventoryAction ClickAction = event.getAction();

        
    
        
        
        ItemStack CurrentItem = event.getCurrentItem();
        if(CurrentItem == null){
            user.closeInventory();
            return;
        }
        
        if(CurrentItem == new ItemStack(Material.AIR)){
            event.setCancelled(true);
            return;
        }else{
        //Grab what item the grabbed.
        	
            event.setCancelled(true);
            String ItemName = "";
            if(ClickAction != InventoryAction.NOTHING){
            
            ItemMeta CurrentItemMeta = CurrentItem.getItemMeta();
            
            if(CurrentItemMeta == null){
            	event.setCancelled(true);
            	return;
            }
            
            ItemName = CurrentItemMeta.getDisplayName();
            
            }
           
 
        if(event.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_BLUE + "Server Compass")){
            event.setCancelled(true);
            user.closeInventory();
        }
       
        String Server = ChatColor.stripColor(ItemName);
        //send to server.
     
        if(ItemName.contains("UHC Games")){
        	GUI.OpenUHCGUI(user);
        	return;
        }
        
        //Profile 
        if(ItemName.contains(ChatColor.stripColor("Crate Keys"))){
        	
        	int NumberOfCrateKeys = Players.GetNumberOfCrateKeys(user.getUniqueId().toString());
        	ItemStack CrateKey = new ItemStack(Material.TRIPWIRE_HOOK, NumberOfCrateKeys);
    		ItemMeta CrateKeyMeta = CrateKey.getItemMeta();
    		CrateKeyMeta.setDisplayName(ChatColor.DARK_PURPLE + "Crate Key");
    		CrateKeyMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click me on a Drop Crate", ChatColor.GRAY + "You have " + ChatColor.GOLD +  NumberOfCrateKeys + ChatColor.GRAY + " Crate Keys left." ));
    		CrateKey.setItemMeta(CrateKeyMeta);
    		
        	
        	user.getInventory().setItem(0, CrateKey);
        	return;
        }
        if(ItemName.contains(ChatColor.stripColor("Free For All Menu"))){
        	GUI.OpenFFAGUI(user);
        	return;
        }
        if(ItemName.contains(ChatColor.stripColor("Arena Menu"))){
        	GUI.OpenARENASUBMENUGUI(user);
        	return;
        }
        if(ItemName.contains(ChatColor.stripColor("Survival Games Menu"))){
        	GUI.OpenSGSUBMENUGUI(user);
        	return;
        }
        if(ItemName.contains(ChatColor.stripColor("Ultra Hardcore Menu"))){
        	GUI.OpenUHCSUBMENUGUI(user);
        	return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("My Clan"))){
            GUI.OpenClanPageGUI(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("Survival Games stats"))){
            GUI.OpenSurvivalGamesStats(user);
            return;
        }

        if(ItemName.contains(ChatColor.stripColor("UHCGames Stats"))){
            GUI.OpenUHCGamesStats(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Free For All"))){
            GUI.OpenFFAStats(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("Ultra Hardcore Meetup stats"))){
            GUI.OpenUHCMStats(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("Ultra Hardcore stats"))){
            GUI.OpenUHCStats(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("Clan Stats"))){
            GUI.OpenClanSurvivalGamesStats(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor(user.getName()))){
            user.closeInventory();
            user.sendMessage(ChatColor.LIGHT_PURPLE + "-----> Click the link to view Web Profile <-----");
            user.sendMessage(ChatColor.RED + info.techwizmatt.ServerCore.Main.Website + "/stats/profile/?u=" + user.getName());
            user.sendMessage(ChatColor.LIGHT_PURPLE + "----------------------------------------");
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("CLOSE STATS"))){
            GUI.OpenUserProfileGUI(user);
            return;
        }
        
        //Compass CLOSE STATS
        
        if(ItemName.contains(ChatColor.stripColor("Close Inventory"))){
            user.closeInventory();
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("FFA-SG"))){
        	
            if(ClickAction == InventoryAction.PICKUP_HALF){
            	GUI.OpenFFAStats(user);
            	return;
            }
            
            GUI.OpenFFAGUI(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("FFA-UHC"))){
        	
            if(ClickAction == InventoryAction.PICKUP_HALF){
            	GUI.OpenFFAUHCStats(user);
            	return;
            }
            
            GUI.OpenFFAUHCGUI(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Games (SG)"))){

        	if(ClickAction == InventoryAction.PICKUP_HALF){
        		GUI.OpenSurvivalGamesStats(user);
            	return;
            }
            GUI.OpenSGGUI(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Particle Effects"))){

        	if(ClickAction == InventoryAction.PICKUP_HALF){
            	
            	return;
            }
            GUI.OpenPlayerParticlesGUI(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Hat Shop"))){

            GUI.OpenHatShop(user);
        }

        if(ItemName.contains(ChatColor.stripColor("Gadget Shop"))){
                GUI.OpenGadgetShop(user);
                return;
            }

        if(ItemName.contains(ChatColor.stripColor("Particle Shop"))){
                GUI.OpenParticleShop(user);
                return;
            }
        if(ItemName.contains(ChatColor.stripColor("Hardcore (UHC)"))){

        	if(ClickAction == InventoryAction.PICKUP_HALF){
        		GUI.OpenUHCStats(user);
            	return;
            }
            GUI.OpenUHCGUI(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Meetup (UHCM)"))){

        	if(ClickAction == InventoryAction.PICKUP_HALF){
        		GUI.OpenUHCMStats(user);
            	return;
            }
            GUI.OpenUHCMGUI(user);
            return;
        }
        
        if(ItemName.contains(ChatColor.stripColor("Arena PVP (APVP)"))){

        	if(ClickAction == InventoryAction.PICKUP_HALF){
        		GUI.OpenUHCMStats(user);
            	return;
            }
            GUI.OpenARENAGUI(user);
            return;
        }

        if(ItemName.contains(ChatColor.stripColor("Clan Members"))){
            GUI.OpenClanMembersGUI(user);
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("Clan Survival Games (CSG)"))){
        	if(ClanAPI.getClanId(user.getUniqueId().toString()) == 0){
        		user.closeInventory();
        		user.sendMessage(ChatColor.RED + "you're not in a clan! Type " +  ChatColor.GOLD + ChatColor.BOLD +"/Clan" + ChatColor.RED + " to get started");
        		return;
        	}

        	if(ClickAction == InventoryAction.PICKUP_HALF){
                GUI.OpenClanPageGUI(user);
            	return;
            }
        	
        	if(ClanAPI.getClanRank(user.getUniqueId().toString()) >= 4){
                GUI.OpenCSGGUI(user);
        	}else{
        		GUI.OpenMCSGGUI(user);
        	}
        
            return;
        }
        if(ItemName.contains(ChatColor.stripColor("?????"))){
            user.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "What Should we add next?");
            user.closeInventory();
            return;
        }
       
        //DEV
        if(ItemName.contains(ChatColor.stripColor("Development Server"))){
            Main.SendToServer(user.getName(), "DEV01", user);
            user.closeInventory();
            return;
        }
        /////
        
        
        
        
        //Cosmetics
        
        
        
        /////
        
        
        
        
        if(ItemName.contains(ChatColor.stripColor("Change Hearts Color"))){
        	Cosmetics.ToggleHeartColor(user);
        	user.closeInventory();
        }
        if(ItemName.contains(ChatColor.stripColor("Toggle Xp Bar"))){
        	Cosmetics.toggleXpBar(user);
        	user.closeInventory();
        }
        if(ItemName.contains(ChatColor.stripColor("Toggle Heart Amount"))){
        	Cosmetics.ToggleOneHeart(user);
        	user.closeInventory();
        }
        if(ItemName.contains(ChatColor.stripColor("Toggle Flight"))){
        	Cosmetics.toggleFlight(user);
        	user.closeInventory();
        }
        if(ItemName.contains(ChatColor.stripColor("Toggle Helix Effect"))){
        	Cosmetics.toggleHelixEffect(user);

        	user.sendMessage(ChatColor.RED + "This Effect is not available at the moment, Sorry for inconvineces. ");
        	user.closeInventory();
        }
        
//        Toggle Helix Effect
        
        //§8»§9 " + user.getName() + " §8«
        

        
//        ---
       
       if(getServers.GetOnlineServerLists(20).contains(Server)){
    	   Main.SendToServer(user.getName(), Server, user);
    	   user.closeInventory();
       }
        	}
        
    }
    
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerBlockRemoval(BlockBreakEvent BreakEvent){
    	if(Render.AddLocs){
    		Player user = BreakEvent.getPlayer();
    		if(user.getName() == Render.addLocPlayer.getName()){
        		if(Render.locations.size() >= 15){
        			user.sendMessage(ChatColor.RED + "You have hit " + Render.locations.size() + " blocks out of 15. /statwall StatName - to finish.");
        			BreakEvent.setCancelled(true);
        		}else{
        			Location clickedLoc = BreakEvent.getBlock().getLocation();
            		Render.locations.add(clickedLoc);
            		user.sendMessage(ChatColor.GREEN + "Added to Locations" + "[" + ChatColor.LIGHT_PURPLE + Render.locations.size() + ChatColor.GREEN + "]");

        		}
    		}else{
    			BreakEvent.setCancelled(true);
    		}
    		
    		
    	}else{
    		if(!Main.MaintenceMode){
        		BreakEvent.setCancelled(true);
        	}
    	}
    	
    	
    }
    
    @EventHandler //PlaceRemoval.
    public void PlayerPlace(BlockPlaceEvent PlaceEvent){
    	if(!Main.MaintenceMode){
    		PlaceEvent.setCancelled(true);
    	}
    }
    
    
    @EventHandler //PlayerPickUpItemEvent.
    public void PlayerPickUpItem(PlayerPickupItemEvent ItemEvent){
    	if(!Main.MaintenceMode){
    		ItemEvent.setCancelled(true);
    	}
    }
    
    @EventHandler //Dropping items
    public void StopItemDrop(PlayerDropItemEvent DropItem){ 
        DropItem.setCancelled(true);
    }
    
    @EventHandler
    public void FoodLevel(FoodLevelChangeEvent event){
    	event.setCancelled(true);
    }
   
   
   
   
}