package me.tud.diskuise.elements.entities.arrow.conditions;

import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.arrow.BetterTippedArrowWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Arrow Disguise - Is Critical")
@Description("Checks whether the arrow disguise is a critical arrow")
@Examples("if player's disguise is critical")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsCritical extends WatcherPropertyCondition<BetterTippedArrowWatcher> {

    static {
        register(CondIsCritical.class, "[a] critical [arrow]");
    }

    @Override
    protected boolean check(BetterTippedArrowWatcher tippedArrowWatcher) {
        return tippedArrowWatcher.isCritical();
    }

    @Override
    protected String getPropertyName() {
        return "a critical arrow";
    }
}
