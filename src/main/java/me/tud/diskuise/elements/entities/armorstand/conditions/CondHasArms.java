package me.tud.diskuise.elements.entities.armorstand.conditions;

import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Armor Stand Disguise - Has Arms")
@Description("Checks whether an armor stand disguise has its arms shown")
@Examples("if player's disguise has arms")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
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
