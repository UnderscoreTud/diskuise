package me.tud.diskuise.elements.entities.axolotl.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Axolotl Disguise - Playing Dead")
@Description("Set or get whether the axolotl disguise is playing dead")
@Examples("set playing dead of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprPlayingDead extends WatcherBooleanExpression<AxolotlWatcher> {

    static {
        register(ExprPlayingDead.class, Boolean.class, "play[ing] dead");
    }

    @Override
    protected Boolean convert(AxolotlWatcher axolotlWatcher) {
        return axolotlWatcher.isPlayingDead();
    }

    @Override
    protected String getPropertyName() {
        return "playing dead";
    }

    @Override
    protected void change(Event e, AxolotlWatcher axolotlWatcher, boolean bool) {
        axolotlWatcher.setPlayingDead(bool);
    }
}
