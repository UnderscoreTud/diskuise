package me.tud.diskuise.util.skript;

import ch.njol.skript.classes.Changer.ChangeMode;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public abstract class WatcherPropertyExpression<F extends FlagWatcher, T> extends DisguisePropertyExpression<T> {

    @Override
    @SuppressWarnings("unchecked")
    public final @Nullable T convert(Disguise disguise) {
        try {
            return convert((F) disguise.getWatcher());
        }
        catch (ClassCastException ignore) {}
        return null;
    }

    abstract protected T convert(F f);

    @Override
    @SuppressWarnings("unchecked")
    public final void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
        for (Disguise disguise : getExpr().getArray(e)) {
            try {
                change(e, (F) disguise.getWatcher(), delta, mode);
                DisguiseUtils.update(disguise);
            }
            catch (ClassCastException ignore) {}
        }
    }

    protected void change(Event e, F f, @Nullable Object[] delta, ChangeMode mode) {
        super.change(e, delta, mode);
    }
}
