package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.game.units.Unit;

public class BattleCalc {
    public static int attack(Unit attacker, Unit target) {
        int out = attacker.getStats().getAttack() + attacker.getWeapon().getDamage();
        out -= target.getStats().getDefence() + target.getArmor().getDefence();
        out *= target.getArmor().getResistance(attacker.getWeapon().type);
        if (out < 0) {
            out = 0;
        }
        return out;
    }

    public static int checkCounterAttack(Unit attacker, Unit target) {
        if (MathUtils.random() < 0.5f) {
            int amount = attack(target, attacker);
            return amount;
        }
        return 0;
    }
}
