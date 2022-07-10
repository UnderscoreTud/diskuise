package me.tud.diskuise.elements.entities.witherskull.conditions;

import me.libraryaddict.disguise.disguisetypes.watchers.WitherSkullWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

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
