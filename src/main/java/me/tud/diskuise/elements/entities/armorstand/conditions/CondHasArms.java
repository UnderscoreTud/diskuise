package me.tud.diskuise.elements.entities.armorstand.conditions;

import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

public class CondHasArms extends WatcherPropertyCondition<BetterArmorStandWatcher> {

    static {
        register(CondHasArms.class, PropertyType.HAVE, "arms");
    }

    @Override
    protected boolean check(BetterArmorStandWatcher armorStandWatcher) {
        return armorStandWatcher.isShowArms();
    }

    @Override
    protected String getPropertyName() {
        return "arms";
    }

}
