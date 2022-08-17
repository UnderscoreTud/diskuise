package me.tud.diskuise.elements.entities.bat.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Bat Disguise - Hanging")
@Description("Set or get whether the bat disguise is hanging upside down")
@Examples("set hanging upside down of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprHanging extends WatcherBooleanExpression<BatWatcher> {

    static {
        register(ExprHanging.class, Boolean.class, "hang[ing] [upside[( |-)]down]");
    }

    @Override
    protected Boolean convert(BatWatcher batWatcher) {
        return batWatcher.isHanging();
    }

    @Override
    protected String getPropertyName() {
        return "hanging";
    }

    @Override
    protected void change(Event e, BatWatcher batWatcher, boolean bool) {
        batWatcher.setHanging(bool);
    }
}
