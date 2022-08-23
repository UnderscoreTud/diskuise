package me.tud.diskuise.elements.entities.pillager.condition;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PillagerWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Pillager Disguise - Is Aiming Bow")
@Description("Check whether a pillager disguise is aiming a bow")
@Examples("if player's disguise is aiming a bow")
@Since("0.3.2")
@RequiredPlugins("LibsDisguises")
public class CondIsAimingBow extends WatcherPropertyCondition<PillagerWatcher> {

    static {
        register(CondIsAimingBow.class, "aiming [a] bow");
    }

    @Override
    protected boolean check(PillagerWatcher pillagerWatcher) {
        return pillagerWatcher.isAimingBow();
    }

    @Override
    protected String getPropertyName() {
        return "aiming bow";
    }
}
