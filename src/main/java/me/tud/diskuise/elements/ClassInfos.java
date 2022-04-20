package me.tud.diskuise.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;

public class ClassInfos {
    static {
        Classes.registerClass(new ClassInfo<>(Disguise.class, "disguise")
                .user("disguises?")
                .name("Disguise")
                .description("A customizable disguise")
                .examples("set {disguise} to a new cow disguise",
                        "\tset view self of disguise {disguise} to false",
                        "\tset age of disguise {disguise} to baby",
                        "\tdisguise player as {disguise}"
                )
                .since("0.1")
                .defaultExpression(new EventValueExpression<>(Disguise.class))
                .requiredPlugins("LibsDisguises")
                .parser(new Parser<Disguise>() {
                    @Override
                    public Disguise parse(String input, ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(Disguise o, int flags) {
                        return toVariableNameString(o);
                    }

                    @Override
                    public String toVariableNameString(Disguise o) {
                        return "disguise:" + o.getType().toReadable().toLowerCase().replace(" ", "_");
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(DisguiseConfig.NotifyBar.class, "notifybar")
                .user("notify ?bars?")
                .name("Notify Bar")
                .description("Change the notify bar of a disguise")
                .examples("set notifybar of {disguise} to none",
                        "set notifybar of {disguise} to action bar",
                        "set {disguise}'s notify bar to bossbar"
                )
                .since("0.2")
                .usage("boss bar", "action bar", "none")
                .defaultExpression(new EventValueExpression<>(DisguiseConfig.NotifyBar.class))
                .requiredPlugins("LibsDisguises")
                .parser(new Parser<DisguiseConfig.NotifyBar>() {
                    @Override
                    public DisguiseConfig.NotifyBar parse(String input, ParseContext context) {
                        for (DisguiseConfig.NotifyBar notifyBar : DisguiseConfig.NotifyBar.values()) {
                            if (notifyBar.toString().replace("_", " ").equalsIgnoreCase(input)) return notifyBar;
                            if (notifyBar.toString().replace("_", "").equalsIgnoreCase(input)) return notifyBar;
                        }
                        return null;
                    }

                    @Override
                    public String toString(DisguiseConfig.NotifyBar o, int flags) {
                        return toVariableNameString(o);
                    }

                    @Override
                    public String toVariableNameString(DisguiseConfig.NotifyBar o) {
                        return o.toString().toLowerCase().replace("_", "");
                    }
                })
                .serializer(new Serializer<DisguiseConfig.NotifyBar>() {
                    @Override
                    public Fields serialize(DisguiseConfig.NotifyBar o) throws NotSerializableException {
                        Fields f = new Fields();
                        f.putObject("notifybar", o);
                        return f;
                    }

                    @Override
                    public DisguiseConfig.NotifyBar deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                        return f.getObject("notifybar", DisguiseConfig.NotifyBar.class);
                    }

                    @Override
                    public void deserialize(DisguiseConfig.NotifyBar o, Fields f) throws StreamCorruptedException, NotSerializableException {
                        assert false;
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
