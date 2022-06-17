package me.tud.diskuise.util.skript;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public abstract class WatcherPropertyCondition<T extends FlagWatcher> extends DisguisePropertyCondition {

    @Override
    @SuppressWarnings("unchecked")
    public final boolean check(Disguise disguise) {
        return check((T) disguise.getWatcher());
    }

    abstract protected boolean check(T t);
}