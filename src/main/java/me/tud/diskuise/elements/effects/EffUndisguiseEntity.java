package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Undisguise Entity")
@Description("Undisguises an entity")
@Examples("undisguise all players")
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class EffUndisguiseEntity extends Effect {

    static {
        Skript.registerEffect(EffUndisguiseEntity.class, "undisguise %entities%");
    }

    private Expression<Entity> entities;

    @Override
    protected void execute(Event e) {
        for (Entity entity : this.entities.getArray(e)) DisguiseUtils.undisguise(entity);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "undisguise " + entities.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        return true;
    }
}
