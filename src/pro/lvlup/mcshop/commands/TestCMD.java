package pro.lvlup.mcshop.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pro.lvlup.mcshop.Main;

public class TestCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("pomoc")){
            for(String x : Main.getInst().getConfig().getStringList("pomoc")){
                commandSender.sendMessage(x);
            }
            return true;
        }
        return false;
    }
}
