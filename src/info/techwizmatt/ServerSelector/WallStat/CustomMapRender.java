package info.techwizmatt.ServerSelector.WallStat;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;


public class CustomMapRender extends MapRenderer{
	
	@SuppressWarnings("deprecation")
	@Override
	public void render(MapView view, MapCanvas canvas, Player user) {
		
//		if(info.techwizmatt.ServerCore.Main.HiddenPacketPlayers.contains(user)){
	
//		}else{
			canvas.drawImage(0, 0, Stat.Images.get((int)view.getId()));
//		}
		
	}
}