package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Sleeping")
@Description("Set or get whether the disguise is sleeping")
@Examples("set sleeping of disguise {dis} to true")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprSleeping extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprSleeping.class, Boolean.class, "sleeping");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isSleeping();
    }

    @Override
    protected String getPropertyName() {
        return "sleeping";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean bool) {
        flagWatcher.setSleeping(bool);
    }
}
