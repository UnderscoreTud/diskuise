package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public abstract class WatcherBooleanExpression<T extends FlagWatcher> extends WatcherPropertyExpression<T, Boolean> {

    public static <T> void register(Class<? extends Expression<T>> c, Class<T> type, String property) {
        Skript.registerExpression(c, type, ExpressionType.PROPERTY, "[the] " + property + " [state] of [dis(g|k)uise[s]] %disguises%",
                "[dis(g|k)uise[s]] %disguises%'[s] " + property + " [state]");
    }

    @Override
    public final Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    protected final void change(Event e, T t, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Boolean bool = (Boolean) delta[0];
        if (bool == null) return;
        change(e, t, bool);
    }

    abstract protected void change(Event e, T t, boolean bool);
}
