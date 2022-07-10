package me.tud.diskuise.elements.types;

import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.tud.diskuise.util.DisguiseUtils;
import me.tud.diskuise.util.skript.ClassUtils;
import org.jetbrains.annotations.Nullable;

import java.io.StreamCorruptedException;

public class TypDisguise {
    static {
        ClassUtils.registerClass(new ClassInfo<>(Disguise.class, "disguise")
                .user("disguises?")
                .name("Disguise")
                .description("A customizable disguise")
                .examples("set {disguise} to a new cow disguise",
                        "set view self of disguise {disguise} to false",
                        "set age of disguise {disguise} to baby",
                        "disguise player as {disguise}")
                .since("0.1")
                .parser(new Parser<>() {
                    @Override
                    public @Nullable Disguise parse(String s, ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(Disguise o, int flags) {
                        return EntityUtils.toSkriptEntityData(o.getType().getEntityType()).toString(flags) + " disguise";
                    }

                    @Override
                    public String toVariableNameString(Disguise o) {
                        return EntityUtils.toSkriptEntityData(o.getType().getEntityType()) + " disguise";
                    }
                })
                .serializer(new Serializer<>() {
                    @Override
                    public Fields serialize(Disguise o) {
                        Fields fields = new Fields();
                        fields.putObject("type", o.getType().toString());
                        return fields;
                    }

                    @Override
                    public void deserialize(Disguise o, Fields f) {
                        assert false;
                    }

                    @Override
                    protected Disguise deserialize(Fields fields) throws StreamCorruptedException {
                        DisguiseType disguiseType = DisguiseType.valueOf(fields.getAndRemoveObject("type", String.class));
                        return DisguiseUtils.createDisguise(disguiseType);
                    }

                    @Override
                    public boolean mustSyncDeserialization() {
                        return false;
                    }

                    @Override
                    protected boolean canBeInstantiated() {
                        return false;
                    }
                })
        );
    }
}
