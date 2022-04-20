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

@Name("Disguise - Raise hand")
@Description("Sets if a disguise appears to be raising its main hand or offhand")
@Examples("raise main hand of player's disguise")
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseRaiseHand extends Effect {

    static {
        Skript.registerEffect(EffDisguiseRaiseHand.class,
                "(1¦lower|[1¦un]raise) [dis(k|g)uise] %disguise% [main[( |-)]]hand",
                "(1¦lower|[1¦un]raise) [dis(k|g)uise] %disguise% off[( |-)]hand");
    }

    Expression<Disguise> disguise;
    boolean bool;
    boolean isMainHand = true;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        LivingWatcher watcher;
        try {
            watcher = (LivingWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        if (!isMainHand) watcher.setOffhandRaised(bool);
        else watcher.setMainHandRaised(bool);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        if (matchedPattern == 1) isMainHand = false;
        bool = parseResult.mark != 1;
        return true;
    }
}
