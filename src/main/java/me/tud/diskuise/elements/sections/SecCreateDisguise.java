package me.tud.diskuise.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.DisguiseUtils;
import org.bukkit.OfflinePlayer;
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
public class SecCreateDisguise extends Section {

    static {
        Skript.registerSection(SecCreateDisguise.class,
                "[create] [a] [new] %*entitydata% dis(g|k)uise",
                "[create] [a] [new] dis(g|k)uise from %string/offlineplayer/entitydata%");
    }

    private Expression<?> expr;
    private Disguise sectionDisguise;

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
    public boolean init(Expression<?>[] exprs,
                        int matchedPattern,
                        Kleenean isDelayed,
                        SkriptParser.ParseResult parseResult,
                        SectionNode sectionNode,
                        List<TriggerItem> triggerItems) {
        expr = exprs[0];
        if (expr instanceof Literal<?> literal)
            if (((EntityData<?>) literal.getSingle()).getSuperType().equals(EntityData.fromClass(Player.class))) {
                Skript.error("Use a string to create a player disguise, e.g. 'set {_disguise} to disguise from \"_tud\"");
                return false;
            }
        loadCode(sectionNode);
        return true;
    }

    @Override
    protected @Nullable TriggerItem walk(Event e) {
        sectionDisguise = getDisguise(e);
        return super.walk(e, true);
    }

    public Disguise getSectionDisguise() {
        return sectionDisguise;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "disguise from " + expr.toString(e, debug);
    }

}
