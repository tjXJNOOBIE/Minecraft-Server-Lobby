package info.techwizmatt.ServerSelector;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;


public class books {

	 public static void openBook(ItemStack book, Player p) {

	        int slot = p.getInventory().getHeldItemSlot();
	        ItemStack old = p.getInventory().getItem(slot);
	        p.getInventory().setItem(slot, book);

	        //Open Book
	        
	        

	        p.getInventory().setItem(slot, old);
	    }
	
	
}
