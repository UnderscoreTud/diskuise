package me.tud.diskuise.elements.entities.armorstand.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Armor Stand Disguise - Make Marker")
@Description("Set whether an armor stand disguise is a marker")
@Examples("make player's disguise a marker")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class EffMakeMarker extends WatcherMakeEffect<ArmorStandWatcher> {

    static {
        register(EffMakeMarker.class, "[:not] [a] marker");
    }

    @Override
    protected String getProperty() {
        return "marker";
    }

    @Override
    protected void make(Event e, ArmorStandWatcher armorStandWatcher, boolean state) {
        armorStandWatcher.setMarker(state);
    }

}
