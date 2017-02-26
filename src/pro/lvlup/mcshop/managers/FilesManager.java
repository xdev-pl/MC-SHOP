package pro.lvlup.mcshop.managers;

import java.io.File;

import pro.lvlup.mcshop.Main;
import pro.lvlup.mcshop.utils.Utils;

public class FilesManager {
	
	public static File folder = new File("plugins/MC-SHOP");
	public static File servicesconfig = new File("plugins/MC-SHOP/uslugi.yml");
	public static File config = new File("plugins/MC-SHOP/config.yml");
	
	public static void checkFiles(){
		if(!folder.exists()){
			folder.mkdir();
		}
	    if(!servicesconfig.exists()){
	    	Utils.copy(Main.getInst().getResource("uslugi.yml"), servicesconfig);
	    }
	}
	public static File getPluginDirectory(){
		return folder;
	}
}
