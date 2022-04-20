package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Undisguise Entity")
@Description("Resets an entity's disguise")
@Examples("undisguise player")
@Since("0.1")
@RequiredPlugins({"LibsDisguises"})
public class EffUndisguise extends Effect {

    static {
        Skript.registerEffect(EffUndisguise.class,
                "undis(g|k)uise %entities%",
                "(reset|clear|delete) %entities%'[s] dis(g|k)uise");
    }

    Expression<Entity> entities;

    @Override
    protected void execute(Event e) {
        for (Entity entity : this.entities.getAll(e)) DisguiseAPI.undisguiseToAll(entity);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        return true;
    }
}
