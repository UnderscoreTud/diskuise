package me.tud.diskuise.elements.entities.player.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;
import org.bukkit.inventory.MainHand;

@Name("Player Disguise - Is Dominant Hand")
@Description("Checks the dominant hand of a player disguise")
@Examples("if player's disguise is right handed")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class CondIsDominantHand extends WatcherPropertyCondition<PlayerWatcher> {

    static {
        register(CondIsDominantHand.class, "(:right|:left) handed");
    }

    private String hand;

    @Override
    protected boolean check(PlayerWatcher playerWatcher) {
        return playerWatcher.getMainHand() == MainHand.valueOf(hand.toUpperCase());
    }

    @Override
    protected String getPropertyName() {
        return hand + " handed";
    }
}
