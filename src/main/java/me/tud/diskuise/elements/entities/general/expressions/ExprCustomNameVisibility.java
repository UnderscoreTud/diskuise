package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Custom Name Visibility")
@Description("Set or get whether the custom name of a disguise is visible")
@Examples("set custom name visibility of disguise {dis} to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprCustomNameVisibility extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprCustomNameVisibility.class, Boolean.class, "[custom[ ]]name visibility");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isCustomNameVisible();
    }

    @Override
    protected String getPropertyName() {
        return "custom name visibility";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean bool) {
        flagWatcher.setCustomNameVisible(bool);
    }
}
