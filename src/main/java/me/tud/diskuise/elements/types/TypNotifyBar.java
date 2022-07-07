package me.tud.diskuise.elements.types;

import me.libraryaddict.disguise.DisguiseConfig;
import me.tud.diskuise.util.skript.ClassUtils;
import me.tud.diskuise.util.skript.EnumClassInfo;

public class TypNotifyBar {
    static {
        ClassUtils.registerClass(new EnumClassInfo<>(DisguiseConfig.NotifyBar.class, "notifybar")
                .user("notify ?bars?")
                .name("Notify Bar")
                .description("Change the notify bar of a disguise")
                .since("0.2-beta0")
        );
    }
}
