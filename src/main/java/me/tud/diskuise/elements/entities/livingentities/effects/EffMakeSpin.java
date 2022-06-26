package me.tud.diskuise.elements.entities.livingentities.effects;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Living Disguise - Make Spin")
@Description("Sets whether the disguise is spinning")
@Examples("make player's disguise spin")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeSpin extends WatcherMakeEffect<LivingWatcher> {

    static {
        register(EffMakeSpin.class, "[:not] spin");
    }

    @Override
    protected void make(Event e, LivingWatcher livingWatcher) {
        livingWatcher.setSpinning(!isNegated());
        DisguiseUtils.update(livingWatcher.getDisguise());
    }

    @Override
    protected String getProperty() {
        return "spin";
    }
}
