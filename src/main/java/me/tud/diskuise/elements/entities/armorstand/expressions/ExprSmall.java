package me.tud.diskuise.elements.entities.armorstand.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Armor Stand Disguise - Small")
@Description("Set or get whether an armor stand disguise is small")
@Examples("set small of player's disguise to true")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class ExprSmall extends WatcherPropertyExpression<ArmorStandWatcher, Boolean> {

    static {
        register(ExprSmall.class, Boolean.class, "[is] small");
    }

    @Override
    protected Boolean convert(ArmorStandWatcher armorStandWatcher) {
        return armorStandWatcher.isSmall();
    }

    @Override
    protected String getPropertyName() {
        return "small";
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
