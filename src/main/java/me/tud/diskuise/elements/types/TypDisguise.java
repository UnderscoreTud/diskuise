package me.tud.diskuise.elements.types;

import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.tud.diskuise.util.skript.ClassUtils;
import org.jetbrains.annotations.Nullable;

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
        );
    }
}
