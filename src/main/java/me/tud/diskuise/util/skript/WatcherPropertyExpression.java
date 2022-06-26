package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class WatcherPropertyExpression<F extends FlagWatcher, T> extends DisguisePropertyExpression<T> {

    public static <T> void register(final Class<? extends Expression<T>> c, final Class<T> type, final String property) {
        Skript.registerExpression(c, type, ExpressionType.PROPERTY, "[the] " + property + " of [dis(g|k)uise] %disguises%",
                "[dis(g|k)uise] %disguises%'[s] " + property);
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable T convert(Disguise disguise) {
        return convert((F) disguise.getWatcher());
    }

    abstract protected T convert(F f);

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        for (Disguise disguise : getExpr().getArray(e)) change(e, (F) disguise.getWatcher(), delta, mode);
    }

    protected void change(Event e, F f, Object[] delta, Changer.ChangeMode mode) {
        super.change(e, delta, mode);
    }
}
