package me.tud.diskuise.utils;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.util.StringUtil;

import java.util.*;

public class DisguiseMap {

    private final Map<Disguise, Collection<Entity>> map = new HashMap<>();

    public void put(Disguise key, Entity value) {
        map.computeIfAbsent(key, k -> new ArrayList<>());
        map.get(key).add(value);
    }

    public void putIfAbsent(Disguise key, Entity value) {
        map.computeIfAbsent(key, k -> new ArrayList<>());
        if (!map.get(key).contains(value)) map.get(key).add(value);
    }

    public Collection<Entity> get(Disguise key) {
        return map.get(key);
    }

    public Set<Disguise> keySet() {
        return map.keySet();
    }

    public Set<Map.Entry<Disguise, Collection<Entity>>> entrySet() {
        return map.entrySet();
    }

    public Collection<Collection<Entity>> values() {
        return map.values();
    }

    public boolean containsKey(Disguise key) {
        return map.containsKey(key);
    }

    public void remove(Disguise key) {
        map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public boolean remove(Disguise key, Entity value) {
        if (map.get(key) == null) return false;
        return map.get(key).remove(value);
    }

    public void removeAll(Entity value) {
        for (Disguise key : map.keySet()) {
            map.get(key).remove(value);
        }
    }

    public boolean replace(Disguise key, Entity oldValue, Entity newValue) {
        if (map.get(key) == null) return false;
        if (!map.get(key).remove(oldValue)) return false;
        return map.get(key).add(newValue);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}