package info.techwizmatt.ServerSelector;

/**
 * Created by TJ on 12/28/2017.
 */
import com.mojang.authlib.GameProfile;
import io.netty.channel.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CustomPacket implements Listener {


    /**
     * Inject our custom ChannelDuplexHandler when the player joins the server
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        create(event.getPlayer());
    }

    /**
     * Remove our custom ChannelDuplexHandler explicitly, NOTE: If they're quitting
     * the server this data is cleared anyway.
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        remove(event.getPlayer());
    }

    /**
     * Submits a request to the pipeline to remove our ChannelDuplexHandler
     */
    private void remove(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    /**
     * Creates a custom ChannelDuplexHandler for a player, and this serves
     * to intercept packets before the packet handler
     */
    private void create(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            /**
             * Reads packets
             */
            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
//                getLogger().info("READ>> " + packet.toString());
                super.channelRead(context, packet);
            }

            /**
             * Writes packets
             */
            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise channelPromise) throws Exception {

                if (packet instanceof PacketPlayOutPlayerInfo) {
                    /**
                     * We can use reflection to access certain variables as they are
                     * encapsulated
                     */


                }
                super.write(context, packet, channelPromise);
            }
        };

        /**
         * Take the player's networking channel and add our custom DuplexHandler
         */
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();

        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

    public void changeName(Player p, String newName){
        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl == p) continue;
            //REMOVES THE PLAYER
            ((CraftPlayer)pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)p).getHandle()));
            //CHANGES THE PLAYER'S GAME PROFILE
            GameProfile gp = ((CraftPlayer)p).getProfile();
            try {
                Field nameField = GameProfile.class.getDeclaredField("name");
                nameField.setAccessible(true);

                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(nameField, nameField.getModifiers() & ~Modifier.FINAL);

                nameField.set(gp, newName);
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                throw new IllegalStateException(ex);
            }
            //ADDS THE PLAYER
            ((CraftPlayer)pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, ((CraftPlayer)p).getHandle()));
            ((CraftPlayer)pl).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(p.getEntityId()));
            ((CraftPlayer)pl).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle()));
        }
    }

    public static final void reloadSkinForSelf(Player player) {
        final EntityPlayer ep = ((CraftPlayer) player).getHandle();
        final PacketPlayOutPlayerInfo removeInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ep);
        final PacketPlayOutPlayerInfo addInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, ep);
        final Location loc = player.getLocation().clone();
        ep.playerConnection.sendPacket(removeInfo);
        ep.playerConnection.sendPacket(addInfo);
        player.teleport(player.getLocation());
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(loc);
                ep.playerConnection.sendPacket(new PacketPlayOutRespawn(ep.dimension, ep.getWorld().getDifficulty(), ep.getWorld().getWorldData().getType(), ep.playerInteractManager.getGameMode()));
                player.updateInventory();
            }
        }.runTaskLater(Main.CorePluginCall, 2L);
    }
}




