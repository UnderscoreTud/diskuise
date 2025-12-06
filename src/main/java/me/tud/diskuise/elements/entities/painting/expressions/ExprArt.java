package me.tud.diskuise.elements.entities.painting.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.PaintingWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.Art;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Painting Disguise - Art")
@Description("Set or get the 'art' of a painting disguise")
@Examples("set art player's disguise creebet")
@Since("0.2-beta2")
@RequiredPlugins("LibsDisguises")
public class ExprArt extends WatcherPropertyExpression<PaintingWatcher, Art> {

    static {
        register(ExprArt.class, Art.class, "[painting] art");
    }

    @Override
    protected Art convert(PaintingWatcher paintingWatcher) {
        return paintingWatcher.getArt();
    }

    @Override
    protected String getPropertyName() {
        return "painting art";
    }

    @Override
    public Class<? extends Art> getReturnType() {
        return Art.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
        return mode == ChangeMode.SET ? CollectionUtils.array(Art.class) : null;
    }

    @Override
    protected void change(Event e, PaintingWatcher paintingWatcher, @Nullable Object[] delta, ChangeMode mode) {
        paintingWatcher.setArt((Art) delta[0]);
    }
}
