package me.tud.diskuise.elements.types;

import me.tud.diskuise.util.skript.ClassUtils;
import me.tud.diskuise.util.skript.EnumClassInfo;
import org.bukkit.entity.Axolotl;

public class TypAxolotlVariant {

    static {
        ClassUtils.registerClass(new EnumClassInfo<>(Axolotl.Variant.class, "axolotlvariant")
                .user("axolotl ?variants?")
                .name("Axolotl Variant")
                .description("Represents the variant of an axolotl")
                .since("0.2-beta3")
        );
    }
}
