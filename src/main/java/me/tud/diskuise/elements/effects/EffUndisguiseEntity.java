package me.tud.diskuise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.*;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Undisguise Entity")
@Description("Undisguises an entity")
@Examples({"disguise all players as a cow disguise",
        "disguise target entity to zombie",
        "disguise all players as \"_tud\""})
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class EffUndisguiseEntity extends Effect {

    static {
        Skript.registerEffect(EffUndisguiseEntity.class,
                "undis(g|k)uise %entities%");
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
