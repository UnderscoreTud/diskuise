package me.tud.diskuise.elements.entities.armorstand.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Armor Stand Disguise - Marker")
@Description("Set or get whether an armor stand disguise is a marker")
@Examples("set marker of player's disguise to true")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class ExprMarker extends WatcherBooleanExpression<ArmorStandWatcher> {

    static {
        register(ExprMarker.class, Boolean.class, "[is] marker");
    }

    @Override
    protected Boolean convert(ArmorStandWatcher armorStandWatcher) {
        return armorStandWatcher.isSmall();
    }

    @Override
    protected String getPropertyName() {
        return "marker";
    }

    @Override
    protected void change(Event e, ArmorStandWatcher armorStandWatcher, boolean bool) {
        armorStandWatcher.setSmall(bool);
    }
}
