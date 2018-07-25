package com.gdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {
    Vector2 pos;
    Vector2 bounds;

    public Obstacle(Vector2 pos, Vector2 bounds) {
        this.pos = pos;
        this.bounds = bounds;
    }

    public void draw() {

        GdxGame.shapeRenderer.setColor(Color.BLUE);
        GdxGame.shapeRenderer.rect(pos.x, pos.y, bounds.x, bounds.y);
    }
}
