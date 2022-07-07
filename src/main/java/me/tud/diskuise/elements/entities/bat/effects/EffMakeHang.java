package me.tud.diskuise.elements.entities.bat.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.BatWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Bat Disguise - Make Hang")
@Description("Sets whether the bat disguise is hanging upside down")
@Examples("make player's disguise hang upside down")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class EffMakeHang extends WatcherMakeEffect<BatWatcher> {

    static {
        register(EffMakeHang.class, "[:not] hang[ing] [upside[( |-)]down]");
    }

    @Override
    protected String getProperty() {
        return "hang upside down";
    }

    @Override
    protected void make(Event e, BatWatcher batWatcher) {
        batWatcher.setHanging(!isNegated());
    }
}
