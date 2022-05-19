package me.tud.diskuise.elements.watchers.axolotl.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.entity.Axolotl;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl Disguise - Variant")
@Description({"Set or get the variant of an axolotl disguise"})
@Examples("set axolotl variant of disguise {dis} to lucy")
@Since("0.2-beta3")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseVariant extends SimpleExpression<Axolotl.Variant> {

    static {
        Skript.registerExpression(ExprDisguiseVariant.class, Axolotl.Variant.class, ExpressionType.PROPERTY,
                "[the] [axolotl] (variant|color) [(value|option|state)] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [axolotl] (variant|color) [(value|option|state)]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Axolotl.Variant[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        AxolotlWatcher watcher;
        try {
            watcher = (AxolotlWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return null; }
        if (watcher == null) return null;
        return new Axolotl.Variant[]{watcher.getVariant()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Axolotl.Variant> getReturnType() {
        return Axolotl.Variant.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        disguise = (Expression<Disguise>) exprs[0];
        return true;
    }

    @Override
    public @Nullable
    Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return CollectionUtils.array(Axolotl.Variant.class);
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        AxolotlWatcher watcher;
        try {
            watcher = (AxolotlWatcher) disguise.getWatcher();
        } catch (ClassCastException ignore) { return; }
        assert delta[0] != null;
        watcher.setVariant((Axolotl.Variant) delta[0]);
        DisguiseUtil.update(disguise);
    }
}
