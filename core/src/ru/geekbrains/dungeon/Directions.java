package ru.geekbrains.dungeon;

import com.badlogic.gdx.math.MathUtils;

public enum Directions {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int offsetX;
    private final int offsetY;

    Directions(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getOffsetX(){
        return offsetX;
    }

    public int getOffsetY(){
        return offsetY;
    }

    public static Directions getRandomDirection() {
        return Directions.values()[MathUtils.random.nextInt(4)];
    }
}
