package info.techwizmatt.ServerSelector;

import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;


public class Utils implements CommandExecutor {



	public static String getRank(Player player) {
		if (Rank.getRankLevel(player)==8) {
			return ChatColor.RED + "Mod";
		}
		if (Rank.getRankLevel(player)==9) {
			return org.bukkit.ChatColor.DARK_RED + "SrMod";
		}
		if(Rank.getRankLevel(player)==10){
			return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Admin";
		}
		if(Rank.getRankLevel(player)==11) {
			return org.bukkit.ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Developer";
		}

		if (Rank.getRankLevel(player)==3) {
			return org.bukkit.ChatColor.DARK_PURPLE + "VIP";
		}
		if (Rank.getRankLevel(player)==7) {
			return ChatColor.LIGHT_PURPLE + "Sonder";
		}
		if(Rank.getRankLevel(player)==6){
			return  ChatColor.BLUE + "Premium";
		}
		if(Rank.getRankLevel(player)==5) {
			return ChatColor.GREEN +  "Sponsor";
		}
		if(Rank.getRankLevel(player)==1) {
			return ChatColor.DARK_GRAY +"Default";
		}

		return org.bukkit.ChatColor.AQUA +"Default";
	}

	public static ItemStack createItem(Material material, String displayname, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);

		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);


		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, String displayname) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		item.setItemMeta(meta);
		return item;
	}


	public static int getHealth(Player player) {
		return (int) StrictMath.ceil(damageable(player).getHealth());
	}
	public static Damageable damageable(Player player) {
		return player;
	}

	public static void shootfireWork(Location l,Player p, FireworkEffect.Type type, Color color, Color fadecolor, int power, boolean flicker, boolean trial){
		Firework f = (Firework) p.getWorld().spawn(l, Firework.class);

		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder()
				.flicker(flicker)
				.trail(trial)
				.with(type)
				.withColor(color)
				.withFade(fadecolor)
				.build());
		fm.setPower(power);
		f.setFireworkMeta(fm);
	}

//	public static ItemStack addGlow(ItemStack item){ - NMS Error
//		net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
//		NBTTagCompound tag = null;
//		if (!nmsStack.hasTag()) {
//			tag = new NBTTagCompound();
//			nmsStack.setTag(tag);
//		}
//		if (tag == null) tag = nmsStack.getTag();
//		NBTTagList ench = new NBTTagList();
//		tag.set("ench", ench);
//		nmsStack.setTag(tag);
//		return CraftItemStack.asCraftMirror(nmsStack);
//	}
	ArrayList<Player> moving = new ArrayList();

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		return false;
	}
}

