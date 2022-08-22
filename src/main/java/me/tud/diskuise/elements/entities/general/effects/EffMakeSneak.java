package me.tud.diskuise.elements.entities.general.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Disguise - Make Sneak")
@Description("Sets whether the disguise is sneaking")
@Examples("make player's disguise sneak")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeSneak extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeSneak.class, "[not:(not |un)](sneak|crouch)");
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean bool) {
        flagWatcher.setSneaking(bool);
    }

    @Override
    protected String getProperty() {
        return "sneak";
    }
}
