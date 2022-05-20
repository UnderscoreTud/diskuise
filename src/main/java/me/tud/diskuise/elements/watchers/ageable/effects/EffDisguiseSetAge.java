package me.tud.diskuise.elements.watchers.ageable.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.*;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Ageable Disguise - Set Age")
@Description("Sets the age of a disguise")
@Examples("set age of disguise {dis} to baby")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class EffDisguiseSetAge extends Effect {

    static {
        Skript.registerEffect(EffDisguiseSetAge.class,
                "(set|change) [the] (1¦adult|2¦baby) [(value|option|state)] of [dis(k|g)uise] %disguise% to %boolean%",
                "(set|change) [dis(k|g)uise] %disguise% (1¦adult|2¦baby) [(value|option|state)] to %boolean%",

                "(set|change) [the] age [(value|option|state)] of [dis(k|g)uise] %disguise% to (1¦adult|2¦baby)",
                "(set|change) [dis(k|g)uise] %disguise% age [(value|option|state)] to (1¦adult|2¦baby)");
    }

    Expression<Disguise> disguise;
    Expression<Boolean> bool;
    boolean isNegated = false;
    int pattern;

    @Override
    protected void execute(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        boolean isAdult;
        if (pattern == 0) isAdult = Boolean.TRUE.equals(this.bool.getSingle(e));
        else isAdult = false;
        isAdult = isAdult == isNegated;

        FlagWatcher watcher = disguise.getWatcher();
        if (watcher instanceof AgeableWatcher) ((AgeableWatcher) watcher).setBaby(!isAdult);
        else if (watcher instanceof ZombieWatcher) ((ZombieWatcher) watcher).setBaby(!isAdult);
        else if (watcher instanceof PiglinWatcher) ((PiglinWatcher) watcher).setBaby(!isAdult);
        else if (watcher instanceof ZoglinWatcher) ((ZoglinWatcher) watcher).setBaby(!isAdult);
        else return;
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
        if (exprs.length == 2) {
            pattern = 0;
            bool = (Expression<Boolean>) exprs[1];
            if (parseResult.mark == 1) isNegated = true;
        }
        else if (exprs.length == 1) {
            pattern = 1;
            if (parseResult.mark == 2) isNegated = true;
        }
        return true;
    }
}
