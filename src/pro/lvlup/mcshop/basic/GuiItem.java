package pro.lvlup.mcshop.basic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class GuiItem {

	private ItemStack i;
	private Service serviceName;
	
	public GuiItem(Material m, Service serviceName) {
		this.i = new ItemStack(m);
		this.serviceName = serviceName;
	}
	public void setDisplayName(String name) {
		ItemMeta im = this.i.getItemMeta();
		im.setDisplayName((name));
		this.i.setItemMeta(im);
	}
	public ItemStack getItem() {
		return this.i;
	}
	public Service getItemService(){
		return this.serviceName;
	}
}
