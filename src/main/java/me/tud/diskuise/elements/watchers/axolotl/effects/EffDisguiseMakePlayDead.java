package me.tud.diskuise.elements.watchers.axolotl.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl Disguise - Make Play dead")
@Description("Sets if an axolotl disguise appears to be playing dead")
@Examples("make {disguise} play dead")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseMakePlayDead extends Effect {

    static {
        Skript.registerEffect(EffDisguiseMakePlayDead.class,
                "make [dis(k|g)uise] %disguise% [1¦not] play[(ing|s)] dead");
    }

    Expression<Disguise> disguise;
    boolean bool;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        AxolotlWatcher watcher;
        try {
            watcher = (AxolotlWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        watcher.setPlayingDead(bool);
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
