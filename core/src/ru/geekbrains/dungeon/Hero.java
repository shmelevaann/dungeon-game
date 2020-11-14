package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Hero {
    private final GameMap gameMap;
    private final ProjectileController projectileController;
    private final Vector2 position;
    private final Vector2 lastMoveDirection;
    private final TextureRegion texture;

    private final int minFireMode;
    private final int maxFireMode;
    private int currentFireMode;
    private final float fireSpreadAngle;

    private final float speed;
    private final float projectileSpeed;

    private final Pool<Vector2> vector2Pool = Pools.get(Vector2.class);
    private final float width;
    private final float height;
    private final float scale;

    public Hero(TextureAtlas atlas, ProjectileController projectileController, GameMap gameMap) {
        this.gameMap = gameMap;
        this.position = new Vector2(100, 100);
        this.texture = atlas.findRegion("tank");
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.scale = 3.0f;
        this.projectileController = projectileController;
        this.speed = 200;
        this.maxFireMode = 10;
        this.minFireMode = 1;
        this.currentFireMode = minFireMode;
        this.projectileSpeed = 600;
        this.fireSpreadAngle = 90;
        this.lastMoveDirection = new Vector2(0, 1);
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            if (currentFireMode == maxFireMode) {
                currentFireMode = minFireMode;
            } else {
                currentFireMode++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Vector2 projectile = vector2Pool.obtain();
            float offset = fireSpreadAngle / currentFireMode;

            for (int i = 0; i < currentFireMode; i++) {
                projectile.set(lastMoveDirection).nor().rotateDeg(i * offset - (currentFireMode - 1) * offset / 2).scl(projectileSpeed);
                projectileController.activate(position.x, position.y, projectile.x, projectile.y);
            }
            vector2Pool.free(projectile);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            lastMoveDirection.set(0, 1);
            updatePosition(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            lastMoveDirection.set(0, -1);
            updatePosition(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            lastMoveDirection.set(-1, 0);
            updatePosition(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            lastMoveDirection.set(1, 0);
            updatePosition(dt);
        }

    }

    private void updatePosition(float dt) {
        Vector2 newPosition = vector2Pool.obtain();
        Vector2 edge = vector2Pool.obtain();
        newPosition.set(position).mulAdd(lastMoveDirection, speed * dt);
        if (gameMap.isAllowPosition(edge.set(newPosition).mulAdd(lastMoveDirection, height * scale / 2))) {
            position.set(newPosition);
        }
        vector2Pool.free(newPosition);
        vector2Pool.free(edge);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, width, height,
                scale, scale, lastMoveDirection.angleDeg());
    }
}
