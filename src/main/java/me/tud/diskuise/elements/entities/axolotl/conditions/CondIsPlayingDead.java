package me.tud.diskuise.elements.entities.axolotl.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Axolotl Disguise - Is Playing Dead")
@Description("Checks whether the axolotl disguise is playing dead")
@Examples("if player's disguise is playing dead:")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsPlayingDead extends WatcherPropertyCondition<AxolotlWatcher> {

    static {
        register(CondIsPlayingDead.class, "playing dead");
    }

    @Override
    protected boolean check(AxolotlWatcher axolotlWatcher) {
        return axolotlWatcher.isPlayingDead();
    }

    @Override
    protected String getPropertyName() {
        return "playing dead";
    }
}
