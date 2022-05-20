package me.tud.diskuise.elements.watchers.areaeffectcloud.expressions;

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
import me.libraryaddict.disguise.disguisetypes.watchers.AreaEffectCloudWatcher;
import me.tud.diskuise.utils.DisguiseUtil;
import org.bukkit.Particle;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("AoE Disguise - Particle type")
@Description("Set or get an area effect disguise's particle type")
@Examples("set cloud particle type of player's disguise to flame")
@Since("0.2-beta1")
@RequiredPlugins({"LibsDisguises"})
public class ExprDisguiseParticleType extends SimpleExpression<Particle> {

    static {
        Skript.registerExpression(ExprDisguiseParticleType.class, Particle.class, ExpressionType.PROPERTY,
                "[the] [(area [of effect]|AoE)] cloud [potion[s]] particle[s] type [value] of [dis(k|g)uise] %disguise%",
                "[dis(k|g)uise] %disguise%'s [(area [of effect]|AoE)] cloud [potion[s]] particle[s] type [value]");
    }

    Expression<Disguise> disguise;

    @Override
    protected Particle[] get(Event e) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return null;
        return new Particle[]{disguise.getWatcher() instanceof AreaEffectCloudWatcher ?
                ((AreaEffectCloudWatcher) disguise.getWatcher()).getParticleType() : null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Particle> getReturnType() {
        return Particle.class;
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
        switch (mode) {
            case SET, RESET -> { return CollectionUtils.array(Particle.class); }
        }
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Disguise disguise = this.disguise.getSingle(e);
        if (disguise == null) return;
        AreaEffectCloudWatcher watcher;
        if (disguise.getWatcher() instanceof AreaEffectCloudWatcher) watcher = (AreaEffectCloudWatcher) disguise.getWatcher();
        else return;

        Particle particle = Particle.SPELL_MOB;
        if (mode == Changer.ChangeMode.SET && delta[0] != null) particle = (Particle) delta[0];
        watcher.setParticleType(particle);
        DisguiseUtil.update(disguise);
    }
}
