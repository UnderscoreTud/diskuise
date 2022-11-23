package me.tud.diskuise.elements.entities.witherskull.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.WitherSkullWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Wither Skull Disguise - Make Blue Skull")
@Description("Set whether the wither skull disguise is a blue skull")
@Examples("make player's disguise a blue skull")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class EffMakeBlueSkull extends WatcherMakeEffect<WitherSkullWatcher> {

    static {
        register(EffMakeBlueSkull.class, "[a] ([:not] blue|not:black) skull");
    }

    @Override
    protected String getProperty() {
        return "blue skull";
    }

    @Override
    protected void make(Event e, WitherSkullWatcher witherSkullWatcher, boolean state) {
        witherSkullWatcher.setBlue(state);
    }
}
