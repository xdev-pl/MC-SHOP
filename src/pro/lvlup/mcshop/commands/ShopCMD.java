package pro.lvlup.mcshop.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pro.lvlup.mcshop.Main;
import pro.lvlup.mcshop.basic.Service;
import pro.lvlup.mcshop.basic.ServiceManager;
import pro.lvlup.mcshop.managers.Config;
import pro.lvlup.mcshop.managers.FilesManager;
import pro.lvlup.mcshop.utils.IOUtils;
import pro.lvlup.mcshop.utils.Utils;

public class ShopCMD implements CommandExecutor {
	
	private static List<ItemStack> itemStacks = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sklep")) {
			if(!(sender instanceof Player)){
				sender.sendMessage("Konsola nie moze kupowac nic sklepie.");
				return true;
			}
			Player p = (Player) sender;
			if(!p.isOnGround() && p.isFlying()){
				for(String s : Config.SERVICE$ISNT$ON$GROUND){
					s = Utils.replace(s, "&", "§");
					p.sendMessage(Utils.fixColor(s));
				}
				return true;
			}
			openMenu(p);
			for(String s : Config.SERVICE$WARN$MESSAGE){
				s = Utils.replace(s, "&", "§");
				p.sendMessage(Utils.fixColor(s));
			}
		}
		return false;
	}
	private void openMenu(Player p) {
		p.openInventory(fillInv(Bukkit.getServer().createInventory(p, 27, Utils.fixColor("&eZakup Uslugi")), p));
	}
	private Inventory fillInv(Inventory inv, Player p) {
		File f = new File(FilesManager.getPluginDirectory(), "uslugi.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);
		for(int i = 0; i < ServiceManager.getAllServices().length; i++) {
			Service service = ServiceManager.getAllServices()[i];
			Material mat = Material.getMaterial(y.getString("uslugi." + service.getName() + ".material").toUpperCase());
			ItemStack item = new ItemStack(mat);
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(Utils.fixColor(service.getDisplayName()));
			itemMeta.setLore(Arrays.asList(Utils.fixColor("&8» &7Usluga: &3" + service.getName()),
					Utils.fixColor("&8» &7Koszt: &3" + service.getCost()),
					Utils.fixColor("&8» &7Waznosc Uslugi: &3" + service.getDays()),
					Utils.fixColor("&8» &7Tresc Sms'a: &3" + service.getSmsText()),
					Utils.fixColor("&8» &7Numer Sms'a: &3" + service.getSmsNumber()),
					Utils.fixColor("&8» &7Kupiono dzisiaj razy:&3 " + service.getBoughtAmount())));
			item.setItemMeta(itemMeta);
			itemStacks.add(item);
			inv.setItem(i, item);
		}
		return inv;
	}
}