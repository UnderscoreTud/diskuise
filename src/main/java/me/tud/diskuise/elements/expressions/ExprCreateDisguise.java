package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprCreateDisguise extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprCreateDisguise.class, Disguise.class, ExpressionType.COMBINED,
                "[a] [new] %*entitydata% dis(g|k)uise",
                "[a] [new] dis(g|k)uise from %string/offlineplayer/entitydata%");
    }

    private Expression<?> expr;

    private Disguise getDisguise(Event e) {
        Object object = expr.getSingle(e);
        if (object instanceof String name)
            return DisguiseUtils.createDisguise(name);
        else if (object instanceof OfflinePlayer player)
            return DisguiseUtils.createDisguise(player.getName());
        EntityData<?> entityData = (EntityData<?>) object;
        if (entityData == null) return null;
        return DisguiseUtils.createDisguise(entityData);
    }

    @Override
    protected @Nullable Disguise[] get(Event e) {
        return new Disguise[]{getDisguise(e)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "disguise from " + expr.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expr = exprs[0];
        if (expr instanceof Literal<?> literal)
            if (((EntityData<?>) literal.getSingle()).getSuperType().equals(EntityData.fromClass(Player.class))) {
                Skript.error("Use a string to create a player disguise, e.g. 'set {_disguise} to disguise from \"_tud\"");
                return false;
            }
        return true;
    }
}
