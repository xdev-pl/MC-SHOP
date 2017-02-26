package pro.lvlup.mcshop.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import pro.lvlup.mcshop.basic.ServiceManager;

public class PlayerKickList implements Listener{
	
	@EventHandler
	public void onKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		Location newSign = p.getLocation().add(0, 100, 0);
		Location fixnewSign = p.getLocation().add(0, 99, 0);
		if(newSign.getBlock().getType() != Material.AIR){
			newSign.getBlock().setType(Material.AIR);
			fixnewSign.getBlock().setType(Material.AIR);
			ServiceManager.service.remove(p);
		}
	}
}
