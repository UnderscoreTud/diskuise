package me.tud.diskuise.elements.entities.axolotl.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.entity.Axolotl;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Axolotl Disguise - Variant")
@Description("Set or get the variant of an axolotl disguise")
@Examples("set axolotl variant of player's disguise to lucy")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprAxolotlVariant extends WatcherPropertyExpression<AxolotlWatcher, Axolotl.Variant> {

    static {
        register(ExprAxolotlVariant.class, Axolotl.Variant.class, "axolotl variant");
    }

    @Override
    protected Axolotl.Variant convert(AxolotlWatcher axolotlWatcher) {
        return axolotlWatcher.getVariant();
    }

    @Override
    protected String getPropertyName() {
        return "axolotl variant";
    }

    @Override
    public Class<? extends Axolotl.Variant> getReturnType() {
        return Axolotl.Variant.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Axolotl.Variant.class) : null;
    }

    @Override
    protected void change(Event e, AxolotlWatcher axolotlWatcher, @Nullable Object[] delta, ChangeMode mode) {
        axolotlWatcher.setVariant((Axolotl.Variant) delta[0]);
    }
}
