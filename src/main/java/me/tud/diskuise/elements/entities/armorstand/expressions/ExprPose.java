package me.tud.diskuise.elements.entities.armorstand.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.elements.entities.armorstand.BetterArmorStandWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Armor Stand Disguise - Pose")
@Description("Set or get the pose of an armor stand disguise")
@Examples("set head pose of player's disguise to vector(90, 0, 0)")
@Since("0.3.1")
@RequiredPlugins("LibsDisguises")
public class ExprPose extends WatcherPropertyExpression<BetterArmorStandWatcher, Vector> {

    static {
        register(ExprPose.class, Vector.class,
                "(:head|:body|:right arm|:left arm|:right leg|:left leg) pose");
    }

    String part;

    @Override
    protected Vector convert(BetterArmorStandWatcher armorStandWatcher) {
        return getPose(armorStandWatcher, part);
    }

    private Vector getVector(EulerAngle eulerAngle) {
        return new Vector(eulerAngle.getX(), eulerAngle.getY(), eulerAngle.getZ());
    }

    private Vector getPose(BetterArmorStandWatcher armorStandWatcher, String part) {
        return getVector(switch (part) {
            case "head" -> armorStandWatcher.getHead();
            case "body" -> armorStandWatcher.getBody();
            case "right arm" -> armorStandWatcher.getRightArm();
            case "left arm" -> armorStandWatcher.getLeftArm();
            case "right leg" -> armorStandWatcher.getRightLeg();
            case "left leg" -> armorStandWatcher.getLeftLeg();
            default -> null;
        });
    }

    private EulerAngle toAngle(Vector vector) {
        return new EulerAngle(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    protected String getPropertyName() {
        return part + " pose";
    }

    @Override
    public Class<? extends Vector> getReturnType() {
        return Vector.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        part = parseResult.tags.get(0);
        setExpr((Expression<? extends Disguise>) exprs[0]);
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, RESET -> CollectionUtils.array(Vector.class);
            default -> null;
        };
    }

    @Override
    protected void change(Event e, BetterArmorStandWatcher armorStandWatcher, @Nullable Object[] delta, ChangeMode mode) {
        Vector vector = (Vector) delta[0];
        Vector current = getPose(armorStandWatcher, part);
        if (vector == null) return;
        setPose(armorStandWatcher, part, switch (mode) {
            case SET -> toAngle(vector);
            case ADD -> toAngle(vector.add(current));
            case REMOVE -> toAngle(vector.subtract(current));
            default -> EulerAngle.ZERO;
        });
    }

    private void setPose(BetterArmorStandWatcher armorStandWatcher, String part, EulerAngle eulerAngle) {
        switch (part) {
            case "head" -> armorStandWatcher.setHead(eulerAngle);
            case "body" -> armorStandWatcher.setBody(eulerAngle);
            case "right arm" -> armorStandWatcher.setRightArm(eulerAngle);
            case "left arm" -> armorStandWatcher.setLeftArm(eulerAngle);
            case "right leg" -> armorStandWatcher.setRightLeg(eulerAngle);
            case "left leg" -> armorStandWatcher.setLeftLeg(eulerAngle);
        }
    }
}
