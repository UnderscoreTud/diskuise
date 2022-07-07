package me.tud.diskuise.util.skript;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.event.Event;

public abstract class WatcherMakeEffect<T extends FlagWatcher> extends DisguiseMakeEffect {

    abstract protected void make(Event e, T t);

    @Override
    final protected void make(Event e, Disguise disguise) {}

    @Override
    @SuppressWarnings("unchecked")
    protected void execute(Event e) {
        for (Disguise disguise : getExpr().getArray(e))
            try {
                make(e, (T) disguise.getWatcher());
                DisguiseUtils.update(disguise);
            }
            catch (ClassCastException ignore) {}
    }
}
