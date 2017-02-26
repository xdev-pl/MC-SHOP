package pro.lvlup.mcshop.listeners;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import pro.lvlup.mcshop.Main;
import pro.lvlup.mcshop.basic.Service;
import pro.lvlup.mcshop.basic.ServiceManager;
import pro.lvlup.mcshop.managers.Config;

public class SignChangeListener implements Listener {

	@EventHandler
	public void onSignDone(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.BEDROCK)
				&& !(e.getBlock().getLocation().getY() < 25)) {
			String magia = ServiceManager.service.get(p.getPlayer());
			Service service = ServiceManager.getService(magia);
			String kod = e.getLine(0);
			if (e.getLine(0).isEmpty()) {
				for (String s : Config.SERVICE$LINE$EMPTY) {
					s = StringUtils.replace(s, "&", "§");
					p.sendMessage(s);
				}
				e.getBlock().setType(Material.AIR);
				e.getBlock().getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
				return;
			}
			service.checkCode(kod, p, e);
			ServiceManager.service.remove(p.getPlayer());
			e.getBlock().setType(Material.AIR);
			e.getBlock().getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
		}
	}
}
