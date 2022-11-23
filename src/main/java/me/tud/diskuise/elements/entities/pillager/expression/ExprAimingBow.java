package me.tud.diskuise.elements.entities.pillager.expression;

import ch.njol.skript.doc.*;
import me.libraryaddict.disguise.disguisetypes.watchers.PillagerWatcher;
import me.tud.diskuise.util.skript.WatcherBooleanExpression;
import org.bukkit.event.Event;

@Name("Pillager Disguise - Aiming Bow")
@Description("Set or get whether a pillager disguise is aiming a bow")
@Examples("set aiming bow state of player's disguise to true")
@Since("0.3.2")
@RequiredPlugins("LibsDisguises")
public class ExprAimingBow extends WatcherBooleanExpression<PillagerWatcher> {

    static {
        register(ExprAimingBow.class, "aiming [a] bow");
    }

    @Override
    protected Boolean convert(PillagerWatcher pillagerWatcher) {
        return pillagerWatcher.isAimingBow();
    }

    @Override
    protected String getPropertyName() {
        return "aiming a bow";
    }

    @Override
    protected void change(Event e, PillagerWatcher pillagerWatcher, boolean state) {
        pillagerWatcher.setAimingBow(state);
    }
}
