package pro.lvlup.mcshop.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import pro.lvlup.mcshop.basic.GuiItem;
import pro.lvlup.mcshop.basic.Service;
import pro.lvlup.mcshop.basic.ServiceManager;
import pro.lvlup.mcshop.utils.Utils;

public class GuiManager {
	
	public static List<GuiItem> items = new ArrayList<GuiItem>();

	public static GuiItem getGuiItem(ItemStack is) {
		for (GuiItem i : items) {
			String displayname = Utils.fixColor(i.getItem().getItemMeta().getDisplayName());
			if(displayname.equals(is.getItemMeta().getDisplayName())){
				return i;
			}
		}
		return null;
	}
	public static void setupGui(String name){
		File f = new File(FilesManager.getPluginDirectory(), "uslugi.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);
		Service s = ServiceManager.getService(y.getString("uslugi."+name+".idUslugi"));
		GuiItem gItem = new GuiItem(Material.getMaterial(y.getString("uslugi." + name + ".material").toUpperCase()), s);
		gItem.setDisplayName(y.getString("uslugi." + name + ".nazwaWys"));
		items.add(gItem);
	}
}
