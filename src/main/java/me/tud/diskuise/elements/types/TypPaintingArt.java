package me.tud.diskuise.elements.types;

import me.tud.diskuise.util.skript.ClassUtils;
import me.tud.diskuise.util.skript.EnumClassInfo;
import org.bukkit.Art;

public class TypPaintingArt {

    static {
        ClassUtils.registerClass(new EnumClassInfo<>(Art.class, "paintingart")
                .user("(painting ?)?arts?")
                .name("Painting Art")
                .description("Represents the art of a painting")
                .since("0.2-beta2")
        );
    }
}
