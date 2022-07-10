package me.tud.diskuise.elements.entities.livingentities.conditions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Living Disguise - Is Spinning")
@Description("Checks whether the disguise is spinning")
@Examples("if player's disguise is spinning:")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class CondIsSpinning extends WatcherPropertyCondition<LivingWatcher> {

    static {
        register(CondIsSpinning.class, "spinning");
    }

    @Override
    protected boolean check(LivingWatcher livingWatcher) {
        return livingWatcher.isSpinning();
    }

    @Override
    protected String getPropertyName() {
        return "spinning";
    }
}
