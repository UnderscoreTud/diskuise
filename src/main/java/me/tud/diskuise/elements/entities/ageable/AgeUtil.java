package me.tud.diskuise.elements.entities.ageable;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.*;

public class AgeUtil {

    public static Age getDisguiseAge(FlagWatcher flagWatcher) {
        if (flagWatcher instanceof AgeableWatcher watcher)
            return (watcher.isBaby() ? Age.BABY : Age.ADULT);
        if (flagWatcher instanceof ZombieWatcher watcher)
            return (watcher.isBaby() ? Age.BABY : Age.ADULT);
        if (flagWatcher instanceof ZoglinWatcher watcher)
            return (watcher.isBaby() ? Age.BABY : Age.ADULT);
        if (flagWatcher instanceof PiglinWatcher watcher)
            return (watcher.isBaby() ? Age.BABY : Age.ADULT);
        if (flagWatcher instanceof ArmorStandWatcher watcher)
            return (watcher.isSmall() ? Age.BABY : Age.ADULT);
        return null;
    }


    public static void setDisguiseAge(FlagWatcher flagWatcher, Age age) {
        if (flagWatcher instanceof AgeableWatcher watcher)
            if (age == Age.ADULT) watcher.setAdult();
            else watcher.setBaby();
        else if (flagWatcher instanceof ZombieWatcher watcher)
            if (age == Age.ADULT) watcher.setAdult();
            else watcher.setBaby();
        else if (flagWatcher instanceof ZoglinWatcher watcher)
            watcher.setBaby(age == Age.BABY);
        else if (flagWatcher instanceof PiglinWatcher watcher)
            watcher.setBaby(age == Age.BABY);
        else if (flagWatcher instanceof ArmorStandWatcher watcher)
            watcher.setSmall(age == Age.BABY);
    }

}
