package info.techwizmatt.ServerSelector;

import java.util.*;


//2017 COPYWRIGHT - TECHWIZMATT IN OFFLICATION WITH SONDER NETWORK

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import info.techwizmatt.ServerCore.API.ClanAPI;
import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.API.Players;
import info.techwizmatt.ServerSelector.Servers.getServers;
import io.netty.util.internal.ThreadLocalRandom;
import stats.*;

public class GUI {
	
	@SuppressWarnings("deprecation")
public static ItemStack OfflineServerItem(){
		ItemStack OfflineServerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0); // 11 not 0 for blue
		ItemMeta OfflineServerItemMeta = OfflineServerItem.getItemMeta();
		OfflineServerItemMeta.setDisplayName(ChatColor.MAGIC + "00000000");
		OfflineServerItemMeta.setLore(null);
		OfflineServerItem.setItemMeta(OfflineServerItemMeta);

		return OfflineServerItem;
	}
	
	public static ItemStack BGItem(){
		ItemStack OfflineServerItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta OfflineServerItemMeta = OfflineServerItem.getItemMeta();
		OfflineServerItemMeta.setDisplayName(ChatColor.MAGIC + "00000000");
		OfflineServerItemMeta.setLore(null);
		OfflineServerItem.setItemMeta(OfflineServerItemMeta);

		return OfflineServerItem;
	}
	
	public static int  nextLineinServer(int SelectorSlot){
		
		 //5
		 //14
		 //23
		 //32
		 //41
		 //50
		
		if(SelectorSlot == 3 || SelectorSlot == 12 || SelectorSlot == 21 || SelectorSlot == 30 || SelectorSlot == 39 || SelectorSlot == 48 ){
			return (SelectorSlot + 6);
		}
		return (SelectorSlot + 1);
	}
	
	public static int nextLineinOfflineServer(int SelectorSlot){
		
		 //5
		 //14
		 //23
		 //32
		 //41
		 //50
		
		if(SelectorSlot == 9 || SelectorSlot == 18 || SelectorSlot == 27 || SelectorSlot == 36 || SelectorSlot == 45 ){
			return (SelectorSlot + 5);
		}
		return (SelectorSlot + 1);
	}
	
	public static boolean isServerOpentoJoin(ItemStack item){
		if(item.getType().equals(Material.GOLD_BLOCK) || item.getType().equals(Material.REDSTONE_BLOCK) || item.getType().equals(Material.WOOL)){
			return false;
		}
		return true;
	}
		
@SuppressWarnings("deprecation")
public static void OpenServerCompassGUI(Player user){
	
	
	
		
		
		Inventory ServerCompassINV = Bukkit.createInventory(null , 45 , ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Game Menu");
	
		
		ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);
		
		ItemMeta Meta = Grey.getItemMeta();
		Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
		Purple.setItemMeta(Meta);
		Grey.setItemMeta(Meta);



		LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();

		Colors.add(Grey);
		Colors.add(Purple);


		ItemStack[] ColorsArr = new ItemStack[Colors.size()];
		ColorsArr = Colors.toArray(ColorsArr);



			for(int i=0; i<45; i++){
				int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
				ServerCompassINV.setItem(i, ColorsArr[randomNum]);
			}

		
		
//		 for (int i = 0; i < 9; i++)
//	     {  
//				 ServerCompassINV.setItem(i, OfflineServerItem());
//
//	     }
//		 
//		 for (int i = 36; i < 45; i++)
//	     {  
//				 ServerCompassINV.setItem(i, OfflineServerItem());
//
//	     }
		
		
		ItemStack DevelopmentServer = new ItemStack (Material.SAPLING);
		ItemMeta DevelopmentServerMeta = DevelopmentServer.getItemMeta();
		DevelopmentServerMeta.setDisplayName(ChatColor.DARK_GREEN + "Development Server");
		DevelopmentServerMeta.setLore(Arrays.asList("This server is for Development Only! (OP ONLY)"));
		DevelopmentServer.setItemMeta(DevelopmentServerMeta);
//		
		ItemStack FAAServer = new ItemStack (Material.DIAMOND_SWORD);
		ItemMeta FAAServerMeta = FAAServer.getItemMeta();
		FAAServerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Free For All Menu");
		int ServerCount3 = getServers.GetOnlineServerListPerType(1);
		FAAServerMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount3 + "" + ChatColor.GRAY  + " Players in FFA"));
		FAAServer.setItemMeta(FAAServerMeta);

//		ItemStack ClansServer = new ItemStack (Material.DIAMOND_SWORD);
//		ItemMeta ClansServerMeta = FAAServer.getItemMeta();
//		ClansServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Clans Menu");
//		int ServerCount3 = getServers.GetOnlineServerListPerType(0) + getServers.GetOnlineServerListPerType(3);
//		ClansServerMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount3 + "" + ChatColor.GRAY  + " Players in Clans"));
//		ClansServer.setItemMeta(ClansServerMeta);
		
		ItemStack SGServer = new ItemStack (Material.BOW);
		ItemMeta SGMeta = SGServer.getItemMeta();
		SGMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "UHC Games");
		int ServerCount2 = getServers.GetOnlineServerListPerType(7);
		SGMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount2 + "" + ChatColor.GRAY  + " Players in UHC Games" ));
		SGServer.setItemMeta(SGMeta);
		
//		ItemStack UHCMeetupServer = new ItemStack (Material.GOLDEN_APPLE, 1, (short)1);
//		ItemMeta UHCMeetupServerMeta = UHCMeetupServer.getItemMeta();
//		UHCMeetupServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Hardcore Menu");
//		int ServerCount1 = getServers.GetOnlineServerListPerType(3) + getServers.GetOnlineServerListPerType(4);
//		UHCMeetupServerMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount1 + "" + ChatColor.GRAY  +  " Players in UHC" ));
//		UHCMeetupServer.setItemMeta(UHCMeetupServerMeta);
//		
//		ItemStack ArenaServer = new ItemStack (Material.LAVA_BUCKET);
//		ItemMeta ArenaServerMeta = ArenaServer.getItemMeta();
//		ArenaServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Arena Menu");
//		int ServerCount = getServers.GetOnlineServerListPerType(1) + getServers.GetOnlineServerListPerType(5);
//		ArenaServerMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount + ChatColor.GRAY  + " Players in Arena" ));
//		ArenaServer.setItemMeta(ArenaServerMeta);
//		
//		ItemStack WhatShouldWeAdd = new ItemStack (Material.REDSTONE_BLOCK);
//		ItemMeta WhatShouldWeAddMeta = WhatShouldWeAdd.getItemMeta();
//		WhatShouldWeAddMeta.setDisplayName(ChatColor.RED + "?????");
//		WhatShouldWeAddMeta.setLore(Arrays.asList("What should we add? Let us know on the Forums" ));
//		WhatShouldWeAdd.setItemMeta(WhatShouldWeAddMeta);
//		 
//		ItemStack Clock = new ItemStack (Material.WATCH);
//		ItemMeta ClockMeta = Clock.getItemMeta();
//		ClockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Close Inventory");
//		ClockMeta.setLore(Arrays.asList("~~~~~~~~~~~~~~" ));
//		Clock.setItemMeta(ClockMeta);
//		

		
		
		
		//Set Item in Inventory
//		ServerCompassINV.setItem(20, FAAServer);
		ServerCompassINV.setItem(21, FAAServer);
//		ServerCompassINV.setItem(21, ArenaServer); Coming soon...
		ServerCompassINV.setItem(23, SGServer);
//		ServerCompassINV.setItem(24, UHCMeetupServer);

//		ServerCompassINV.setItem(10, WhatShouldWeAdd);
//		ServerCompassINV.setItem(12, WhatShouldWeAdd);
//		ServerCompassINV.setItem(14, WhatShouldWeAdd);
//		ServerCompassINV.setItem(16, WhatShouldWeAdd);
//		ServerCompassINV.setItem(18, WhatShouldWeAdd);

		//Checks to see if user can teleport to the Development Server.
		if(user.isOp() || user.hasPermission("DevServerAccess.enabled")){
			ServerCompassINV.setItem(9, DevelopmentServer);
		}
		//012345678
		user.openInventory(ServerCompassINV);
	}

public static void OpenFFASUBMENUGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 45 , ChatColor.RED + "" + ChatColor.BOLD + "Free For All");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	

	
	ItemStack SGServer = new ItemStack (Material.BOW);
	ItemMeta SGMeta = SGServer.getItemMeta();
	SGMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "FFA-SG");
	SGMeta.setLore(Arrays.asList("Teleport to FFA server with SG settings" ));
	SGServer.setItemMeta(SGMeta);
	
	ItemStack UHCMeetupServer = new ItemStack (Material.GOLDEN_APPLE, 1, (short)1);
	ItemMeta UHCMeetupServerMeta = UHCMeetupServer.getItemMeta();
	UHCMeetupServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "FFA-UHC");
	UHCMeetupServerMeta.setLore(Arrays.asList("TTeleport to FFA server with UHC settings" ));
	UHCMeetupServer.setItemMeta(UHCMeetupServerMeta);
	
	FFAINV.setItem(20, SGServer);
	FFAINV.setItem(24, UHCMeetupServer);
	

	user.openInventory(FFAINV);
}
	

public static void OpenARENASUBMENUGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 45 , ChatColor.RED + "" + ChatColor.BOLD + "Arena PVP");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	

	
	ItemStack ArenaServer = new ItemStack (Material.LAVA_BUCKET);
	ItemMeta ArenaServerMeta = ArenaServer.getItemMeta();
	ArenaServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "US ArenaPVP");
	int ServerCount = getServers.GetOnlineServerListPerType(1) + getServers.GetOnlineServerListPerType(5);
	ArenaServerMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount + ChatColor.GRAY  + " Players in Arena" ));
	ArenaServer.setItemMeta(ArenaServerMeta);
	
	ItemStack ArenaServerEU = new ItemStack (Material.WATER_BUCKET);
	ItemMeta ArenaServerEUMeta = ArenaServerEU.getItemMeta();
	ArenaServerEUMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "EU ArenaPVP");
	ArenaServerEUMeta.setLore(Arrays.asList(ChatColor.GREEN + "" + ServerCount + ChatColor.GRAY  + " Players in Arena" ));
	ArenaServerEU.setItemMeta(ArenaServerEUMeta);
	
	FFAINV.setItem(20, ArenaServer);
	FFAINV.setItem(24, ArenaServerEU);
	

	user.openInventory(FFAINV);
}

public static void OpenSGSUBMENUGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 45 , ChatColor.RED + "" + ChatColor.BOLD + "Survival Games");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	

	
    ItemStack SGServer = new ItemStack(Material.BOW);
    ItemMeta SGMeta = SGServer.getItemMeta();
    SGMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Survival Games (SG)");
    SGMeta.setLore(Arrays.asList("Teleport to Survival Games" ));
    SGServer.setItemMeta(SGMeta);
    
    ItemStack CSGServer = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta CSGMeta = CSGServer.getItemMeta();
    CSGMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Clan Survival Games (CSG)");
    CSGMeta.setLore(Arrays.asList("Teleport to Clan Survival Games"));
    CSGServer.setItemMeta(CSGMeta);
	
	FFAINV.setItem(20, SGServer);
	FFAINV.setItem(24, CSGServer);
	

	user.openInventory(FFAINV);
}

public static void OpenUHCSUBMENUGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 45 , ChatColor.RED + "" + ChatColor.BOLD + "Ultra HardCore");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	

	
    ItemStack UHCServer = new ItemStack(Material.GOLDEN_APPLE);
    ItemMeta UHCServerMeta = UHCServer.getItemMeta();
    UHCServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Hardcore Meetup (UHCM)");
    UHCServerMeta.setLore(Arrays.asList("Teleport to Ultra Hardcore" ));
    UHCServer.setItemMeta(UHCServerMeta);
    
    ItemStack UHCMeetupServer = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
    ItemMeta UHCMeetupServerMeta = UHCMeetupServer.getItemMeta();
    UHCMeetupServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Hardcore (UHC)");
    UHCMeetupServerMeta.setLore(Arrays.asList("Teleport to Ultra Hardcore"));
    UHCMeetupServer.setItemMeta(UHCMeetupServerMeta);
	
	FFAINV.setItem(20, UHCServer);
	FFAINV.setItem(24, UHCMeetupServer);
	

	user.openInventory(FFAINV);
}
public static void OpenServerPackageGUI(Player user){
		Inventory ServerPackageINV = Bukkit.createInventory(null , 45 , ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Cosmetic Menu");
		
		ItemStack PlayerParticles = new ItemStack(Material.NETHER_STAR);
		ItemMeta PlayerParticlesMeta = PlayerParticles.getItemMeta();
		PlayerParticlesMeta.setDisplayName(ChatColor.GRAY + "Particle Effects");
		PlayerParticlesMeta.setLore(Arrays.asList("§5Add special effects to your Avatar." ));
		PlayerParticles.setItemMeta(PlayerParticlesMeta);

		ItemStack HatShop = new ItemStack(Material.HOPPER);
		ItemMeta HatShopMeta = PlayerParticles.getItemMeta();
		HatShopMeta.setDisplayName(ChatColor.GRAY + "Hat Shop");
		HatShopMeta.setLore(Arrays.asList("§5Add style to your head" ));
		HatShop.setItemMeta(HatShopMeta);

		ItemStack GadgetShop = new ItemStack(Material.BOW);
		ItemMeta GadgetShopMeta = PlayerParticles.getItemMeta();
		GadgetShopMeta.setDisplayName(ChatColor.GRAY + "Gadget Shop");
		GadgetShopMeta.setLore(Arrays.asList("§5Show off some tricks in lobby" ));
		GadgetShop.setItemMeta(GadgetShopMeta);
		
		int NumberOfCrateKeys = Players.GetNumberOfCrateKeys(user.getUniqueId().toString());

		if(NumberOfCrateKeys <= 0){
			NumberOfCrateKeys = 1;
		}
		
		ItemStack CrateKey = new ItemStack(Material.TRIPWIRE_HOOK, NumberOfCrateKeys);
		ItemMeta CrateKeyMeta = CrateKey.getItemMeta();
		CrateKeyMeta.setDisplayName(ChatColor.DARK_PURPLE + "Crate Keys");
		CrateKeyMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to add me to the HotBar", ChatColor.GRAY + "You have " + ChatColor.GOLD + Players.GetNumberOfCrateKeys(user.getUniqueId().toString()) + ChatColor.GRAY +  " Crate Keys left" ));
		CrateKey.setItemMeta(CrateKeyMeta);

		ServerPackageINV.setItem(10, GadgetShop);
		ServerPackageINV.setItem(12, PlayerParticles);
		ServerPackageINV.setItem(14, CrateKey);
		ServerPackageINV.setItem(16, HatShop);
		user.openInventory(ServerPackageINV);
	}
public static void OpenHatShop(Player user){
	Inventory HatShop = Bukkit.createInventory(null,45, ChatColor.LIGHT_PURPLE + ""+ChatColor.BOLD + "Hat Shop");

	for (String name : CosmeticAPI.names) {
		ItemStack Hats = new ItemStack(Material.getMaterial(CosmeticAPI.GetItems()), 1);
		ItemMeta HatsMeta = Hats.getItemMeta();
		HatsMeta.setDisplayName(name);
		HatsMeta.setLore(Arrays.asList("Price: " + CosmeticAPI.GetPrices(), ""));
		Hats.setItemMeta(HatsMeta);
		HatShop.setItem(10,Hats);


	}

	ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	SkullMeta meta = (SkullMeta) skull.getItemMeta();
	meta.setOwner(user.getName());
	meta.setDisplayName("§f»§5 Your Coins §f«");
	meta.setLore(Arrays.asList( ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Coins§f: §7" + CoinAPI.GetTokens(user)));
	skull.setItemMeta(meta);
	HatShop.setItem(4,skull);



	user.openInventory(HatShop);
}

public static void OpenGadgetShop(Player user) {
	Inventory HatShop = Bukkit.createInventory(null, 45, "§d§lGadget Shop");
	CosmeticAPI cosmeticAPI = new CosmeticAPI();

	ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	SkullMeta meta = (SkullMeta) skull.getItemMeta();
	meta.setOwner(user.getName());
	meta.setDisplayName("§f»§5 Your Coins §f«");
	meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Coins§f: §7" + CoinAPI.GetTokens(user)));
	skull.setItemMeta(meta);
	HatShop.setItem(4, skull);

	user.openInventory(HatShop);

}
public static void OpenParticleShop(Player user) {
		Inventory HatShop = Bukkit.createInventory(null, 45, "§d§lGadget Shop");
		CosmeticAPI cosmeticAPI = new CosmeticAPI();

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(user.getName());
		meta.setDisplayName("§f»§5 Your Coins §f«");
		meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Coins§f: §7" + CoinAPI.GetTokens(user)));
		skull.setItemMeta(meta);
		HatShop.setItem(4, skull);
		user.openInventory(HatShop);

	}



	public static void OpenFFAGUI(Player user){

	Inventory FFAINV = Bukkit.createInventory(null , 45 , ChatColor.RED + "" + ChatColor.BOLD + "Free For All");
	ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
	ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);

	ItemMeta Meta = Grey.getItemMeta();
	Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
	Purple.setItemMeta(Meta);
	Grey.setItemMeta(Meta);



	LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();

	Colors.add(Grey);
	Colors.add(Purple);


	ItemStack[] ColorsArr = new ItemStack[Colors.size()];
	ColorsArr = Colors.toArray(ColorsArr);



	for(int i=0; i<45; i++){
		int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
		FFAINV.setItem(i, ColorsArr[randomNum]);
	}
	FFAINV.setItem(4, OfflineServerItem());
	FFAINV.setItem(13, OfflineServerItem());
	FFAINV.setItem(22, OfflineServerItem());
	FFAINV.setItem(31, OfflineServerItem());
	FFAINV.setItem(40, OfflineServerItem());
	int CurrentServerOn = 0; //What block do you wanna start at?
		 int OfflineServerOn = 5;

		 
		 
		 
		 for(String SelectedServer: getServers.GetOnlineServerLists(1)){
				ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
				ItemMeta ServerMeta = Server.getItemMeta();
			 	ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
				ServerMeta.setLore(Arrays.asList(ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "Free For All", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "40"));
				Server.setItemMeta(ServerMeta);
				
				
				
				if(isServerOpentoJoin(Server)){
					FFAINV.setItem(CurrentServerOn, Server);
					CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
				}else{
					FFAINV.setItem(OfflineServerOn, Server);
					OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
				}
				
		 }
		user.openInventory(FFAINV);
	}

public static void OpenFFAUHCGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Free For All UHC");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	
	FFAINV.setItem(49, OfflineServerItem());	
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5;

	 
	 
	 
	 for(String SelectedServer: getServers.GetOnlineServerLists(1)){
			ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
			ItemMeta ServerMeta = Server.getItemMeta();
			ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
			ServerMeta.setLore(Arrays.asList(GameState.CurrentGameState(SelectedServer) ,ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "Free For All", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "100"));
			Server.setItemMeta(ServerMeta);
			
			
			
			if(isServerOpentoJoin(Server)){
				FFAINV.setItem(CurrentServerOn, Server);
				CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
			}else{
				FFAINV.setItem(OfflineServerOn, Server);
				OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
			}
			
	 }
	user.openInventory(FFAINV);
}

public static void OpenSGGUI(Player user){
		
		Inventory SGINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Survival Games");
	
		SGINV.setItem(4, OfflineServerItem());	 
		SGINV.setItem(13, OfflineServerItem());	
		SGINV.setItem(22, OfflineServerItem());	
		SGINV.setItem(31, OfflineServerItem());	
		SGINV.setItem(40, OfflineServerItem());	
		SGINV.setItem(49, OfflineServerItem());	
	        
		 int CurrentServerOn = 0; //What block do you wanna start at?
		 int OfflineServerOn = 5; //What block do you wanna start at?
		 
		 for(String SelectedServer: getServers.GetOnlineServerLists(0)){
				ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
				ItemMeta ServerMeta = Server.getItemMeta();
				ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
				ServerMeta.setLore(Arrays.asList(GameState.CurrentGameState(SelectedServer) ,ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "Survival Games", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "24"));
				Server.setItemMeta(ServerMeta);
				
				if(isServerOpentoJoin(Server)){
					SGINV.setItem(CurrentServerOn, Server);
					CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
				}else{
					SGINV.setItem(OfflineServerOn, Server);
					OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
				}
				


		 }
		user.openInventory(SGINV);
	}

public static void OpenARENAGUI(Player user){
	
	Inventory SGINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Arena PVP");

	SGINV.setItem(4, OfflineServerItem());	 
	SGINV.setItem(13, OfflineServerItem());	
	SGINV.setItem(22, OfflineServerItem());	
	SGINV.setItem(31, OfflineServerItem());	
	SGINV.setItem(40, OfflineServerItem());	
	SGINV.setItem(49, OfflineServerItem());	
        
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5; //What block do you wanna start at?
	 
	 for(String SelectedServer: getServers.GetOnlineServerLists(5)){
			ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
			ItemMeta ServerMeta = Server.getItemMeta();
			ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
			ServerMeta.setLore(Arrays.asList(GameState.CurrentGameState(SelectedServer) ,ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "Arena PVP", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "24"));
			Server.setItemMeta(ServerMeta);
			
			if(isServerOpentoJoin(Server)){
				SGINV.setItem(CurrentServerOn, Server);
				CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
			}else{
				SGINV.setItem(OfflineServerOn, Server);
				OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
			}
			


	 }
	user.openInventory(SGINV);
}


public static void OpenCSGGUI(Player user){
	int UserClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	int FirstClanID = 0;
	int SecondClanID = 0;
	String FirstClanName = "";
	String SecondClanName = "";
	String ClanVClan = "";
	String SpecialMessage = "";
	Inventory SGINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Clan Survival Games");
	SGINV.setItem(4, OfflineServerItem());	 
	SGINV.setItem(13, OfflineServerItem());	
	SGINV.setItem(22, OfflineServerItem());	
	SGINV.setItem(31, OfflineServerItem());	
	SGINV.setItem(40, OfflineServerItem());	
	SGINV.setItem(49, OfflineServerItem());	
	
	 
	 
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5;
	 for(String SelectedServer: getServers.GetOnlineServerLists(2)){
		 	List<Integer> ClansInGame = new LinkedList<Integer>();
		 	List<String> OnlinePlayers = getServers.GetOnlinePlayerListServer(SelectedServer);
		 	List<String> PlayersInFirstClan = new LinkedList<String>();
		 	
		 	for(String PlayerName: OnlinePlayers){
		 		if(!ClansInGame.contains(ClanAPI.getClanIdFromName(PlayerName))){
		 			ClansInGame.add(ClanAPI.getClanIdFromName(PlayerName));
		 		}
		 	}
 			Integer[] Clans = ClansInGame.toArray(new Integer[ClansInGame.size()]);

	 		if(ClansInGame.size() >= 2){
	 			FirstClanID = Clans[0];
	 			SecondClanID = Clans[1];
	 			ClanVClan = ChatColor.GOLD + ClanAPI.getClanName(FirstClanID) + ChatColor.RED + " vs. " + ChatColor.GOLD + ClanAPI.getClanName(SecondClanID);
	 		}else if(ClansInGame.size() == 1){
	 			FirstClanID = Clans[0];
		 		ClanVClan = ChatColor.GOLD + ClanAPI.getClanName(FirstClanID) + ChatColor.RED + " vs. " + ChatColor.GOLD + "NO CLAN";
	 		}else{
	 			ClanVClan = ChatColor.GREEN + "No Clans in Game, Join now!";
	 		}
		 	
	 		if(ClansInGame.contains(UserClanID)){
	 			SpecialMessage = ChatColor.GREEN + "Your clan is in this game!";
	 		}else if(ClansInGame.size() == 1){
	 			SpecialMessage = ChatColor.GREEN + "This game is waiting for a Clan!";
	 		}else if(ClansInGame.size() >= 2){
	 			SpecialMessage = ChatColor.RED + "This game already has two clans";
	 		}
		 
	 		List<String> Lore = new ArrayList<>(Arrays.asList(
	 				SpecialMessage , "Players: " + getServers.ServerPlayerCount(SelectedServer, false), 
					ClanVClan));
			ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
			ItemMeta ServerMeta = Server.getItemMeta();
			ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
			ServerMeta.setLore(Lore);
			Server.setItemMeta(ServerMeta);
			
			
			if(isServerOpentoJoin(Server)){
				SGINV.setItem(CurrentServerOn, Server);
				CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
			}else{
				SGINV.setItem(OfflineServerOn, Server);
				OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
			}
	 }
	user.openInventory(SGINV);

}

public static void OpenMCSGGUI(Player user){
	int ClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	int OtherClanID = 0;
	Inventory SGINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Games with your Clan");
	SGINV.setItem(4, OfflineServerItem());	 
	SGINV.setItem(13, OfflineServerItem());	
	SGINV.setItem(22, OfflineServerItem());	
	SGINV.setItem(31, OfflineServerItem());	
	SGINV.setItem(40, OfflineServerItem());	
	SGINV.setItem(49, OfflineServerItem());	
	 
	 
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5;
	 for(String SelectedServer: getServers.GetOnlineServerLists(2)){
		 	List<Integer> ClansInGame = new LinkedList<Integer>();
		 	List<String> OnlinePlayers = getServers.GetOnlinePlayerListServer(SelectedServer);
		 	List<String> AllowedRank = new LinkedList<String>();
		 	
		 	
		 	
		 	for(String PlayerName: OnlinePlayers){
		 		if(ClanAPI.getClanIdFromName(PlayerName) == ClanID){
		 			if(!ClansInGame.contains(ClanID)){
		 				ClansInGame.add(ClanID);
		 			}
			 		if(ClanAPI.getClanRankFromUsername(PlayerName) >= 4){
			 			AllowedRank.add(PlayerName);
		 			}
		 		}else{
		 			if(!ClansInGame.contains(ClanAPI.getClanIdFromName(PlayerName))){
		 				ClansInGame.add(ClanAPI.getClanIdFromName(PlayerName));
		 			}
		 		}
		 		
		 		if(OtherClanID == 0){
		 			if(ClansInGame.size() >= 2){
		 				for(int Clan: ClansInGame){
		 					if(Clan != ClanID){
								OtherClanID = Clan;
							}
		 				}
		 			}
		 		}
		 		
		 	}
		 	if(!AllowedRank.isEmpty()){
		 		
		 		List<String> LoreBefore = new ArrayList<>(Arrays.asList(
						"Players: " + getServers.ServerPlayerCount(SelectedServer, false), 
						ChatColor.GOLD + ClanAPI.getClanName(ClanID) + ChatColor.RED + " vs. " + ChatColor.GOLD + ClanAPI.getClanName(OtherClanID),
						ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Player from your Clan: ") );
		 		List<String> Lore = new ArrayList<String>(LoreBefore);
			 	Lore.addAll(AllowedRank);
				ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer), getServers.ServerPlayerCount(SelectedServer, true));
				ItemMeta ServerMeta = Server.getItemMeta();
				ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
				ServerMeta.setLore(Lore);
				Server.setItemMeta(ServerMeta);
				
				if(isServerOpentoJoin(Server)){
					SGINV.setItem(CurrentServerOn, Server);
					CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
				}else{
					SGINV.setItem(OfflineServerOn, Server);
					OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
				}
				
		 	}

	 }
	user.openInventory(SGINV);
}
	
public static void OpenUHCGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "UHC Games");
	
	ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
	ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);
	
	ItemMeta Meta = Grey.getItemMeta();
	Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
	Purple.setItemMeta(Meta);
	Grey.setItemMeta(Meta);

	

	LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();
	
	Colors.add(Grey);
	Colors.add(Purple);

	
	ItemStack[] ColorsArr = new ItemStack[Colors.size()];
	ColorsArr = Colors.toArray(ColorsArr);
	

	
		for(int i=0; i<54; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
			FFAINV.setItem(i, ColorsArr[randomNum]);
		}
	
	
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	
	FFAINV.setItem(49, OfflineServerItem());	
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5;
	 for(String SelectedServer: getServers.GetOnlineServerLists(7)){
		 
			ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer) , getServers.ServerPlayerCount(SelectedServer, true));
			ItemMeta ServerMeta = Server.getItemMeta();
			ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
			ServerMeta.setLore(Arrays.asList(GameState.CurrentGameState(SelectedServer) ,ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "UHC Games", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "24"));
			Server.setItemMeta(ServerMeta);
			
			if(isServerOpentoJoin(Server)){
				FFAINV.setItem(CurrentServerOn, Server);
				CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
			}else{
				FFAINV.setItem(OfflineServerOn, Server);
				OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
			}
			
	 }
	user.openInventory(FFAINV);
}

public static void OpenUHCMGUI(Player user){
	
	Inventory FFAINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "Ultra Hardcore Meetup");
	FFAINV.setItem(4, OfflineServerItem());	 
	FFAINV.setItem(13, OfflineServerItem());	
	FFAINV.setItem(22, OfflineServerItem());	
	FFAINV.setItem(31, OfflineServerItem());	
	FFAINV.setItem(40, OfflineServerItem());	
	FFAINV.setItem(49, OfflineServerItem());	
	 int CurrentServerOn = 0; //What block do you wanna start at?
	 int OfflineServerOn = 5;
	 for(String SelectedServer: getServers.GetOnlineServerLists(4)){
		 
			ItemStack Server = new ItemStack(GameState.CurrentGameStateMaterial(SelectedServer) , getServers.ServerPlayerCount(SelectedServer, true));
			ItemMeta ServerMeta = Server.getItemMeta();
			ServerMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + SelectedServer);
			ServerMeta.setLore(Arrays.asList(GameState.CurrentGameState(SelectedServer) ,ChatColor.GRAY + "Server" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "" + ChatColor.ITALIC + SelectedServer,ChatColor.GRAY + "Gamemode" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + "Ultra Hardcore Meet-up", ChatColor.GREEN + "" + getServers.ServerPlayerCount(SelectedServer, false) + ChatColor.GRAY + " / " + ChatColor.RED + "24"));
			Server.setItemMeta(ServerMeta);
			
			if(isServerOpentoJoin(Server)){
				FFAINV.setItem(CurrentServerOn, Server);
				CurrentServerOn = GUI.nextLineinServer(CurrentServerOn);
			}else{
				FFAINV.setItem(OfflineServerOn, Server);
				OfflineServerOn = GUI.nextLineinOfflineServer(OfflineServerOn);
			}
	 }
	user.openInventory(FFAINV);
}

public static void OpenPlayerParticlesGUI(Player user){
	Inventory PPINV = Bukkit.createInventory(null , 9 , ChatColor.RED + "" + ChatColor.BOLD + "Player Particles");
	 for (int i = 0; i < 9; i++)
     {  
		 PPINV.setItem(i, OfflineServerItem());
     }
	 


		ItemStack hearts = new ItemStack(Material.POTION, 1);
		PotionMeta metaPotion = (PotionMeta) hearts.getItemMeta();
		metaPotion.setDisplayName(ChatColor.DARK_PURPLE + "Change Hearts Color");
		hearts.setItemMeta(metaPotion);
		
		ItemStack xp = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta xpmeta = xp.getItemMeta();
		xpmeta.setDisplayName(ChatColor.DARK_PURPLE + "Toggle Xp Bar");
		xp.setItemMeta(xpmeta);
		
		ItemStack oneheart = new ItemStack(Material.REDSTONE);
		ItemMeta ohmeta = oneheart.getItemMeta();
		ohmeta.setDisplayName(ChatColor.DARK_PURPLE + "Toggle Heart Amount");
		oneheart.setItemMeta(ohmeta);
		
		ItemStack fly = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta flymeta = fly.getItemMeta();
		flymeta.setDisplayName(ChatColor.DARK_PURPLE + "Toggle Flight");
		fly.setItemMeta(flymeta);
		
		ItemStack particleOne = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta pometa = particleOne.getItemMeta();
		pometa.setDisplayName(ChatColor.DARK_PURPLE + "Toggle Particle Option 1");
		particleOne.setItemMeta(pometa);
		
		ItemStack pets = new ItemStack(Material.NAME_TAG);
		ItemMeta petsMeta = particleOne.getItemMeta();
		petsMeta.setDisplayName(ChatColor.DARK_PURPLE + "Pets");
		pets.setItemMeta(petsMeta);
		
		
		ItemStack helix = new ItemStack(Material.ARROW);
		ItemMeta helixMeta = helix.getItemMeta();
		helixMeta.setDisplayName(ChatColor.DARK_PURPLE + "Toggle Helix Effect");
		helix.setItemMeta(helixMeta);
		
		PPINV.setItem(0, hearts);
		PPINV.setItem(1, xp);
		PPINV.setItem(2, oneheart);
		PPINV.setItem(3, fly);
		PPINV.setItem(4, helix);
//		PPINV.setItem(4, particleOne);
//		PPINV.setItem(8, pets);
	 
	 
		user.openInventory(PPINV);
	
}

public static void OpenUserProfileGUI(Player user){
	int ClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	Inventory ProfieINV = Bukkit.createInventory(null , 45 , ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + user.getName() + "'s Profile");
	ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
	ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);

	ItemMeta Meta = Grey.getItemMeta();
	Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
	Purple.setItemMeta(Meta);
	Grey.setItemMeta(Meta);



	LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();

	Colors.add(Grey);
	Colors.add(Purple);


	ItemStack[] ColorsArr = new ItemStack[Colors.size()];
	ColorsArr = Colors.toArray(ColorsArr);



	for(int i=0; i<45; i++){
		int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
		ProfieINV.setItem(i, ColorsArr[randomNum]);
	}
	ItemStack FAAServer = new ItemStack (Material.IRON_SWORD);
	ItemMeta FAAServerMeta = FAAServer.getItemMeta();
	FAAServerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Free For All");
	FAAServerMeta.setLore(Arrays.asList("§7Free for all Statistics"));
	FAAServer.setItemMeta(FAAServerMeta);
	
	ItemStack SGServer = new ItemStack (Material.GOLDEN_APPLE);
	ItemMeta SGMeta = SGServer.getItemMeta();
	SGMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "UHCGames Stats");
	SGMeta.setLore(Arrays.asList("§7UHC Games Statistics" ));
	SGServer.setItemMeta(SGMeta);
	
	ItemStack CSGServer = new ItemStack (Material.DIAMOND_SWORD);
	ItemMeta CSGMeta = CSGServer.getItemMeta();
	CSGMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "My Clan");

	if(ClanID == 0){
		CSGMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Clan Surival Games stats");
		CSGMeta.setLore(Arrays.asList(ChatColor.RED + "You need to be in a clan to" , ChatColor.RED + "view clan information."));

	}else{
		CSGMeta.setLore(Arrays.asList(""));
	}
	CSGServer.setItemMeta(CSGMeta);
	
	ItemStack UHCServer = new ItemStack (Material.GOLDEN_APPLE);
	ItemMeta UHCServerMeta = UHCServer.getItemMeta();
	UHCServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Hardcore Meetup stats");
	UHCServerMeta.setLore(Arrays.asList("Teleport to Ultra Hardcore" ));
	UHCServer.setItemMeta(UHCServerMeta);
	
	ItemStack UHCMeetupServer = new ItemStack (Material.GOLDEN_APPLE, 1, (short)1);
	ItemMeta UHCMeetupServerMeta = UHCMeetupServer.getItemMeta();
	UHCMeetupServerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Hardcore stats");
	UHCMeetupServerMeta.setLore(Arrays.asList("Teleport to Ultra Hardcore" ));
	UHCMeetupServer.setItemMeta(UHCMeetupServerMeta);
	
	ItemStack WhatShouldWeAdd = new ItemStack (Material.REDSTONE_BLOCK);
	ItemMeta WhatShouldWeAddMeta = WhatShouldWeAdd.getItemMeta();
	WhatShouldWeAddMeta.setDisplayName(ChatColor.RED + "?????");
	WhatShouldWeAddMeta.setLore(Arrays.asList("§7What should we add? Let us know at our website @ §5Sonder.Network" ));
	WhatShouldWeAdd.setItemMeta(WhatShouldWeAddMeta);
	 
	
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwner(user.getName());
    meta.setDisplayName("§8»§9 " + user.getName() + " §8«");
    meta.setLore(Arrays.asList( ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Coins§f: §7" + CoinAPI.GetTokens(user) , "§5View Profile Website §7» §dClick §7«"));
    skull.setItemMeta(meta);
    
    ProfieINV.setItem(22, skull);
    ProfieINV.setItem(20, FAAServer);
    ProfieINV.setItem(4, SGServer);
    ProfieINV.setItem(24, WhatShouldWeAdd);
    ProfieINV.setItem(39, WhatShouldWeAdd);
    ProfieINV.setItem(41, WhatShouldWeAdd);
    
    user.openInventory(ProfieINV);
}

public static void OpenClanPageGUI(Player user){
	int ClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	
	if(ClanID != 0){
	Inventory ClanINV = Bukkit.createInventory(null , 9 , ChatColor.RED + "" + ChatColor.BOLD + ClanAPI.getClanName(ClanID) + "'s Clan");
	 for (int i = 0; i < 9; i++)
    {  
		 ClanINV.setItem(i, OfflineServerItem());
    }
		ItemStack Members = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta meta = (SkullMeta) Members.getItemMeta();
	    meta.setOwner(user.getName());
	    meta.setDisplayName("§8»§9" + "Clan Members" + "§8«");
	    meta.setLore(Arrays.asList(""));
	    Members.setItemMeta(meta);
	    
		ItemStack ClanStats = new ItemStack(Material.BOW, 1);
	    ItemMeta ClanStatsmeta = ClanStats.getItemMeta();
	    ClanStatsmeta.setDisplayName("§8»§9" + "Clan Stats" + "§8«");
	    ClanStatsmeta.setLore(Arrays.asList("View Stats on your Clan"));
	    ClanStats.setItemMeta(ClanStatsmeta);
	    
	    
	    ClanINV.setItem(0, Members);
	    ClanINV.setItem(1, ClanStats);
	  user.openInventory(ClanINV);
	  return;
	}
	user.closeInventory();
	user.sendMessage(ChatColor.RED + "You are not in a clan.");
}

public static void OpenClanMembersGUI(Player user){
	int ClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	int count = 0;
	List<String> ClanMembers = ClanAPI.getClanMembers(ClanID);
	Inventory ClanMemberINV = Bukkit.createInventory(null , 27 , ChatColor.RED + "" + ChatColor.BOLD + ClanAPI.getClanName(ClanID) + " Members");
	for (int i = 0; i < 27; i++)
    {  
		ClanMemberINV.setItem(i, OfflineServerItem());
    }
	
	for(String PlayerName: ClanMembers){
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta meta = (SkullMeta) skull.getItemMeta();
	    meta.setOwner(PlayerName);
	    meta.setDisplayName("§8»§9" + PlayerName + "§8«");
	    meta.setLore(Arrays.asList(ChatColor.GOLD + "Rank: " + ClanAPI.ClanRankNumberToName(ClanAPI.getClanRankFromUsername(PlayerName))));
	    skull.setItemMeta(meta);
	    ClanMemberINV.setItem(count, skull);
		count++;
	}
	
	user.openInventory(ClanMemberINV);
	
}


public static void OpenCountryGUI(Player user){
	Inventory CountryINV = Bukkit.createInventory(null , 27 , ChatColor.RED + "" + ChatColor.BOLD + "Country Selector");
	for (int i = 0; i < 27; i++)
    {  
		CountryINV.setItem(i, OfflineServerItem());
    }
	
	ItemStack NAserver = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 4);
	ItemMeta NAserverMeta = NAserver.getItemMeta();
	NAserverMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "North America Server");
	NAserverMeta.setLore(Arrays.asList(ChatColor.GOLD + info.techwizmatt.ServerCore.Main.UsServerIP));
	NAserver.setItemMeta(NAserverMeta);
	
	CountryINV.setItem(0, NAserver);
	
	ItemStack EUserver = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 14);
	ItemMeta EUserverMeta = EUserver.getItemMeta();
	EUserverMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "European Union Server");
	EUserverMeta.setLore(Arrays.asList(ChatColor.GOLD + info.techwizmatt.ServerCore.Main.EuServerIP));
	EUserver.setItemMeta(EUserverMeta);
	
	CountryINV.setItem(1, EUserver);
	
	user.openInventory(CountryINV);
	
}


public static void OpenSurvivalGamesStats(Player user){
	Inventory SGSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "SG Stats");
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, BGItem());
    }
	
	Map<String, String> stats = SG.getStats(user);
	
	if(stats.get("PLAYED") == null){
		user.closeInventory();
		user.sendMessage(ChatColor.RED + "There are no Stats for you on Survival Games.");
		return;
	}
	
	ItemStack WoodSword = new ItemStack(Material.WOOD_SWORD);  // Losses
	ItemMeta WoodSwordMeta = WoodSword.getItemMeta();
	WoodSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "LOSSES");
	WoodSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("LOSSES")));
	WoodSword.setItemMeta(WoodSwordMeta);
	
	ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
	ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
	GoldSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WINS");
	GoldSwordMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + stats.get("WINS")));
	GoldSword.setItemMeta(GoldSwordMeta);
	
	ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
	ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
	StoneSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DEATHS");
	StoneSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("DEATHS")));
	StoneSword.setItemMeta(StoneSwordMeta);
	
	ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
	ItemMeta IronSwordMeta = IronSword.getItemMeta();
	IronSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILLS");
	IronSwordMeta.setLore(Arrays.asList("~~~~~",ChatColor.GOLD + stats.get("KILLS")));
	IronSword.setItemMeta(IronSwordMeta);
	
	ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // Win rate
	ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
	DiamondSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WINRATE");
	DiamondSwordMeta.setLore(Arrays.asList("~~~~~~~",ChatColor.GOLD + stats.get("WINRATE")));
	DiamondSword.setItemMeta(DiamondSwordMeta);
	
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
	ItemMeta SkullMeta = Skull.getItemMeta();
	SkullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " " +user.getName());
	int NameCount = user.getName().length();
	String Underline = "";
	for (int j = 0; j <= NameCount; j++) { 
		Underline = Underline + "~";
	}
	SkullMeta.setLore(Arrays.asList(Underline));
	Skull.setItemMeta(SkullMeta);
	
	ItemStack Star = new ItemStack(Material.NETHER_STAR); // KDR
	ItemMeta StarMeta = Star.getItemMeta();
	StarMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " KILL DEATH RATIO");
	StarMeta.setLore(Arrays.asList("~~~~~~~~~~~~~~~~",ChatColor.GOLD + stats.get("KDR")));
	Star.setItemMeta(StarMeta);
	
	ItemStack SunFlower = new ItemStack(Material.YELLOW_FLOWER); // Points
	ItemMeta SunFlowerMeta = SunFlower.getItemMeta();
	SunFlowerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "POINTS");
	SunFlowerMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("POINTS")));
	SunFlower.setItemMeta(SunFlowerMeta);
	
	ItemStack Redstone = new ItemStack(Material.REDSTONE); // Damage Taken
	ItemMeta RedstoneMeta = Redstone.getItemMeta();
	RedstoneMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE TAKEN");
	RedstoneMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DMGTAKEN")));
	Redstone.setItemMeta(RedstoneMeta);
	
	ItemStack RedDye = new ItemStack(Material.RED_ROSE); //Played
	ItemMeta RedDyeMeta = RedDye.getItemMeta();
	RedDyeMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "GAMES PLAYED");
	RedDyeMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("PLAYED")));
	RedDye.setItemMeta(RedDyeMeta);
	 
	ItemStack Arrow = new ItemStack(Material.ARROW); // Damage Given
	ItemMeta ArrowMeta = Arrow.getItemMeta();
	ArrowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE GIVEN");
	ArrowMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DMGGIVEN")));
	Arrow.setItemMeta(ArrowMeta);

	ItemStack Bow = new ItemStack(Material.BOW); // Bow Accuracys
	ItemMeta BowMeta = Bow.getItemMeta();
	BowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "BOW ACCURACY");
	BowMeta.setLore(Arrays.asList("~~~~~~~~~~~~~",ChatColor.GOLD + stats.get("BOWACCURACY")));
	Bow.setItemMeta(BowMeta);
	
	ItemStack Torch = new ItemStack(Material.REDSTONE_TORCH_ON);  //Close the menu
	ItemMeta TorchMeta = Torch.getItemMeta();
	TorchMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "CLOSE STATS");
	TorchMeta.setLore(Arrays.asList(""));
	Torch.setItemMeta(TorchMeta);
	
	ItemStack Sign = new ItemStack(Material.SIGN); // Rank
	ItemMeta SignMeta = Sign.getItemMeta();
	SignMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "RANK");
	SignMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + stats.get("RANK")));
	Sign.setItemMeta(SignMeta);
	
	ItemStack WhitePane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 0);
	ItemMeta WhitePaneMeta = WhitePane.getItemMeta();
	WhitePaneMeta.setDisplayName(ChatColor.MAGIC + "12345678");
	WhitePane.setItemMeta(WhitePaneMeta);
	
	for (int i = 9; i < 45; i++)
    {  
		SGSTATSINV.setItem(i, WhitePane);
    }
	
	
	SGSTATSINV.setItem(9, WoodSword);
	SGSTATSINV.setItem(11, GoldSword);
	SGSTATSINV.setItem(13, StoneSword);
	SGSTATSINV.setItem(15, IronSword);
	SGSTATSINV.setItem(17, DiamondSword);
	SGSTATSINV.setItem(22, Skull);
	SGSTATSINV.setItem(31, Star);
	SGSTATSINV.setItem(36, SunFlower);
	SGSTATSINV.setItem(38, Redstone);
	SGSTATSINV.setItem(40, RedDye);
	SGSTATSINV.setItem(42, Arrow);
	SGSTATSINV.setItem(44, Bow);
	SGSTATSINV.setItem(45, Torch);
	SGSTATSINV.setItem(53, Sign);

	
	user.openInventory(SGSTATSINV);
}
	public static void OpenUHCGamesStats(Player user){
		Inventory UHCGAMESSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "UHC Games Stats");

		ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);

		ItemMeta Meta = Grey.getItemMeta();
		Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
		Purple.setItemMeta(Meta);
		Grey.setItemMeta(Meta);



		LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();

		Colors.add(Grey);
		Colors.add(Purple);


		ItemStack[] ColorsArr = new ItemStack[Colors.size()];
		ColorsArr = Colors.toArray(ColorsArr);



		for(int i=0; i<45; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
			UHCGAMESSTATSINV.setItem(i, ColorsArr[randomNum]);
		}
		Map<String, String> stats = UHCGames.getStats(user);

		if(stats.get("PLAYED") == null){
			user.closeInventory();
			user.sendMessage(ChatColor.RED + "You haven't played any UHC Games, get into the games using the compass!");
			return;
		}

		ItemStack WoodSword = new ItemStack(Material.WOOD_SWORD);  // Losses
		ItemMeta WoodSwordMeta = WoodSword.getItemMeta();
		WoodSwordMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Lost");
		WoodSwordMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("LOSSES")));
		WoodSword.setItemMeta(WoodSwordMeta);

		ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
		ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
		GoldSwordMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Wins");
		GoldSwordMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("WINS")));
		GoldSword.setItemMeta(GoldSwordMeta);

		ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
		ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
		StoneSwordMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Deaths");
		StoneSwordMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("DEATHS")));
		StoneSword.setItemMeta(StoneSwordMeta);

		ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
		ItemMeta IronSwordMeta = IronSword.getItemMeta();
		IronSwordMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Kills");
		IronSwordMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("KILLS")));
		IronSword.setItemMeta(IronSwordMeta);

		ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // Win rate
		ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
		DiamondSwordMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Win Ratio");
		DiamondSwordMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("WINRATE")));
		DiamondSword.setItemMeta(DiamondSwordMeta);

		ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
		ItemMeta SkullMeta = Skull.getItemMeta();
		SkullMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + " " +user.getName());
		int NameCount = user.getName().length();
		String Underline = "";
		for (int j = 0; j <= NameCount; j++) {
			Underline = Underline + "~";
		}
		SkullMeta.setLore(Arrays.asList(Underline));
		Skull.setItemMeta(SkullMeta);

		ItemStack Star = new ItemStack(Material.NETHER_STAR); // KDR
		ItemMeta StarMeta = Star.getItemMeta();
		StarMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + " Kill/Death Ratio");
		StarMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("KDR")));
		Star.setItemMeta(StarMeta);

		ItemStack SunFlower = new ItemStack(Material.YELLOW_FLOWER); // Points
		ItemMeta SunFlowerMeta = SunFlower.getItemMeta();
		SunFlowerMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "POINTS");
		SunFlowerMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("POINTS")));
		SunFlower.setItemMeta(SunFlowerMeta);

		ItemStack Redstone = new ItemStack(Material.REDSTONE); // Damage Taken
		ItemMeta RedstoneMeta = Redstone.getItemMeta();
		RedstoneMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Damage Taken");
		RedstoneMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("DMGTAKEN")));
		Redstone.setItemMeta(RedstoneMeta);

		ItemStack RedDye = new ItemStack(Material.RED_ROSE); //Played
		ItemMeta RedDyeMeta = RedDye.getItemMeta();
		RedDyeMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Played");
		RedDyeMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("PLAYED")));
		RedDye.setItemMeta(RedDyeMeta);

		ItemStack Arrow = new ItemStack(Material.ARROW); // Damage Given
		ItemMeta ArrowMeta = Arrow.getItemMeta();
		ArrowMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Damage Given");
		ArrowMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("DMGGIVEN")));
		Arrow.setItemMeta(ArrowMeta);

		ItemStack Bow = new ItemStack(Material.BOW); // Bow Accuracys
		ItemMeta BowMeta = Bow.getItemMeta();
		BowMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Bow Accuracy");
		BowMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("BOWACCURACY")));
		Bow.setItemMeta(BowMeta);

		ItemStack Torch = new ItemStack(Material.REDSTONE_TORCH_ON);  //Close the menu
		ItemMeta TorchMeta = Torch.getItemMeta();
		TorchMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close");
		TorchMeta.setLore(Arrays.asList(""));
		Torch.setItemMeta(TorchMeta);

		ItemStack Sign = new ItemStack(Material.SIGN); // Rank
		ItemMeta SignMeta = Sign.getItemMeta();
		SignMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Rank");
		SignMeta.setLore(Arrays.asList("♦ "+ChatColor.DARK_PURPLE + stats.get("RANK")));
		Sign.setItemMeta(SignMeta);




		UHCGAMESSTATSINV.setItem(9, WoodSword);
		UHCGAMESSTATSINV.setItem(11, GoldSword);
		UHCGAMESSTATSINV.setItem(13, StoneSword);
		UHCGAMESSTATSINV.setItem(15, IronSword);
		UHCGAMESSTATSINV.setItem(17, DiamondSword);
		UHCGAMESSTATSINV.setItem(22, Skull);
		UHCGAMESSTATSINV.setItem(31, Star);
		UHCGAMESSTATSINV.setItem(36, SunFlower);
		UHCGAMESSTATSINV.setItem(38, Redstone);
		UHCGAMESSTATSINV.setItem(40, RedDye);
		UHCGAMESSTATSINV.setItem(42, Arrow);
		UHCGAMESSTATSINV.setItem(44, Bow);
		UHCGAMESSTATSINV.setItem(45, Torch);
		UHCGAMESSTATSINV.setItem(53, Sign);


		user.openInventory(UHCGAMESSTATSINV);
	}
public static void OpenClanSurvivalGamesStats(Player user){
	
	int ClanID = ClanAPI.getClanId(user.getUniqueId().toString());
	if(ClanID == 0){
		user.closeInventory();
		user.sendMessage(ChatColor.RED + "You are not in a clan.");
		return;
	}
	
	String ClanName = ClanAPI.getClanName(ClanID);
	
	Inventory SGSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "CSG Stats");
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, BGItem());
    }
	
	Map<String, String> stats = CSG.getStats(ClanID);
	
	if(stats.get("PLAYED") == null){
		user.closeInventory();
		user.sendMessage(ChatColor.RED + "There are no Stats for your Clan.");
		return;
	}
	
	ItemStack WoodSword = new ItemStack(Material.WOOD_SWORD);  // Losses
	ItemMeta WoodSwordMeta = WoodSword.getItemMeta();
	WoodSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "LOSSES");
	WoodSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("LOSSES")));
	WoodSword.setItemMeta(WoodSwordMeta);
	
	ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
	ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
	GoldSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WINS");
	GoldSwordMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + stats.get("WINS")));
	GoldSword.setItemMeta(GoldSwordMeta);
	
	ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
	ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
	StoneSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DEATHS");
	StoneSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("DEATHS")));
	StoneSword.setItemMeta(StoneSwordMeta);
	
	ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
	ItemMeta IronSwordMeta = IronSword.getItemMeta();
	IronSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILLS");
	IronSwordMeta.setLore(Arrays.asList("~~~~~",ChatColor.GOLD + stats.get("KILLS")));
	IronSword.setItemMeta(IronSwordMeta);
	
	ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // Win rate
	ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
	DiamondSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WINRATE");
	DiamondSwordMeta.setLore(Arrays.asList("~~~~~~~",ChatColor.GOLD + stats.get("WINRATE")));
	DiamondSword.setItemMeta(DiamondSwordMeta);
	
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
	ItemMeta SkullMeta = Skull.getItemMeta();
	SkullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " " + ClanName);
	int NameCount = ClanName.length();
	String Underline = "";
	for (int j = 0; j <= NameCount; j++) { 
		Underline = Underline + "~";
	}
	SkullMeta.setLore(Arrays.asList(Underline));
	Skull.setItemMeta(SkullMeta);
	
	ItemStack Star = new ItemStack(Material.NETHER_STAR); // KDR
	ItemMeta StarMeta = Star.getItemMeta();
	StarMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " KILL DEATH RATIO");
	StarMeta.setLore(Arrays.asList("~~~~~~~~~~~~~~~~",ChatColor.GOLD + stats.get("KDR")));
	Star.setItemMeta(StarMeta);
	
	ItemStack SunFlower = new ItemStack(Material.YELLOW_FLOWER); // Points
	ItemMeta SunFlowerMeta = SunFlower.getItemMeta();
	SunFlowerMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "POINTS");
	SunFlowerMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("POINTS")));
	SunFlower.setItemMeta(SunFlowerMeta);
	
	ItemStack Redstone = new ItemStack(Material.REDSTONE); // Damage Taken
	ItemMeta RedstoneMeta = Redstone.getItemMeta();
	RedstoneMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE TAKEN");
	RedstoneMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DMGTAKEN")));
	Redstone.setItemMeta(RedstoneMeta);
	
	ItemStack RedDye = new ItemStack(Material.RED_ROSE); //Played
	ItemMeta RedDyeMeta = RedDye.getItemMeta();
	RedDyeMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "GAMES PLAYED");
	RedDyeMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("PLAYED")));
	RedDye.setItemMeta(RedDyeMeta);
	 
	ItemStack Arrow = new ItemStack(Material.ARROW); // Damage Given
	ItemMeta ArrowMeta = Arrow.getItemMeta();
	ArrowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE GIVEN");
	ArrowMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DMGGIVEN")));
	Arrow.setItemMeta(ArrowMeta);

	ItemStack Bow = new ItemStack(Material.BOW); // Bow Accuracys
	ItemMeta BowMeta = Bow.getItemMeta();
	BowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "BOW ACCURACY");
	BowMeta.setLore(Arrays.asList("~~~~~~~~~~~~~",ChatColor.GOLD + stats.get("BOWACCURACY")));
	Bow.setItemMeta(BowMeta);
	
	ItemStack Torch = new ItemStack(Material.REDSTONE_TORCH_ON);  //Close the menu
	ItemMeta TorchMeta = Torch.getItemMeta();
	TorchMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "CLOSE STATS");
	TorchMeta.setLore(Arrays.asList(""));
	Torch.setItemMeta(TorchMeta);
	
	ItemStack Sign = new ItemStack(Material.SIGN); // Rank
	ItemMeta SignMeta = Sign.getItemMeta();
	SignMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "RANK");
	SignMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + stats.get("RANK")));
	Sign.setItemMeta(SignMeta);
	
	ItemStack WhitePane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 0);
	ItemMeta WhitePaneMeta = WhitePane.getItemMeta();
	WhitePaneMeta.setDisplayName(ChatColor.MAGIC + "12345678");
	WhitePane.setItemMeta(WhitePaneMeta);
	
	for (int i = 9; i < 45; i++)
    {  
		SGSTATSINV.setItem(i, WhitePane);
    }
	
	
	SGSTATSINV.setItem(9, WoodSword);
	SGSTATSINV.setItem(11, GoldSword);
	SGSTATSINV.setItem(13, StoneSword);
	SGSTATSINV.setItem(15, IronSword);
	SGSTATSINV.setItem(17, DiamondSword);
	SGSTATSINV.setItem(22, Skull);
	SGSTATSINV.setItem(31, Star);
	SGSTATSINV.setItem(36, SunFlower);
	SGSTATSINV.setItem(38, Redstone);
	SGSTATSINV.setItem(40, RedDye);
	SGSTATSINV.setItem(42, Arrow);
	SGSTATSINV.setItem(44, Bow);
	SGSTATSINV.setItem(45, Torch);
	SGSTATSINV.setItem(53, Sign);

	
	user.openInventory(SGSTATSINV);
}

public static void OpenFFAStats(Player user){
	Inventory SGSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "FFA Stats");

	ItemStack Grey = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
	ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);

	ItemMeta Meta = Grey.getItemMeta();
	Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
	Purple.setItemMeta(Meta);
	Grey.setItemMeta(Meta);



	LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();

	Colors.add(Grey);
	Colors.add(Purple);


	ItemStack[] ColorsArr = new ItemStack[Colors.size()];
	ColorsArr = Colors.toArray(ColorsArr);



	for(int i=0; i<45; i++){
		int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
		SGSTATSINV.setItem(i, ColorsArr[randomNum]);
	}
	
	Map<String, String> stats = FFA.getStats(user);

	if(stats.get("ID") == null){
		user.sendMessage(ChatColor.RED + "You currenty have no stats for FFA");
		user.closeInventory();
		return;
	}
	
	ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
	ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
	GoldSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE GIVEN");
	GoldSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DAMAGEGIVEN")));
	GoldSword.setItemMeta(GoldSwordMeta);
	
	ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
	ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
	StoneSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DEATHS");
	StoneSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("DEATHS")));
	StoneSword.setItemMeta(StoneSwordMeta);
	
	ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
	ItemMeta IronSwordMeta = IronSword.getItemMeta();
	IronSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILLS");
	IronSwordMeta.setLore(Arrays.asList("~~~~~",ChatColor.GOLD + stats.get("KILLS")));
	IronSword.setItemMeta(IronSwordMeta);
	
	ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // KDR
	ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
	DiamondSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILL DEATH RATIO");
	DiamondSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~",ChatColor.GOLD + stats.get("KDR")));
	DiamondSword.setItemMeta(DiamondSwordMeta);
	
	ItemStack Nugget = new ItemStack(Material.GOLD_NUGGET); // KDR
	ItemMeta NuggetMeta = Nugget.getItemMeta();
	NuggetMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ELO");
	NuggetMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + "" + FFA.getElo(user)));
	Nugget.setItemMeta(NuggetMeta);
	
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
	ItemMeta SkullMeta = Skull.getItemMeta();
	SkullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " " + user.getName());
	int NameCount = user.getName().length();
	String Underline = "";
	for (int j = 0; j <= NameCount; j++) { 
		Underline = Underline + "~";
	}
	SkullMeta.setLore(Arrays.asList(Underline));
	Skull.setItemMeta(SkullMeta);
	
	SGSTATSINV.setItem(10, IronSword);
	SGSTATSINV.setItem(16, DiamondSword);
	SGSTATSINV.setItem(22, Nugget);
	SGSTATSINV.setItem(31, Skull);
	SGSTATSINV.setItem(37, GoldSword);
	SGSTATSINV.setItem(43, StoneSword);
	
	user.openInventory(SGSTATSINV);
}

public static void OpenFFAUHCStats(Player user){
	Inventory SGSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "FFA UHC Stats");
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, BGItem());
    }
	
	ItemStack WhitePane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 0);
	ItemMeta WhitePaneMeta = WhitePane.getItemMeta();
	WhitePaneMeta.setDisplayName(ChatColor.MAGIC + "12345678");
	WhitePane.setItemMeta(WhitePaneMeta);
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, WhitePane);
    }
	
	Map<String, String> stats = FFA.getStats(user);

	if(stats.get("ID") == null){
		user.sendMessage(ChatColor.RED + "You currenty have no stats for FFA");
		user.closeInventory();
		return;
	}
	
	ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
	ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
	GoldSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DAMAGE GIVEN");
	GoldSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("DAMAGEGIVEN")));
	GoldSword.setItemMeta(GoldSwordMeta);
	
	ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
	ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
	StoneSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DEATHS");
	StoneSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("DEATHS")));
	StoneSword.setItemMeta(StoneSwordMeta);
	
	ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
	ItemMeta IronSwordMeta = IronSword.getItemMeta();
	IronSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILLS");
	IronSwordMeta.setLore(Arrays.asList("~~~~~",ChatColor.GOLD + stats.get("KILLS")));
	IronSword.setItemMeta(IronSwordMeta);
	
	ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // KDR
	ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
	DiamondSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILL DEATH RATIO");
	DiamondSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~",ChatColor.GOLD + stats.get("KDR")));
	DiamondSword.setItemMeta(DiamondSwordMeta);
	
	ItemStack Nugget = new ItemStack(Material.GOLD_NUGGET); // KDR
	ItemMeta NuggetMeta = Nugget.getItemMeta();
	NuggetMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ELO");
	NuggetMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + "" + FFA.getElo(user)));
	Nugget.setItemMeta(NuggetMeta);
	
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
	ItemMeta SkullMeta = Skull.getItemMeta();
	SkullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " " + user.getName());
	int NameCount = user.getName().length();
	String Underline = "";
	for (int j = 0; j <= NameCount; j++) { 
		Underline = Underline + "~";
	}
	SkullMeta.setLore(Arrays.asList(Underline));
	Skull.setItemMeta(SkullMeta);
	
	SGSTATSINV.setItem(10, IronSword);
	SGSTATSINV.setItem(16, DiamondSword);
	SGSTATSINV.setItem(22, Nugget);
	SGSTATSINV.setItem(31, Skull);
	SGSTATSINV.setItem(37, GoldSword);
	SGSTATSINV.setItem(43, StoneSword);
	
	user.openInventory(SGSTATSINV);
}

public static void OpenUHCMStats(Player user){
	user.closeInventory();
	user.sendMessage(ChatColor.RED + "UHCM Stats have not been enabled at the time. Sorry for the Inconvience.");
}

public static void OpenUHCStats(Player user){
	Inventory SGSTATSINV = Bukkit.createInventory(null , 54 , ChatColor.RED + "" + ChatColor.BOLD + "UHC Stats");
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, BGItem());
    }
	
	ItemStack WhitePane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 0);
	ItemMeta WhitePaneMeta = WhitePane.getItemMeta();
	WhitePaneMeta.setDisplayName(ChatColor.MAGIC + "12345678");
	WhitePane.setItemMeta(WhitePaneMeta);
	
	for (int i = 0; i < 54; i++)
    {  
		SGSTATSINV.setItem(i, WhitePane);
    }
	
	Map<String, String> stats = UHC.getStats(user);

	if(stats.get("WINS") == null){
		user.sendMessage(ChatColor.RED + "You currenty have no stats for UHC");
		user.closeInventory();
		return;
	}
	
	ItemStack GoldSword = new ItemStack(Material.GOLD_SWORD); //Wins
	ItemMeta GoldSwordMeta = GoldSword.getItemMeta();
	GoldSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "WINS");
	GoldSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~~",ChatColor.GOLD + stats.get("WINS")));
	GoldSword.setItemMeta(GoldSwordMeta);
	
	ItemStack StoneSword = new ItemStack(Material.STONE_SWORD);  //Deaths
	ItemMeta StoneSwordMeta = StoneSword.getItemMeta();
	StoneSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "DEATHS");
	StoneSwordMeta.setLore(Arrays.asList("~~~~~~",ChatColor.GOLD + stats.get("DEATHS")));
	StoneSword.setItemMeta(StoneSwordMeta);
	
	ItemStack IronSword = new ItemStack(Material.IRON_SWORD); //Kills
	ItemMeta IronSwordMeta = IronSword.getItemMeta();
	IronSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILLS");
	IronSwordMeta.setLore(Arrays.asList("~~~~~",ChatColor.GOLD + stats.get("KILLS")));
	IronSword.setItemMeta(IronSwordMeta);
	
	ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD); // KDR
	ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
	DiamondSwordMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILL DEATH RATIO");
	DiamondSwordMeta.setLore(Arrays.asList("~~~~~~~~~~~",ChatColor.GOLD + stats.get("KDR")));
	DiamondSword.setItemMeta(DiamondSwordMeta);
	
	ItemStack Nugget = new ItemStack(Material.GOLD_NUGGET); // KDR
	ItemMeta NuggetMeta = Nugget.getItemMeta();
	NuggetMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "KILL STREAK");
	NuggetMeta.setLore(Arrays.asList("~~~~",ChatColor.GOLD + "" + stats.get("KILLSTREAK")));
	Nugget.setItemMeta(NuggetMeta);
	
	ItemStack Skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 0); // Name
	ItemMeta SkullMeta = Skull.getItemMeta();
	SkullMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " " + user.getName());
	int NameCount = user.getName().length();
	String Underline = "";
	for (int j = 0; j <= NameCount; j++) { 
		Underline = Underline + "~";
	}
	SkullMeta.setLore(Arrays.asList(Underline));
	Skull.setItemMeta(SkullMeta);
	
	SGSTATSINV.setItem(10, IronSword);
	SGSTATSINV.setItem(16, DiamondSword);
	SGSTATSINV.setItem(22, Nugget);
	SGSTATSINV.setItem(31, Skull);
	SGSTATSINV.setItem(37, GoldSword);
	SGSTATSINV.setItem(43, StoneSword);
	
	user.openInventory(SGSTATSINV);
}

@SuppressWarnings("deprecation")
public static Inventory DropCrateInv(){
	Inventory inv = RandomBackground(45, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Drop " + ChatColor.WHITE + "" + ChatColor.BOLD + "Crate");
	
	
	ItemStack Hook = new ItemStack(Material.TRIPWIRE_HOOK);
	ItemMeta HookMeta = Hook.getItemMeta();
	HookMeta.setDisplayName(ChatColor.GOLD + "Result");
	Hook.setItemMeta(HookMeta);
	
	inv.setItem(13, Hook);
	
	ItemStack SpawnEgg = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta SpawnEggMeta = SpawnEgg.getItemMeta();
	SpawnEggMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD +  "+5 Coins");
	SpawnEgg.setItemMeta(SpawnEggMeta);
	
	ItemStack SnowBall = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta SnowBallMeta = SnowBall.getItemMeta();
	SnowBallMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+10 Coins");
	SnowBallMeta.setLore(Arrays.asList(ChatColor.GOLD + ""));
	SnowBall.setItemMeta(SnowBallMeta);
	
	ItemStack NetherStar = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta NetherStarMeta = NetherStar.getItemMeta();
	NetherStarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+125 Coins");
	NetherStar.setItemMeta(NetherStarMeta);
	
	ItemStack EyeOfEnder = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta EyeOfEnderMeta = EyeOfEnder.getItemMeta();
	EyeOfEnderMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+25 Coins");
	EyeOfEnder.setItemMeta(EyeOfEnderMeta);
	
	ItemStack FireWorkStar = new ItemStack(Material.FIREWORK_CHARGE);
	ItemMeta FireWorkStarMeta = FireWorkStar.getItemMeta();
	FireWorkStarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+2 Crate Keys");
	FireWorkStar.setItemMeta(FireWorkStarMeta);
	Random rand = new Random();
	if(rand.nextInt(100) <= 10){
		FireWorkStarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+5 Crate Keys");

	}


	ItemStack Bone = new ItemStack(Material.BONE);
	ItemMeta BoneMeta = Bone.getItemMeta();
	BoneMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You get nothing, unless you call opening a chest a reward?!");
	Bone.setItemMeta(BoneMeta);
	
	ItemStack Map = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta MapMeta = Map.getItemMeta();
	MapMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+5 Coins");
	Map.setItemMeta(MapMeta);
	
	ItemStack Disk = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta DiskMeta = Disk.getItemMeta();
	DiskMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "+25 Coins");
	Disk.setItemMeta(DiskMeta);
	
	ItemStack GoldNugget = new ItemStack(Material.GOLD_NUGGET);
	ItemMeta GoldNuggetMeta = GoldNugget.getItemMeta();
	GoldNuggetMeta.setDisplayName(ChatColor.DARK_PURPLE + "" +  ChatColor.BOLD + "+75 Coins");
	GoldNugget.setItemMeta(GoldNuggetMeta);

	if(rand.nextInt(100)<=80){
		inv.setItem(18, randomItem(Material.ARROW,"Helix","§7Particle Effect"));
	}else{
		inv.setItem(18, GoldNugget);

	}
	if (rand.nextInt(100)<=80) {
		inv.setItem(19, randomItem(Material.STONE, "Stone Hat", "§7Hat"));
	}else{
		inv.setItem(19, GoldNugget);

	}
	if(rand.nextInt(100)<= 50) {
		inv.setItem(20, randomItem(Material.BOW, "Tnt Bow", "§7Gadget"));
	}else{
		inv.setItem(20, GoldNugget);

	}
	if(rand.nextInt(100)<= 1) {
		inv.setItem(21, randomItem(Material.BOW, "Flight", "§7Miscellaneous"));
	}else {
		inv.setItem(21, GoldNugget);

	}
	 if(rand.nextInt(100)<= 80) {
		 inv.setItem(22, randomItem(Material.LAVA_BUCKET, "Lava Hat", "§7Hat"));
	 }else {
		 inv.setItem(22, GoldNugget);

	 }
	 if(rand.nextInt(100)<= 5){
		inv.setItem(23,randomItem(Material.WATER_BUCKET,"Water Hat","§7Hat"));
	}else{
		 inv.setItem(23, GoldNugget);

	 }

	inv.setItem(24, Map);
	inv.setItem(25, Disk);
	inv.setItem(26, GoldNugget);
	
	
	
	
	
	
	
	
	return inv;
	
}

public static ItemStack randomItem(Material m, String name, String lore){
	ItemStack material = new ItemStack(m);
	ItemMeta Meta = material.getItemMeta();
	Meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + name);
	Meta.setLore(Arrays.asList(lore));
	material.setItemMeta(Meta);
	return material;
}

public static Inventory RandomBackground(int Size, String inventoryName){
	
	ItemStack Lime = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
	ItemStack LightBlue = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 3);
	ItemStack Purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10);
	ItemStack Red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
	ItemStack Pink = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 6);
	ItemStack Yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4);
	
	ItemMeta Meta = Lime.getItemMeta();
	Meta.setDisplayName(ChatColor.MAGIC + "RANDOM");
	Lime.setItemMeta(Meta);
	LightBlue.setItemMeta(Meta);
	Purple.setItemMeta(Meta);
	Red.setItemMeta(Meta);
	Pink.setItemMeta(Meta);
	Yellow.setItemMeta(Meta);
	

	LinkedList<ItemStack> Colors = new LinkedList<ItemStack>();
	
	Colors.add(Yellow);
	Colors.add(Red);
	Colors.add(Purple);
	Colors.add(LightBlue);
	Colors.add(Lime);
	Colors.add(Pink);
	
	ItemStack[] ColorsArr = new ItemStack[Colors.size()];
	ColorsArr = Colors.toArray(ColorsArr);
	
	Inventory inv = Bukkit.createInventory(null, Size, inventoryName);
	
		for(int i=0; i<Size; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(0, Colors.size());
			inv.setItem(i, ColorsArr[randomNum]);
		}
	return inv;
}

public static HashMap<Player, Integer> ItemRotateTaskIds = new HashMap<Player, Integer>();

public static void RotateItems(Player user, int RotationTimes, long delay, boolean SlowDownRequest){
	
 
	
	int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.CorePluginCall, new RotateItemsDropCrate(user, RotationTimes, SlowDownRequest), delay, delay);
	
	ItemRotateTaskIds.put(user, task);

	
}

	
	
	


}
