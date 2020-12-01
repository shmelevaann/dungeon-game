package ru.geekbrains.dungeon.game;

import java.util.HashMap;
import java.util.Map;

public class Armor {
    private int defence;
    private Map<Weapon.Type, Float> resistance;

    public Armor(int defence) {
        this.defence = defence;
        resistance = new HashMap<>();
    }


    public Armor tuneResistance(Weapon.Type type, float resistance) {
        this.resistance.put(type, resistance);
        return this;
    }

    public int getDefence() {
        return defence;
    }

    public float getResistance(Weapon.Type type) {
        Float result = resistance.get(type);
        return result != null ? result : 1;
    }
}
