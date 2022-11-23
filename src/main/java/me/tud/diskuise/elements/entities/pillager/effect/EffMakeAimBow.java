package me.tud.diskuise.elements.entities.pillager.effect;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PillagerWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Pillager Disguise - Make Aim Bow")
@Description("Set whether a pillager disguise is aiming a bow")
@Examples("make player's disguise aim a bow")
@Since("0.3.2")
@RequiredPlugins("LibsDisguises")
public class EffMakeAimBow extends WatcherMakeEffect<PillagerWatcher> {

    static {
        register(EffMakeAimBow.class, "[:not] aim [a] bow");
    }

    @Override
    protected String getProperty() {
        return "aiming a bow";
    }

    @Override
    protected void make(Event e, PillagerWatcher pillagerWatcher, boolean state) {
        pillagerWatcher.setAimingBow(state);
    }
}
