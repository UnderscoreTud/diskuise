package me.tud.diskuise.elements.entities.player.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Player Disguise - Is Displayed In Tab")
@Description("Checks whether a player disguise is displayed in tab")
@Examples("if player's disguise is displayed in tab")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class CondIsDisplayedInTab extends WatcherPropertyCondition<PlayerWatcher> {

    static {
        register(CondIsDisplayedInTab.class, "display(ed|ing) in tab");
    }

    @Override
    protected boolean check(PlayerWatcher playerWatcher) {
        return playerWatcher.isDisplayedInTab();
    }

    @Override
    protected String getPropertyName() {
        return "displayed in tab";
    }
}
