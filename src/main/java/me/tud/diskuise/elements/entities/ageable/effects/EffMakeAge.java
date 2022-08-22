package me.tud.diskuise.elements.entities.ageable.effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.tud.diskuise.elements.entities.ageable.Age;
import me.tud.diskuise.elements.entities.ageable.AgeUtil;
import me.tud.diskuise.util.skript.WatcherMakeEffect;
import org.bukkit.event.Event;

@Name("Ageable Disguise - Make Age")
@Description("Set the age of a disguise")
@Examples("make {dis} an adult")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class EffMakeAge extends WatcherMakeEffect<FlagWatcher> {

    static {
        register(EffMakeAge.class, "(adult:[an] adult|baby:[a] baby)");
    }

    private Age age;

    @Override
    protected String getProperty() {
        return age.name().toLowerCase();
    }

    @Override
    protected void make(Event e, FlagWatcher flagWatcher, boolean bool) {
        AgeUtil.setDisguiseAge(flagWatcher, age);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        age = Age.valueOf(parseResult.tags.get(0).toUpperCase());
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }
}
