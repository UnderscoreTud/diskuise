package me.tud.diskuise.elements.expressions;

import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityType;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.skript.DisguisePropertyExpression;
import org.jetbrains.annotations.Nullable;

@Name("Entity Type Of Disguise")
@Description("Returns the entity type of a disguise")
@Examples("broadcast player's disguise")
@Since("0.2-beta0")
@RequiredPlugins("LibsDisguises")
public class ExprEntityTypeOfDisguise extends DisguisePropertyExpression<EntityType> {

    static {
        register(ExprEntityTypeOfDisguise.class, EntityType.class, "entity[( |-)]type");
    }

    @Override
    protected String getPropertyName() {
        return "entity type";
    }

    @Override
    public @Nullable EntityType convert(Disguise disguise) {
        return new EntityType(EntityUtils.toSkriptEntityData(disguise.getType().getEntityType()), 1);
    }

    @Override
    public Class<? extends EntityType> getReturnType() {
        return EntityType.class;
    }

}
