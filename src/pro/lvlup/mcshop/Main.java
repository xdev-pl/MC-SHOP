package pro.lvlup.mcshop;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pro.lvlup.mcshop.basic.Service;
import pro.lvlup.mcshop.basic.ServiceManager;
import pro.lvlup.mcshop.commands.ShopCMD;
import pro.lvlup.mcshop.listeners.*;
import pro.lvlup.mcshop.managers.Config;
import pro.lvlup.mcshop.managers.FilesManager;
import pro.lvlup.mcshop.managers.GuiManager;
import pro.lvlup.mcshop.utils.Metrics;
import pro.lvlup.mcshop.utils.Utils;

public class Main extends JavaPlugin {

	private static Main instance;
	private Config config;

	public void onEnable() {
		instance = this;
		FilesManager.checkFiles();
		config = new Config(this);
		loadAllServices();
		registerListeners();
		getCommand("sklep").setExecutor(new ShopCMD());
		Utils.infos();
		loadMetrics();
		
	}

	public void onDisable() {
		Utils.infos();
	}
	public static Main getInst() {
		return instance;
	}
	public static String getVersion() {
		return getInst().getDescription().getVersion();
	}

	private void loadAllServices() {
		File f = new File(FilesManager.getPluginDirectory(), "uslugi.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);
		for (String s : y.getConfigurationSection("uslugi").getKeys(false)) {
			Service service = new Service(s, y.getString("uslugi." + s + ".nazwaWys"),
					y.getString("uslugi." + s + ".tresc"), y.getInt("uslugi." + s + ".sms"),
					y.getString("uslugi." + s + ".cena"), y.getString("uslugi." + s + ".waznosc"),
					Material.getMaterial(y.getString("uslugi." + s + ".material")),
					y.getStringList("uslugi." + s + ".komendy"),
					y.getString("uslugi." + s + ".idUslugi"));
			service.setBoughtAmount(0);
			service.setLastBuyer(null);
			ServiceManager.addService(service);
			GuiManager.setupGui(s);
		}
	}

	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new SignChangeListener(), this);
		pm.registerEvents(new PlayerQuitList(), this);
		pm.registerEvents(new PlayerKickList(), this);
	}
	
	public void loadMetrics(){
		try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    } catch (IOException e) {
	        Utils.info("[Metrics] Wystapil blad podczas ladowania statystyk :(");
	    }
	}
}