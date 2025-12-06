package me.tud.diskuise.elements.entities.guardian.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.tud.diskuise.elements.entities.guardian.BetterGuardianWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Guardian Disguise - Laser Beam")
@Description("Set or get the targeted entity of a guardian disguise laser beam")
@Examples("set laser beam target of player's disguise to {_entity}")
@Since("0.3")
@RequiredPlugins("LibsDisguises")
public class ExprLaserTarget extends WatcherPropertyExpression<BetterGuardianWatcher, Entity> {

    static {
        register(ExprLaserTarget.class, Entity.class, "(laser|guardian) beam [target]");
    }

    @Override
    protected Entity convert(BetterGuardianWatcher guardianWatcher) {
        return guardianWatcher.getTargetEntity();
    }

    @Override
    protected String getPropertyName() {
        return "guardian beam target";
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, RESET, DELETE -> CollectionUtils.array(Entity.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, BetterGuardianWatcher guardianWatcher, @Nullable Object[] delta, ChangeMode mode) {
        switch (mode) {
            case DELETE, RESET -> guardianWatcher.setTarget((Entity) null);
            case SET -> {
                Entity entity = (Entity) delta[0];
                if (entity == null) return;
                guardianWatcher.setTarget(entity);
            }
        }
    }
}
