package me.tud.diskuise.elements.watchers.living.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Spin")
@Description("Sets if a disguise appears to be spinning")
@Examples("make player's disguise spin")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeSpin extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeSpin.class,
                "make [dis(k|g)uise] %disguise% [1¦not] spin[ning][s]");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        watcher.setSpinning(bool);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        bool = parseResult.mark != 1;
        return true;
    }
}
