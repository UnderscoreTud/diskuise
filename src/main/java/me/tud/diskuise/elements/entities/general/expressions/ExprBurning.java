package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Burning")
@Description("Set or get whether the disguise is burning")
@Examples("set burning of disguise {dis} to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprBurning extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprBurning.class, "(burn[ing]|on fire)");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isBurning();
    }

    @Override
    protected String getPropertyName() {
        return "burning";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setBurning(state);
    }
}
