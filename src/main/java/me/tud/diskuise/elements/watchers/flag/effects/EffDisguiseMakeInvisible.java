package me.tud.diskuise.elements.watchers.flag.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Invisible")
@Description("Sets if a disguise is invisible")
@Examples("make {disgusie} visible")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeInvisible extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeInvisible.class,
                "make [dis(k|g)uise] %disguise% [1¦not] invisible[s]",
                "make [dis(k|g)uise] %disguise% [1¦not] visible[s]");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return;
        watcher.setInvisible(bool);
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
        if (matchedPattern == 3) bool = !bool;
        return true;
    }
}
