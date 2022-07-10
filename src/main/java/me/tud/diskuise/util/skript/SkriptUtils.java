package me.tud.diskuise.util.skript;

import ch.njol.skript.util.SkriptColor;
import org.bukkit.ChatColor;

public class SkriptUtils {

    public static SkriptColor toSkriptColor(ChatColor chatColor) {
        for (SkriptColor skriptColor : SkriptColor.values())
            if (chatColor.equals(skriptColor.asChatColor())) return skriptColor;
        return null;
    }

}
