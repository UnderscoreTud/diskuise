package me.tud.diskuise.elements.entities.creeper.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Creeper Disguise - Is Powered")
@Description("Check whether the creeper disguise is powered")
@Examples("if player's disguise is powered")
@Since("1.0")
@RequiredPlugins("LibsDisguises")
public class CondIsPowered extends WatcherPropertyCondition<CreeperWatcher> {

    static {
        register(CondIsPowered.class, "powered");
    }

    @Override
    protected boolean check(CreeperWatcher creeperWatcher) {
        return creeperWatcher.isPowered();
    }

    @Override
    protected String getPropertyName() {
        return "powered";
    }

}
