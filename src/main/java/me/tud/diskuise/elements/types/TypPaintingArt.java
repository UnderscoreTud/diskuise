package me.tud.diskuise.elements.types;

import ch.njol.skript.classes.registry.RegistryClassInfo;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Art;
import org.bukkit.Registry;

public class TypPaintingArt {

    static {
		// Skript 2.12.1 doesn't have proper support for Paper RegistryKey at the moment. Safe to use deprecated .ART
		Classes.registerClass(new RegistryClassInfo<>(Art.class, Registry.ART, "paintingart", "paintingarts")
			.user("(painting ?)?arts?")
			.name("Painting Art")
			.description("Represents the art of a painting")
			.since("0.2-beta2")
		);
    }
}
