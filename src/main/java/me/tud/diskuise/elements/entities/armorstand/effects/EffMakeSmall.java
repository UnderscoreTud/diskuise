package me.tud.diskuise.elements.entities.armorstand.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Armor Stand Disguise - Make Small")
@Description("Set whether an armor stand disguise is small")
@Examples("make player's disguise small")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class EffMakeSmall extends WatcherMakeEffect<ArmorStandWatcher> {

    static {
        register(EffMakeSmall.class, "[:not] small");
    }

    @Override
    protected String getProperty() {
        return "small";
    }

    @Override
    protected void make(Event e, ArmorStandWatcher armorStandWatcher, boolean state) {
        armorStandWatcher.setSmall(state);
    }

}
