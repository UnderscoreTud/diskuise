package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public abstract class WatcherBooleanExpression<T extends FlagWatcher> extends WatcherPropertyExpression<T, Boolean> {

    public static void register(Class<? extends Expression<Boolean>> c, String property) {
        Skript.registerExpression(c, Boolean.class, ExpressionType.PROPERTY, "[the] " + property + " [state] of [disguise[s]] %disguises%",
                "[disguise[s]] %disguises%'[s] " + property + " [state]");
    }

    @Override
    public final Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    protected final void change(Event e, T t, @Nullable Object[] delta, ChangeMode mode) {
        Boolean state = (Boolean) delta[0];
        if (state == null) return;
        change(e, t, state);
    }

    protected void change(Event e, T t, boolean state) {
        super.change(e, new Object[]{state}, ChangeMode.SET);
    }
}
