package me.tud.diskuise.elements.watchers.flag.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Make Custom name visible")
@Description("Sets if a disguise has its custom name visible")
@Examples("make custom name of {disguise} visible")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeCustomNameVisible extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeCustomNameVisible.class,
                "make [custom[( |-)]]name of [dis(k|g)uise] %disguise% [1¦not] visible");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        FlagWatcher watcher = disguise.getWatcher();
        if (watcher == null) return;
        watcher.setCustomNameVisible(bool);
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
