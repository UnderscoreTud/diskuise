package me.tud.diskuise.elements.entities.axolotl.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Axolotl Disguise - Make Play Dead")
@Description("Sets whether the axolotl disguise is playing dead")
@Examples("make player's disguise play dead")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakePlayDead extends WatcherMakeEffect<AxolotlWatcher> {

    static {
        register(EffMakePlayDead.class, "[:not] play dead");
    }

    @Override
    protected String getProperty() {
        return "play dead";
    }

    @Override
    protected void make(Event e, AxolotlWatcher axolotlWatcher, boolean state) {
        axolotlWatcher.setPlayingDead(state);
    }
}
