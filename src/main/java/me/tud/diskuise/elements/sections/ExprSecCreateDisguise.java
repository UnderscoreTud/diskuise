package me.tud.diskuise.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.Util;
import me.tud.diskuise.util.skript.ExpressionSection;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Create Disguise")
@Description("Creates a new customizable disguise")
@Examples({"command /disguise <entitytype>:",
        "\ttrigger:",
        "\t\tset {_disguise} to new arg 1 disguise",
        "\t\tdisguise player as {_disguise}",
        "\n",
        "disguise player as new cow disguise",
        "\n\n",
        "create a new zombie disguise:",
        "\tset burning of disguise to true",
        "\tmake disguise glow red",
        "\tdisguise all players as disguise"})
@Since("0.1, 0.3 (Section)")
@RequiredPlugins("LibsDisguises")
public class ExprSecCreateDisguise extends ExpressionSection<Disguise> {

    static {
        register(ExprSecCreateDisguise.class, Disguise.class, ExpressionType.PATTERN_MATCHES_EVERYTHING,
                "[create] [a] [new] %*entitydata% dis(g|k)uise",
                "[create] [a] [new] %string% [player] dis(g|k)uise");
    }

    @Override
    protected @Nullable Disguise[] get(Event e) {
        if (expr.getSingle(e) instanceof String name)
            return new Disguise[]{DisguiseUtils.getDisguise(name)};
        EntityData<?> entityData = (EntityData<?>) expr.getSingle(e);
        if (entityData == null) return null;
        EntityType entityType = EntityUtils.toBukkitEntityType(entityData);
        return new Disguise[]{DisguiseUtils.getDisguise(entityType)};
    }

    private Expression<?> expr;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Disguise> getReturnType() {
        return Disguise.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult,
                        @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if (sectionNode != null) loadCode(sectionNode);
        expr = exprs[0];
        return true;
    }

    @Override
    protected @Nullable TriggerItem walk(Event e) {
        return super.walk(e, true);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        if (expr.getSingle(e) instanceof String) return "a new \"" + expr.toString(e, debug) + "\" player disguise";
        return "a " + expr.toString(e, debug) + " disguise";
    }

}
