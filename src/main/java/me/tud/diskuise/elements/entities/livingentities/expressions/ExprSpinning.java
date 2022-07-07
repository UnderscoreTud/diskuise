package me.tud.diskuise.elements.entities.livingentities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;

@Name("Living Disguise - Spinning")
@Description("Set or get whether the disguise is spinning")
@Examples("set spinning of player's disguise to true")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprSpinning extends WatcherPropertyExpression<LivingWatcher, Boolean> {

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
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    protected void change(Event e, LivingWatcher livingWatcher, Object[] delta, Changer.ChangeMode mode) {
        livingWatcher.setSpinning((boolean) delta[0]);
    }
}
