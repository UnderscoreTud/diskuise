package me.tud.diskuise.util;

import me.tud.diskuise.Diskuise;

public class Util {

    public static void log(String message) {
        Diskuise.getInstance().getLogger().info(message);
    }

}
