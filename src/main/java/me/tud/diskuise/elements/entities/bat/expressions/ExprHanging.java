package me.tud.diskuise.elements.entities.bat.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Bat Disguise - Hanging")
@Description("Set or get whether the bat disguise is hanging upside down")
@Examples("set hanging upside down of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprHanging extends WatcherPropertyExpression<BatWatcher, Boolean> {

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
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, BatWatcher batWatcher, Object[] delta, Changer.ChangeMode mode) {
        batWatcher.setHanging((boolean) delta[0]);
    }
}
