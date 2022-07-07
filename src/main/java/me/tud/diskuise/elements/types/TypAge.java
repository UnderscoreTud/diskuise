package me.tud.diskuise.elements.types;

import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.util.skript.ClassUtils;
import me.tud.diskuise.util.skript.EnumClassInfo;

public class TypAge {
    static {
        ClassUtils.registerClass(new EnumClassInfo<>(Age.class, "age")
                .user("ages?")
                .name("Age")
                .description("The age of a disguise")
                .since("0.3")
        );
    }
}
