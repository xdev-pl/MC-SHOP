package pro.lvlup.mcshop.nms;

import com.google.common.reflect.Reflection;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pro.lvlup.mcshop.Main;
import pro.lvlup.mcshop.utils.Utils;

import java.util.logging.Level;

public class OpenSignEditor {

    public static void openSignEditor(Player player, Sign s){
        String version = Reflections.ver();
        if(version.startsWith("v1_7") || version.startsWith("v1_8_R1") || version.startsWith("v1_8_R2") || version.startsWith("v1_8_R3") || version.startsWith("v1_9_R1") || version.startsWith("v1_9_R2")){
            Object tileEntity = Reflections.getDeclaredField(s,  "sign");
            Reflections.setDeclaredField(tileEntity, "isEditable", true);
            Reflections.setDeclaredField(tileEntity, Reflections.ver().startsWith("v1_7") ? "k" : "h", Reflections.getHandle(player));
            Reflections.sendPacket(player,  Reflections.getPacket("OpenSignEditor", s.getX(), s.getY(), s.getZ()));
        }
        if(version.startsWith("v1_12") || version.startsWith("v1_11") || version.startsWith("v1_10")){
            Location loc = s.getLocation();
            BlockPosition pos = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());
            EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
            TileEntitySign tileEntitySign = (TileEntitySign) nmsPlayer.world.getTileEntity(pos);
            PlayerConnection conn = nmsPlayer.playerConnection;
            tileEntitySign.isEditable = true;
            tileEntitySign.a(nmsPlayer);
            conn.sendPacket(new PacketPlayOutOpenSignEditor(pos));
        }else{
            Utils.log(Level.SEVERE, "You're using a version that is not supported by the plugin, please user >1.7 version.");
            Utils.log(Level.SEVERE, "You're using a version that is not supported by the plugin, please user >1.7 version.");
            Utils.log(Level.SEVERE, "You're using a version that is not supported by the plugin, please user >1.7 version.");
            Bukkit.getPluginManager().disablePlugin(Main.getInst());
        }
    }

}
