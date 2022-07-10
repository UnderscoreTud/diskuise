package me.tud.diskuise.util.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import me.tud.diskuise.Diskuise;

public class ClassUtils {

    public static <T> void registerClass(ClassInfo<T> classInfo) {
        if (Classes.getClassInfoNoError(classInfo.getCodeName()) != null) {
            Diskuise.getInstance().getLogger().severe("Could not register classinfo: " + classInfo);
            Diskuise.getInstance().getLogger().severe("Code name already used");
            return;
        }
        if (Classes.getExactClassInfo(classInfo.getC()) != null) {
            Diskuise.getInstance().getLogger().severe("Could not register classinfo: " + classInfo);
            Diskuise.getInstance().getLogger().severe("Class already registered");
            return;
        }
        Classes.registerClass(classInfo);
    }

}
