package me.tud.diskuise.elements.entities.witherskull.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.WitherSkullWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Wither Skull Disguise - Is Blue Skull")
@Description("Checks whether the wither skull disguise is a blue skull")
@Examples("if player's disguise is a blue skull:")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class CondIsBlueSkull extends WatcherPropertyCondition<WitherSkullWatcher> {

    static {
        register(CondIsBlueSkull.class, "[a] blue skull");
    }

    @Override
    protected boolean check(WitherSkullWatcher witherSkullWatcher) {
        return witherSkullWatcher.isBlue();
    }

    @Override
    protected String getPropertyName() {
        return "blue skull";
    }
}
