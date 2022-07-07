package me.tud.diskuise.elements.entities.axolotl.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.watchers.AxolotlWatcher;
import me.tud.diskuise.util.skript.WatcherPropertyExpression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl Disguise - Playing Dead")
@Description("Set or get whether the axolotl disguise is playing dead")
@Examples("set playing dead of player's disguise to true")
@Since("0.2-beta3")
@RequiredPlugins("LibsDisguises")
public class ExprPlayingDead extends WatcherPropertyExpression<AxolotlWatcher, Boolean> {

    static {
        register(ExprPlayingDead.class, Boolean.class, "play[ing] dead");
    }

    @Override
    protected Boolean convert(AxolotlWatcher axolotlWatcher) {
        return axolotlWatcher.isPlayingDead();
    }

    @Override
    protected String getPropertyName() {
        return "playing dead";
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode == Changer.ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    protected void change(Event e, AxolotlWatcher axolotlWatcher, Object[] delta, Changer.ChangeMode mode) {
        axolotlWatcher.setPlayingDead((boolean) delta[0]);
    }
}
