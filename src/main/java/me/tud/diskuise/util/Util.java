package me.tud.diskuise.util;

import me.tud.diskuise.Diskuise;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class Util {

    public static void log(Object object) {
        if (object != null && object.getClass().isArray()) {
            Diskuise.getInstance().getLogger().info(colored(Arrays.toString((Object[]) object)));
            return;
        }
        Diskuise.getInstance().getLogger().info(colored(object + ""));
    }

    public static String colored(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
