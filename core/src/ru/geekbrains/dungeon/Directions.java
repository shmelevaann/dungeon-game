package ru.geekbrains.dungeon;

import com.badlogic.gdx.math.MathUtils;

public enum Directions {
    UP {
        public int getOffsetX() {
            return  0;
        }
        public int getOffsetY() {
            return 1;
        }
    },
    DOWN {
        public int getOffsetX() {
            return  0;
        }
        public int getOffsetY() {
            return -1;
        }
    },
    LEFT {
        public int getOffsetX() {
            return  -1;
        }
        public int getOffsetY() {
            return 0;
        }
    },
    RIGHT {
        public int getOffsetX() {
            return  1;
        }
        public int getOffsetY() {
            return 0;
        }
    };

    public abstract int getOffsetX();
    public abstract int getOffsetY();

    public static Directions getRandomDirection(){
        switch (MathUtils.random.nextInt(4)) {
            case 0: return Directions.UP;
            case 1: return Directions.DOWN;
            case 2: return Directions.LEFT;
            default: return Directions.RIGHT;
        }
    }
}
