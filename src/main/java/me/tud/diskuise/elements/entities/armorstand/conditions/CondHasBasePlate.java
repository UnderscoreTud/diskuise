package me.tud.diskuise.elements.entities.armorstand.conditions;

import ch.njol.skript.doc.*;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Armor Stand Disguise - Has Base Plate")
@Description("Checks whether an armor stand disguise has its base plate shown")
@Examples("if player's disguise has a base plate")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class CondHasBasePlate extends WatcherPropertyCondition<BetterArmorStandWatcher> {

    static {
        register(CondHasBasePlate.class, PropertyType.HAVE, "[a] base[ ]plate");
    }

    @Override
    protected boolean check(BetterArmorStandWatcher armorStandWatcher) {
        return !armorStandWatcher.isNoBasePlate();
    }

    @Override
    protected String getPropertyName() {
        return "base plate";
    }

}
