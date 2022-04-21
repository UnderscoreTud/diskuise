package me.tud.diskuise.elements.watchers.ageable.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Disguise - Age")
@Description("Returns the age of a disguise (as a string)")
@Examples("broadcast age of player's disguise")
@Since("0.2-beta0")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseAge extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprDisguiseAge.class, String.class, ExpressionType.PROPERTY,
                "[the] age [(option|value)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s age [(option|value)]");
    }

    Expression<Disguise> disguise;
    boolean isBaby = false;

    @Override
    protected @Nullable
    String[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        MobDisguise mobDisguise = (MobDisguise) disguise;
        assert mobDisguise != null;
        LivingWatcher watcher = mobDisguise.getWatcher();
        try {
            if (((AgeableWatcher) watcher).isBaby()) isBaby = true;
        } catch (ClassCastException ignore) {}
        try {
            if (((ZombieWatcher) watcher).isBaby()) isBaby = true;
        } catch (ClassCastException ignore) {}
        try {
            if (((PiglinWatcher) watcher).isBaby()) isBaby = true;
        } catch (ClassCastException ignore) {}
        try {
            if (((ZoglinWatcher) watcher).isBaby()) isBaby = true;
        } catch (ClassCastException ignore) {}
        String age = (isBaby) ? "baby" : "adult";
        return new String[]{age};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }
}
