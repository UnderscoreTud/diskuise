package me.tud.diskuise.elements.entities.player.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;


@Name("Player Disguise - Cape")
@Description("Set or get whether the cape of a player disguise is enabled")
@Examples("set cape of player's disguise to true")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class ExprCape extends WatcherBooleanExpression<PlayerWatcher> {

    static {
        register(ExprCape.class, "cape");
    }

    @Override
    protected Boolean convert(PlayerWatcher playerWatcher) {
        return playerWatcher.isCapeEnabled();
    }

    @Override
    protected String getPropertyName() {
        return "cape";
    }

    @Override
    protected void change(Event e, PlayerWatcher playerWatcher, boolean state) {
        playerWatcher.setCapeEnabled(state);
    }
}
