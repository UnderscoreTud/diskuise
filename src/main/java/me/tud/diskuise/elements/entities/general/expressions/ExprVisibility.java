package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Visibility")
@Description("Set or get whether the disguise is visible")
@Examples("set visibility of disguise {dis} to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprVisibility extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprVisibility.class, "visibility");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return !flagWatcher.isInvisible();
    }

    @Override
    protected String getPropertyName() {
        return "visibility";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setInvisible(!state);
    }
}
