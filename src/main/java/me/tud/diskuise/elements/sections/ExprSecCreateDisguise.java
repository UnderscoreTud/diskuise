package me.tud.diskuise.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.ExpressionSection;
import org.bukkit.entity.Player;
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

    private Expression<?> expr;
    private Disguise sectionDisguise;

    @Override
    protected @Nullable Disguise[] get(Event e) {
        return new Disguise[]{getDisguise(e)};
    }

    private Disguise getDisguise(Event e) {
        if (expr.getSingle(e) instanceof String name)
            return DisguiseUtils.createDisguise(name);
        EntityData<?> entityData = (EntityData<?>) expr.getSingle(e);
        if (entityData == null) return null;
        return DisguiseUtils.createDisguise(entityData);
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
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs,
                        int matchedPattern,
                        Kleenean isDelayed,
                        SkriptParser.ParseResult parseResult,
                        @Nullable SectionNode sectionNode,
                        @Nullable List<TriggerItem> triggerItems) {
        expr = exprs[0];
        if (expr instanceof Literal<?> literal)
            if (((EntityData<?>) literal.getSingle()).getSuperType().equals(EntityData.fromClass(Player.class))) {
                Skript.error("Use a string to create a player disguise, e.g. 'set {_disguise} to \"_tud\" player disguise");
                return false;
            }
        if (sectionNode != null) loadCode(sectionNode);
        return true;
    }

    @Override
    protected @Nullable TriggerItem walk(Event e) {
        sectionDisguise = getDisguise(e);
        DisguiseUtils.setLastCreatedDisguise(sectionDisguise);
        return super.walk(e, true);
    }

    public Disguise getSectionDisguise() {
        return sectionDisguise;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        if (expr.getSingle(e) instanceof String) return "a \"" + expr.toString(e, debug) + "\" player disguise";
        return "a " + expr.toString(e, debug) + " disguise";
    }

}
