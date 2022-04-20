package me.tud.diskuise.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.util.Checker;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.events.DisguiseEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EvtDisguise extends SkriptEvent {

    static {
        Skript.registerEvent("Disguise", EvtDisguise.class, DisguiseEvent.class,
                "[entity] dis(g|k)uise [(of|for) %-entitydatas%]")
                .description("This event gets called when an entity disguises"
                        , "(Cancellable)")
                .examples("on entity disguise:",
                        "\tbroadcast \"%event-entity% disguised as %type of event-disguise%!\"")
                .since("1.0")
                .requiredPlugins("LibsDisguises");
        EventValues.registerEventValue(DisguiseEvent.class, Entity.class, new Getter<Entity, DisguiseEvent>() {
            @Override
            public @Nullable Entity get(DisguiseEvent arg) {
                return arg.getDisguised();
            }
        }, 0);
        EventValues.registerEventValue(DisguiseEvent.class, Disguise.class, new Getter<Disguise, DisguiseEvent>() {
            @Override
            public @Nullable Disguise get(DisguiseEvent arg) {
                return arg.getDisguise();
            }
        }, 0);
    }

    Literal<EntityData<?>> entities;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        entities = (Literal<EntityData<?>>) args[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        if (entities == null) return true;
        Entity entity = ((DisguiseEvent) e).getEntity();
        return entities.check(e, new Checker<EntityData<?>>() {
            @Override
            public boolean check(EntityData<?> o) {
                return o.isInstance(entity);
            }
        });
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }
}
