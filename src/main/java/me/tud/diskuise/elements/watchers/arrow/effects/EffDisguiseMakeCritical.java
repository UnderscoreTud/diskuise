package me.tud.diskuise.elements.watchers.arrow.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.ArrowWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Arrow Disguise - Make Critical")
@Description("Sets if a falling block disguise is locked to a grid")
@Examples("make player's disguise critical")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeCritical extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeCritical.class,
                "make [dis(k|g)uise] %disguise% [1¦not] crit[ical][(s|ing)]");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        ArrowWatcher watcher;
        try {
            watcher = (ArrowWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        watcher.setCritical(bool);
        DisguiseUtil.update(disguise);
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
