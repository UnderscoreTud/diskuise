package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Custom Name Visible")
@Description("Sets whether the custom name of a disguise is visible")
@Examples("make custom name of player's disguise not visible")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeCustomNameVisible extends WatcherMakeEffect<FlagWatcher> {

    static {
        Skript.registerEffect(EffMakeCustomNameVisible.class,
                "make [custom[ ]]name of [dis(g|k)uise[s]] %disguises% [(:not |not:in)]visible");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher) {
        flagWatcher.setCustomNameVisible(!isNegated());
        DisguiseUtils.update(flagWatcher.getDisguise());
    }

    @Override
    protected String getProperty() {
        return null;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make custom name of " + getExpr().toString(e, debug) + (isNegated() ? " not " : " ") + "visible";
    }
}
