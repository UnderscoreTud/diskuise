package me.tud.diskuise.elements.entities.ageable;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.AgeableWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.PiglinWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ZoglinWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.ZombieWatcher;

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
        return null;
    }


    public static void setDisguiseAge(FlagWatcher flagWatcher, Age age) {
        if (flagWatcher instanceof AgeableWatcher watcher)
            if (age == Age.ADULT) watcher.setAdult();
            else watcher.setBaby();
        if (flagWatcher instanceof ZombieWatcher watcher)
            if (age == Age.ADULT) watcher.setAdult();
            else watcher.setBaby();
        if (flagWatcher instanceof ZoglinWatcher watcher)
            watcher.setBaby(age == Age.BABY);
        if (flagWatcher instanceof PiglinWatcher watcher)
            watcher.setBaby(age == Age.BABY);
    }

}
