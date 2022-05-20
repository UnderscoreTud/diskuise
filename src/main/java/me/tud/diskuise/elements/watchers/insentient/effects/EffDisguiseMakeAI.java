package me.tud.diskuise.elements.watchers.insentient.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.InsentientWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Insentient Disguise - Make AI")
@Description("Sets if an insentient disguise has AI")
@Examples("make player's disguise have ai")
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakeAI extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakeAI.class,
                "make [dis(k|g)uise] %disguise% [1¦not] have (ai|artificial intelligence)");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        InsentientWatcher watcher;
        if (disguise.getWatcher() instanceof InsentientWatcher) watcher = (InsentientWatcher) disguise.getWatcher();
        else return;
        watcher.setAI(bool);
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