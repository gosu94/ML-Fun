package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.gdx.game.GdxGame.goal;

public class Dot {
    Vector2 pos;
    Vector2 vel;
    Brain brain;
    boolean alive;
    double fitness;
    boolean reachedGoal;
    boolean isBest;
    Rectangle r1;
    Rectangle r2;
    private Vector2 acc;
    private Color color;

    public Dot(Vector2 pos) {

        this.brain = new Brain(500);
        alive = true;
        this.pos = pos;
        this.vel = new Vector2(0, 0);
        this.acc = new Vector2(0, 0);
        this.color = Color.BLACK;
        isBest = false;
        reachedGoal = false;
        r1 = new Rectangle();
        r2 = new Rectangle();
    }

    public Dot(Vector2 pos, Color color) {
        this.brain = new Brain(400);
        alive = true;
        this.pos = pos;
        this.vel = new Vector2(0, 0);
        this.acc = new Vector2(0, 0);
        this.color = color;
        isBest = false;
        reachedGoal = false;
        r1 = new Rectangle();
        r2 = new Rectangle();
    }

    public void render() {
        if (isBest)
            GdxGame.shapeRenderer.setColor(Color.GREEN);
        else
            GdxGame.shapeRenderer.setColor(color);
        GdxGame.shapeRenderer.circle(pos.x, pos.y, 5);
    }

    public void move() {
        if (brain.step < brain.directions.size()) {
            acc = brain.directions.get(brain.step);
            brain.step++;
        }
        vel.add(acc);
        vel.limit(5);
        pos.add(vel);
    }

    public void update() {
        if (pos.x > Gdx.graphics.getWidth() - 3 || pos.x < 3
                || pos.y > Gdx.graphics.getHeight() - 3 || pos.y < 3)
            alive = false;

        for (Obstacle obstacle : GdxGame.obstacles) {

            r1.set(pos.x, pos.y, 5, 5);
            r2 = getfixPos(obstacle);

            if (r1.overlaps(r2))
                alive = false;

        }

        if (distance(this.pos, goal.pos) < 5) reachedGoal = true;

        if (alive && !reachedGoal) {
            move();
        }
    }

    void calculateFitness() {
        double dist = distance(this.pos, goal.pos);
        if (reachedGoal) {
            fitness = 100000 / brain.step;
        } else
            fitness = 1 / (dist * dist);
    }

    Dot giveBaby() {
        Dot baby = new Dot(new Vector2(Gdx.graphics.getWidth() / 2, 50));
        baby.brain.directions.clear();
        baby.brain.directions.addAll(brain.directions);
        return baby;
    }

    double distance(Vector2 object1, Vector2 object2) {
        return Math.sqrt(Math.pow((object2.x - object1.x), 2) + Math.pow((object2.y - object1.y), 2));
    }

    Rectangle getfixPos(Obstacle obstacle) {

        Rectangle rec = new Rectangle();

        float fixX;
        float fixY;
        float fixW;
        float fixH;
        if (obstacle.bounds.x < 0) {

            fixX = obstacle.pos.x + obstacle.bounds.x;
            fixW = (-1) * obstacle.bounds.x;
        } else {

            fixX = obstacle.pos.x;
            fixW = obstacle.bounds.x;
        }
        if (obstacle.bounds.y < 0) {
            fixY = obstacle.pos.y + obstacle.bounds.y;
            fixH = (-1) * obstacle.bounds.y;
        } else {
            fixY = obstacle.pos.y;
            fixH = obstacle.bounds.y;
        }
        rec.set(fixX, fixY, fixW, fixH);

        return rec;
    }

}
