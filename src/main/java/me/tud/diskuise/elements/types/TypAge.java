package me.tud.diskuise.elements.types;

import ch.njol.skript.registrations.Classes;
import me.libraryaddict.disguise.DisguiseConfig;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.util.skript.EnumClassInfo;
import org.bukkit.entity.Ageable;

public class TypAge {
    static {
        if (Classes.getClassInfoNoError("age") == null) {
            Classes.registerClass(new EnumClassInfo<>(Age.class, "age")
                    .user("ages?")
                    .name("Age")
                    .description("The age of a disguise")
                    .since("0.3")
            );
        }
    }
}
