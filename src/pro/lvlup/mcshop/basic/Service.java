package pro.lvlup.mcshop.basic;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import pro.lvlup.mcshop.callback.CodeVerifier;
import pro.lvlup.mcshop.managers.Config;

public class Service {
	
	private int smsNumber;
	private String name;
	private String displayName;
	private String cost;
	private String days;
	private Material mat;
	private String smsText;
	private List<String> commands;
	private String serviceID;
	private int boughtAmount;
	private String lastBuyer;
	
	public Service(String name, String displayname,String text, int smsNumber, String cost, String days, Material mat, List<String> commands, String serviceID) {
		this.displayName = displayname;
		this.smsNumber = smsNumber;
		this.name = name;
		this.cost = cost;
		this.days = days;
		this.mat = mat;
		this.smsText = text;
		this.commands = commands;
		this.serviceID = serviceID;
	}
	public String getSmsText() {
		return smsText;
	}
	public String getServiceID(){
		return serviceID;
	}
	public List<String> getCommandToRun(){
		return commands;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Material getMat() {
		return mat;
	}

	public Service() {
	}

	public int getSmsNumber(){
		return smsNumber;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public String getDays() {
		return days;
	}
	public int getBoughtAmount() {
		return boughtAmount;
	}
	public void setBoughtAmount(int boughtAmount) {
		this.boughtAmount = boughtAmount;
	}
	
	public void checkCode(String code, Player p, SignChangeEvent e){
		new CodeVerifier(code, this, p, e);
		
	}
	public void verifyCode(int i, Player p, SignChangeEvent e){
		if (i == 1) {
			for (String m : getCommandToRun()) {
				m = StringUtils.replace(m, "{PLAYER}", p.getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), m);
			}
			for (String s : Config.SERVICE$BOUGHT$SUCCESSFULLY) {
				s = StringUtils.replace(s, "&", "§");
				s = StringUtils.replace(s, "{SERVICE}", getName().toUpperCase());
				s = StringUtils.replace(s, "{PLAYER}", e.getPlayer().getName());
				Bukkit.broadcastMessage(s);
			}
			setBoughtAmount(getBoughtAmount() +1);
			return;
		}
		if (i == 0) {
			for (String s : Config.SERVICE$BAD$CODE) {
				s = StringUtils.replace(s, "&", "§");
				p.sendMessage(s);
			}
			
			return;
		}
		if (i == 2) {
			for (String s : Config.SERVICE$CANT$CONNECT$API) {
				s = StringUtils.replace(s, "&", "§");
				p.sendMessage(s);
			}
		}
	}
	public String getLastBuyer() {
		return lastBuyer;
	}
	public void setLastBuyer(String lastBuyer) {
		this.lastBuyer = lastBuyer;
	}
}
