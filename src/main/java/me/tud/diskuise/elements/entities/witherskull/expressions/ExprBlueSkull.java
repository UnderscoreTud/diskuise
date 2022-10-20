package me.tud.diskuise.elements.entities.witherskull.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.WitherSkullWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Wither Skull Disguise - Blue Skull")
@Description("Set or get whether the wither skull disguise is a blue skull")
@Examples("set blue skull of player's disguise to true")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprBlueSkull extends WatcherBooleanExpression<WitherSkullWatcher> {

    static {
        register(ExprBlueSkull.class, "blue skull");
    }

    @Override
    protected Boolean convert(WitherSkullWatcher witherSkullWatcher) {
        return witherSkullWatcher.isBlue();
    }

    @Override
    protected String getPropertyName() {
        return "blue skull";
    }

    @Override
    protected void change(Event e, WitherSkullWatcher witherSkullWatcher, boolean state) {
        witherSkullWatcher.setBlue(state);
    }
}
