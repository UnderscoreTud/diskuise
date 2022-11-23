package me.tud.diskuise.elements.entities.general.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Disguise - Glowing")
@Description("Set or get whether the disguise is glowing")
@Examples("set glowing of disguise {dis} to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprGlowing extends WatcherBooleanExpression<FlagWatcher> {

    static {
        register(ExprGlowing.class, "glow[ing]");
    }

    @Override
    protected Boolean convert(FlagWatcher flagWatcher) {
        return flagWatcher.isGlowing();
    }

    @Override
    protected String getPropertyName() {
        return "glowing";
    }

    @Override
    protected void change(Event e, FlagWatcher flagWatcher, boolean state) {
        flagWatcher.setGlowing(state);
    }
}
