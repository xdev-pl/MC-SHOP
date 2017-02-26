package pro.lvlup.mcshop.managers;

import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class Config extends ConfigManager {
	
	public static List<String> SERVICE$LINE$EMPTY, SERVICE$BOUGHT$SUCCESSFULLY,
	SERVICE$BAD$CODE, SERVICE$CANT$CONNECT$API, SERVICE$WARN$MESSAGE, SERVICE$ISNT$ON$GROUND;
	public static int USER$ID;
	public static String SERVICE$DESCRIPTION;
	public static boolean UPDATER$STATUS;
	
	static {
		USER$ID = 0;
		UPDATER$STATUS = true;
		SERVICE$DESCRIPTION = "Usluga zakupiona poprzez itemshop na serwerze.";
        SERVICE$LINE$EMPTY = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&7Pierwsza linia w tabliczce jest pusta",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
        SERVICE$BOUGHT$SUCCESSFULLY = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&7Gracz &6{PLAYER} &7Zakupil Usluge &6{SERVICE} &7Dziekujemy!",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
        SERVICE$CANT$CONNECT$API = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&cPlugin nie moze sie teraz polaczyc z API lvlup.pro.",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
        SERVICE$WARN$MESSAGE = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&c&lWAZNE INFORMACJE:", "&7Link do reklamacji: &6http://www.dotpay.pl/reklamacje",
        		"&7Link do Regulaminu: &6http://www.dotpay.pl/regulamin-serwisow-sms-premium/",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
        SERVICE$ISNT$ON$GROUND = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&cAby zakupic usluge, musisz byc na ziemi.",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
        SERVICE$BAD$CODE = Arrays.asList("&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------",
        		"&cPodany kod jest nieprawidlowy, spróbuj ponownie.",
        		"&8&m-------------------&7[&6ITEMSHOP&7]&8&m-------------------");
    }
	public Config(JavaPlugin plugin) {
        super(plugin, "config.yml", "ustawienia.");
    }
}
