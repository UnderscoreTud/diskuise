package me.tud.diskuise.elements.entities.armorstand.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Armor Stand Disguise - Is Marker")
@Description("Checks whether an armor stand disguise is a marker")
@Examples("if player's disguise is a marker")
@Since("INSERT VERSION")
@RequiredPlugins("LibsDisguises")
public class CondIsMarker extends WatcherPropertyCondition<ArmorStandWatcher> {

    static {
        register(CondIsMarker.class, "[a] marker");
    }

    @Override
    protected boolean check(ArmorStandWatcher armorStandWatcher) {
        return armorStandWatcher.isSmall();
    }

    @Override
    protected String getPropertyName() {
        return "marker";
    }
}
