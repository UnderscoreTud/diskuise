package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
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
                "make [custom[ ]]name of [disguise[s]] %disguises% [(:not |not:in)]visible",
                "(show|not:hide) [custom[ ]]name of [disguise[s]] %disguises%",
                "(show|not:hide) [disguise[s]] %disguises%'[s] [custom[ ]]name");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean state) {
        if (flagWatcher instanceof PlayerWatcher playerWatcher)
            playerWatcher.setNameVisible(false);
        else flagWatcher.setCustomNameVisible(state);
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
