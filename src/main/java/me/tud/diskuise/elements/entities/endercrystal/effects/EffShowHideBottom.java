package me.tud.diskuise.elements.entities.endercrystal.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.EnderCrystalWatcher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Ender Crystal Disguise - Bottom")
@Description("Set whether an ender crystal disguise has the bottom shown")
@Examples("show the bottom of player's disguise")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class EffShowHideBottom extends Effect {

    static {
        Skript.registerEffect(EffShowHideBottom.class,
                "(:show|:hide) [the] bottom of [disguise[s]] %disguises%",
                "(:show|:hide) [disguise[s]] %disguises%'[s] bottom");
    }

    private Expression<Disguise> disguiseExpr;
    private boolean show;

    @Override
    protected void execute(Event e) {
        for (Disguise disguise : disguiseExpr.getArray(e))
            if (disguise.getWatcher() instanceof EnderCrystalWatcher enderCrystalWatcher)
                enderCrystalWatcher.setShowBottom(show);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return (show ? "show" : "hide") + " the bottom of " + disguiseExpr.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        show = parseResult.hasTag("show");
        disguiseExpr = (Expression<Disguise>) exprs[0];
        return true;
    }
}
