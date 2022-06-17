package me.tud.diskuise.elements.types;

import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.EnumClassInfo;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;

public class TypNotifyBar {
    static {
        if (Classes.getClassInfoNoError("") == null) {
            Classes.registerClass(new EnumClassInfo<>(DisguiseConfig.NotifyBar.class, "notifybar")
                    .user("notify ?bars?")
                    .name("Notify Bar")
                    .description("Change the notify bar of a disguise")
                    .since("0.2-beta0")
            );
        }
    }
}
