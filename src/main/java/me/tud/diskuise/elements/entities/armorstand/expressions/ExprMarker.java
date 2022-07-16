package me.tud.diskuise.elements.entities.armorstand.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Armor Stand Disguise - Marker")
@Description("Set or get whether an armor stand disguise is a marker")
@Examples("set marker of player's disguise to true")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class ExprMarker extends WatcherPropertyExpression<ArmorStandWatcher, Boolean> {

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
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, ArmorStandWatcher armorStandWatcher, Object[] delta, Changer.ChangeMode mode) {
        armorStandWatcher.setSmall((boolean) delta[0]);
    }
}
