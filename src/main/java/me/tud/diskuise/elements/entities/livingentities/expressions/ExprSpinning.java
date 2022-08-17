package me.tud.diskuise.elements.entities.livingentities.expressions;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Living Disguise - Spinning")
@Description("Set or get whether the disguise is spinning")
@Examples("set spinning of player's disguise to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprSpinning extends WatcherBooleanExpression<LivingWatcher> {

    static {
        register(ExprSpinning.class, Boolean.class, "spin[ning]");
    }

    @Override
    protected Boolean convert(LivingWatcher livingWatcher) {
        return livingWatcher.isSpinning();
    }

    @Override
    protected String getPropertyName() {
        return "spinning";
    }

    @Override
    protected void change(Event e, LivingWatcher livingWatcher, boolean bool) {
        livingWatcher.setSpinning(bool);
    }
}
