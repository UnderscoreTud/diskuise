package me.tud.diskuise.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

@Name("Disguise Of Entity")
@Description("Returns the disguise of an entity")
@Examples("broadcast player's disguise")
@Since("0.1")
@RequiredPlugins("LibsDisguises")
public class ExprDisguiseOfEntity extends SimplePropertyExpression<Entity, Disguise> {

    static {
        register(ExprDisguiseOfEntity.class, Disguise.class, "dis(g|k)uise", "entities");
    }

    @Override
    protected String getPropertyName() {
        return "disguise";
    }

    @Override
    public @Nullable Disguise convert(Entity entity) {
        return DisguiseAPI.getDisguise(entity);
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }
}
