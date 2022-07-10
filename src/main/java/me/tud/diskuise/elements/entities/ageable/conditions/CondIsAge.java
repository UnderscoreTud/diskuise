package me.tud.diskuise.elements.entities.ageable.conditions;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.elements.entities.ageable.AgeUtil;
import me.tud.diskuise.util.skript.WatcherPropertyCondition;

@Name("Ageable Disguise - Is Age")
@Description("Checks whether the disguise is an adult or a baby")
@Examples("if player's disguise is baby")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class CondIsAge extends WatcherPropertyCondition<FlagWatcher> {

    static {
        register(CondIsAge.class, "(adult:[an] adult|baby:[a] baby)");
    }

    private Age age;

    @Override
    protected boolean check(FlagWatcher flagWatcher) {
        return AgeUtil.getDisguiseAge(flagWatcher) == age;
    }

    @Override
    protected String getPropertyName() {
        return age.name().toLowerCase();
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        age = Age.valueOf(parseResult.tags.get(0).toUpperCase());
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }
}
