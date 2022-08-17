package me.tud.diskuise.elements.entities.armorstand.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.ArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Armor Stand Disguise - Is Small")
@Description("Checks whether an armor stand disguise is small")
@Examples("if player's disguise is small")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class CondIsSmall extends WatcherPropertyCondition<ArmorStandWatcher> {

    static {
        register(CondIsSmall.class, "small");
    }

    @Override
    protected boolean check(ArmorStandWatcher armorStandWatcher) {
        return armorStandWatcher.isSmall();
    }

    @Override
    protected String getPropertyName() {
        return "small";
    }
}
