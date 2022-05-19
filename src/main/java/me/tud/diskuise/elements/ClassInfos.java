package me.tud.diskuise.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.Art;
import org.bukkit.Particle;
import org.bukkit.entity.Axolotl;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassInfos {
    static {
        String prefix = "diskuise ";

        if (Classes.getExactClassInfo(Disguise.class) == null) {
            Classes.registerClass(new ClassInfo<>(Disguise.class, "disguise")
                    .user("disguises?")
                    .name("Disguise")
                    .description("A customizable disguise")
                    .examples("set {disguise} to a new cow disguise",
                            "set view self of disguise {disguise} to false",
                            "set age of disguise {disguise} to baby",
                            "disguise player as {disguise}"
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
                            return EntityData.fromClass(o.getType().getEntityClass()) + " disguise";
                        }
                    })
            );
        }

        if (Classes.getExactClassInfo(DisguiseConfig.NotifyBar.class) == null) {
            Classes.registerClass(new ClassInfo<>(DisguiseConfig.NotifyBar.class, "notifybar")
                    .user("notify ?bars?")
                    .name("Notify Bar")
                    .description("Change the notify bar of a disguise")
                    .examples("set notifyBar of {disguise} to none",
                            "set notifyBar of {disguise} to action bar",
                            "set {disguise}'s notify bar to bossbar"
                    )
                    .since("0.2-beta0")
                    .usage("boss bar", "action bar", "none")
                    .defaultExpression(new EventValueExpression<>(DisguiseConfig.NotifyBar.class))
                    .requiredPlugins("LibsDisguises")
                    .parser(new Parser<DisguiseConfig.NotifyBar>() {
                        @Override
                        public DisguiseConfig.NotifyBar parse(String input, ParseContext context) {
                            for (DisguiseConfig.NotifyBar notifyBar : DisguiseConfig.NotifyBar.values()) {
                                if (notifyBar.toString().replace("_", " ").equalsIgnoreCase(input) ||
                                        notifyBar.toString().replace("_", "").equalsIgnoreCase(input))
                                    return notifyBar;
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
                            f.putObject("notifyBar", o);
                            return f;
                        }

                        @Override
                        public DisguiseConfig.NotifyBar deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                            return f.getObject("notifyBar", DisguiseConfig.NotifyBar.class);
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

        if (Classes.getExactClassInfo(Particle.class) == null) {
            List<String> particleList = new ArrayList<>();
        for (Particle particle : Particle.values()) {
            String name = particle.name().toLowerCase().replace("_", " ");
            if (name.contains("legacy")) continue;
            particleList.add(name);
        }
            Classes.registerClass(new ClassInfo<>(Particle.class, "diskuiseparticle")
                    .user("(diskuise)? ?particles?")
                    .name("Diskuise - Particle")
                    .description("A particle type")
                    .examples("set area effect cloud particle type of player's disguise to flame")
                    .since("0.2-beta1")
                    .usage(String.join(", ", particleList))
                    .defaultExpression(new EventValueExpression<>(Particle.class))
                    .requiredPlugins("LibsDisguises")
                    .parser(new Parser<Particle>() {
                        @Override
                        public Particle parse(String input, ParseContext context) {
                            if (input.length() > prefix.length() && input.substring(0, prefix.length()).equalsIgnoreCase(prefix))
                                input = input.substring(prefix.length());
                            for (String string : particleList) {
                                if (string.equalsIgnoreCase(input) ||
                                        string.replace("_", " ").equalsIgnoreCase(input) ||
                                        string.replace("_", "").equalsIgnoreCase(input))
                                    return Particle.valueOf(string.toUpperCase().replace(" ", "_"));
                            }
                            return null;
                        }

                        @Override
                        public String toString(Particle o, int flags) {
                            return toVariableNameString(o);
                        }

                        @Override
                        public String toVariableNameString(Particle o) {
                            return o.toString().toLowerCase().replace("_", " ");
                        }
                    })
                    .serializer(new Serializer<Particle>() {
                        @Override
                        public Fields serialize(Particle o) throws NotSerializableException {
                            Fields f = new Fields();
                            f.putObject("particle", o);
                            return f;
                        }

                        @Override
                        public Particle deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                            return f.getObject("particle", Particle.class);
                        }

                        @Override
                        public void deserialize(Particle o, Fields f) throws StreamCorruptedException, NotSerializableException {
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

        if (Classes.getExactClassInfo(Art.class) == null) {
            List<String> artList = new ArrayList<>();
            Arrays.asList(Art.values()).forEach(art -> artList.add(art.name().toLowerCase()));
            Classes.registerClass(new ClassInfo<>(Art.class, "diskuiseart")
                    .user("(diskuise)? ?(painting)? ?art")
                    .name("Diskuise - Painting art")
                    .description("The art of a painting")
                    .examples("set art of player's disguise to creebet")
                    .since("0.2-beta2")
                    .usage(String.join(", ", artList))
                    .defaultExpression(new EventValueExpression<>(Art.class))
                    .requiredPlugins("LibsDisguises")
                    .parser(new Parser<Art>() {
                        @Override
                        public Art parse(String input, ParseContext context) {
                            if (input.length() > prefix.length() && input.substring(0, prefix.length()).equalsIgnoreCase(prefix))
                                input = input.substring(prefix.length());
                            for (Art art : Art.values()) {
                                String name = art.name().toLowerCase();
                                if (name.equalsIgnoreCase(input) ||
                                        name.replace("_", " ").equalsIgnoreCase(input) ||
                                        name.replace("_", "").equalsIgnoreCase(input))
                                    return art;
                            }
                            return null;
                        }

                        @Override
                        public String toString(Art o, int flags) {
                            return toVariableNameString(o);
                        }

                        @Override
                        public String toVariableNameString(Art o) {
                            return o.toString().toLowerCase();
                        }
                    })
                    .serializer(new Serializer<Art>() {
                        @Override
                        public Fields serialize(Art o) throws NotSerializableException {
                            Fields f = new Fields();
                            f.putObject("art", o);
                            return f;
                        }

                        @Override
                        public Art deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                            return f.getObject("art", Art.class);
                        }

                        @Override
                        public void deserialize(Art o, Fields f) throws StreamCorruptedException, NotSerializableException {
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

        if (Classes.getExactClassInfo(Axolotl.Variant.class) == null) {
            List<String> axolotlVariantList = new ArrayList<>();
            Arrays.asList(Axolotl.Variant.values()).forEach(axolotl -> axolotlVariantList.add(axolotl.name().toLowerCase()));
            Classes.registerClass(new ClassInfo<>(Axolotl.Variant.class, "axolotlvariant")
                    .user("(diskuise)? ?axolotl variants?")
                    .name("Diskuise - Axolotl variant")
                    .description("The variant of an Axolotl")
                    .examples("set axolotl variant of player's disguise to lucy")
                    .since("0.2-beta3")
                    .usage(String.join(", ", axolotlVariantList))
                    .defaultExpression(new EventValueExpression<>(Axolotl.Variant.class))
                    .requiredPlugins("LibsDisguises")
                    .parser(new Parser<Axolotl.Variant>() {
                        @Override
                        public Axolotl.Variant parse(String input, ParseContext context) {
                            if (input.length() > prefix.length() && input.substring(0, prefix.length()).equalsIgnoreCase(prefix))
                                input = input.substring(prefix.length());
                            for (Axolotl.Variant variant : Axolotl.Variant.values()) {
                                String name = variant.name().toLowerCase();
                                if (name.equalsIgnoreCase(input) ||
                                        name.replace("_", " ").equalsIgnoreCase(input) ||
                                        name.replace("_", "").equalsIgnoreCase(input))
                                    return variant;
                            }
                            return null;
                        }

                        @Override
                        public String toString(Axolotl.Variant o, int flags) {
                            return toVariableNameString(o);
                        }

                        @Override
                        public String toVariableNameString(Axolotl.Variant o) {
                            return o.toString().toLowerCase();
                        }
                    })
                    .serializer(new Serializer<Axolotl.Variant>() {
                        @Override
                        public Fields serialize(Axolotl.Variant o) throws NotSerializableException {
                            Fields f = new Fields();
                            f.putObject("variant", o);
                            return f;
                        }

                        @Override
                        public Axolotl.Variant deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
                            return f.getObject("variant", Axolotl.Variant.class);
                        }

                        @Override
                        public void deserialize(Axolotl.Variant o, Fields f) throws StreamCorruptedException, NotSerializableException {
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
}
