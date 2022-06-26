package me.tud.diskuise.util.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;

public abstract class WatcherMakeEffect<T extends FlagWatcher> extends DisguiseMakeEffect {

    abstract protected void make(Event e, T t);

    @Override
    final protected void make(Event e, Disguise disguise) {}

    @Override
    @SuppressWarnings("unchecked")
    protected void execute(Event e) {
        for (Disguise disguise : getExpr().getArray(e))
            make(e, (T) disguise.getWatcher());
    }
}
