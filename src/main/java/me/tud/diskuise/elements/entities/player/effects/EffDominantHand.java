package me.tud.diskuise.elements.entities.player.effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;
import org.bukkit.inventory.MainHand;

@Name("Player Disguise - Make Dominant Hand")
@Description("Set the dominant hand of a player disguise")
@Examples("make player's disguise left handed")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class EffDominantHand extends WatcherMakeEffect<PlayerWatcher> {

    static {
        register(EffDominantHand.class, "(:right|:left) handed");
    }

    private String hand;

    @Override
    protected String getProperty() {
        return hand + " handed";
    }

    @Override
    protected void make(Event e, PlayerWatcher playerWatcher) {
        playerWatcher.setMainHand(MainHand.valueOf(hand.toUpperCase()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Disguise>) exprs[0]);
        hand = parseResult.tags.get(0);
        return true;
    }
}
