package com.gdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Brain {
    List<Vector2> directions;
    int step;
    int size;

    public Brain(int size) {
        directions = new ArrayList<>();
        this.size = size;
        randomize();
    }

    private void randomize() {

        for (int i = 0; i < size; i++) {
            int randX = Math.round((float) Math.random());
            if (Math.random() < 0.5) randX *= (-1);
            int randY = Math.round((float) Math.random());
            if (Math.random() < 0.5) randY *= (-1);
            directions.add(new Vector2(randX, randY));
        }
    }

    Brain cloneBrain() {
        Brain clone = new Brain(size);
        clone.directions.addAll(directions);

        return clone;
    }

    void mutate() {
        double mutationRate = 0.01;
        for (int i = 0; i < directions.size(); i++) {
            double rand = Math.random();
            if (rand < mutationRate) {
                int randX = Math.round((float) Math.random());
                if (Math.random() < 0.5) randX *= (-1);
                int randY = Math.round((float) Math.random());
                if (Math.random() < 0.5) randY *= (-1);
                directions.set(i, new Vector2(randX, randY));

            }
        }

    }

}
