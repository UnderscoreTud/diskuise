package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

@Name("Create disguise")
@Description("Creates a new customizable disguise")
@Examples("set {_disguise} to a new cow disguise")
@Since("0.1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseCreate extends SimpleExpression<Disguise> {

    static {
        Skript.registerExpression(ExprDisguiseCreate.class, Disguise.class, ExpressionType.PROPERTY,
                "[create] [a] [new] %entitydata% dis(g|k)uise",
                "[create] [a] [new] %string% [player] dis(g|k)uise");
    }

    Expression<EntityData> entityData;
    Expression<String> playerName;

    @Override
    protected @Nullable
    Disguise[] get(Event e) {
        Disguise disguise = null;
        // if pattern is 1
        if (playerName != null) {
            String playerName = this.playerName.getSingle(e);
            assert playerName != null;
            disguise = new PlayerDisguise(playerName);
            return new Disguise[]{disguise};
        }

        // if pattern is 0
        EntityData<?> entityData = this.entityData.getSingle(e);
        assert entityData != null;
        DisguiseType type = DisguiseType.getType(EntityUtils.toBukkitEntityType(entityData));
        try {
            disguise = new MobDisguise(type);
        } catch(InvalidParameterException ignore) {}
        try {
            disguise = new MiscDisguise(type);
        } catch(InvalidParameterException ignore) {}
        return new Disguise[]{disguise};

//        EntityData disguiseType = this.disguiseType.getSingle(e);
//        assert disguiseType != null;
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
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (matchedPattern == 0) entityData = (Expression<EntityData>) exprs[0];
        else playerName = (Expression<String>) exprs[0];
        return true;
    }
}
