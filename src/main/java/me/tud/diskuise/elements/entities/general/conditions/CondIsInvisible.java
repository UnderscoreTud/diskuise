package me.tud.diskuise.elements.entities.general.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Disguise - Is Invisible")
@Description("Checks whether a disguise is invisible")
@Examples("if player's disguise is invisible:")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsInvisible extends WatcherPropertyCondition<FlagWatcher> {

    static {
        Skript.registerCondition(CondIsInvisible.class,
                "[disguise[s]] %disguises% (is|are) invisible",
                "[disguise[s]] %disguises% (isn't|is not|aren't|are not) invisible",
                "[disguise[s]] %disguises% (isn't|is not|aren't|are not) visible",
                "[disguise[s]] %disguises% (is|are) visible");
    }

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return flagWatcher.isInvisible();
    }

    @Override
    protected String getPropertyName() {
        return "invisible";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Disguise>) exprs[0]);
        setNegated(matchedPattern % 2 == 0);
        return true;
    }
}
