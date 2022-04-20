package me.tud.diskuise.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Is Disguised")
@Description("Checks if an entity is disguised")
@Examples({"if player is disguised:",
            "\tundisguise player"})
@Since("1.0")
@RequiredPlugins({"LibsDisguises"})
public class CondIsDisguised extends Condition {

    static {
        Skript.registerCondition(CondIsDisguised.class,
                "%entity% (1¦is|2¦is(n't| not)) [in] [a] dis(k|g)uise(|s|d)");
    }

    Expression<Entity> entity;

    @Override
    public boolean check(Event e) {
        Entity entity = this.entity.getSingle(e);
        if (entity == null) return false;
        return DisguiseAPI.isDisguised(entity) == isNegated();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (parseResult.mark == 1) setNegated(true);
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
