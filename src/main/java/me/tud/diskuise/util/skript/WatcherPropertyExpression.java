package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.jetbrains.annotations.Nullable;

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
}
