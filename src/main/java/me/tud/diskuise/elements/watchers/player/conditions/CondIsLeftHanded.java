package me.tud.diskuise.elements.watchers.player.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import org.bukkit.event.Event;
import org.bukkit.inventory.MainHand;
import org.jetbrains.annotations.Nullable;

@Name("Player Disguise - Is Left handed")
@Description("Checks if a disguise is left handed")
@Examples({"if {dis} is left handed:",
            "\tmake {dis} right handed"})
@Since("0.2-beta2")
@RequiredPlugins({"LibsDisguises"})
public class CondIsLeftHanded extends Condition {

    static {
        Skript.registerCondition(CondIsLeftHanded.class,
                "[dis(k|g)uise] %disguise% (1¦is|2¦is(n't| not)) left[( |-)]handed");
    }

    Expression<Disguise> disguise;

    @Override
    public boolean check(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return false;
        PlayerWatcher watcher;
        try {
            watcher = (PlayerWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return false; }
        return (watcher.getMainHand() == MainHand.LEFT) != isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 2) setNegated(true);
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
