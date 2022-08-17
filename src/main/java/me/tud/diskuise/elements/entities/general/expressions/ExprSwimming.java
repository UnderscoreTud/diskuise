package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Swimming")
@Description("Set or get whether the disguise is swimming")
@Examples("set swimming of disguise {dis} to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprSwimming extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprSwimming.class, Boolean.class, "swim[ming]");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isSwimming();
    }

    @Override
    protected String getPropertyName() {
        return "swimming";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean bool) {
        flagWatcher.setSwimming(bool);
    }
}
