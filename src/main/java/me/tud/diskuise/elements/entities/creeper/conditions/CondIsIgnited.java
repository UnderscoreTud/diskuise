package me.tud.diskuise.elements.entities.creeper.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;
import me.libraryaddict.disguise.disguisetypes.watchers.BeeWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Creeper Disguise - Is Ignited")
@Description("Check whether the creeper disguise is ignited")
@Examples("if player's disguise is ignited")
@Since("1.0")
@RequiredPlugins("LibsDisguises")
public class CondIsIgnited extends WatcherPropertyCondition<CreeperWatcher> {

    static {
        register(CondIsIgnited.class, "ignited");
    }

    @Override
    protected boolean check(CreeperWatcher creeperWatcher) {
        return creeperWatcher.isIgnited();
    }

    @Override
    protected String getPropertyName() {
        return "ignited";
    }

}
