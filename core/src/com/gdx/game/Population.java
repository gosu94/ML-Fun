package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Population {
    List<Dot> dots;
    int size;
    double fitnessSum;
    int generation;
    int bestDot;
    int minStep;

    public Population(int size, Vector2 startingPoint) {
        dots = new ArrayList<>();
        this.size = size;
        fitnessSum = 0;
        generation = 1;
        minStep = 1000;
        for (int i = 0; i < size; i++)
            dots.add(new Dot(new Vector2(Gdx.graphics.getWidth() / 2, 50)));

    }

    public void render() {
        for (int i = 1; i < dots.size(); i++)
            dots.get(i).render();
        dots.get(0).render();

    }

    public void update() {
        for (int i = 0; i < dots.size(); i++) {
            if (dots.get(i).brain.step > minStep)
                dots.get(i).alive = false;
            else
                dots.get(i).update();
        }
    }

    void calculateFitness() {
        for (int i = 0; i < dots.size(); i++)
            dots.get(i).calculateFitness();
    }

    boolean allDotsDead() {
        for (int i = 0; i < dots.size(); i++) {
            if (dots.get(i).alive && !dots.get(i).reachedGoal) return false;
        }
        return true;
    }

    void naturalSelection() {
        List<Dot> newDots = new ArrayList<>();
        setBestDot();
        newDots.add(dots.get(bestDot).giveBaby());
        newDots.get(0).isBest = true;
        calculateFitnessSum();
        for (int i = 1; i < dots.size(); i++) {
            Dot parent = getParent();
            newDots.add(parent.giveBaby());
        }

        dots.clear();
        dots.addAll(newDots);
        generation++;
    }

    void mutateBabies() {
        for (int i = 1; i < dots.size(); i++) {
            dots.get(i).brain.mutate();
        }
    }

    void calculateFitnessSum() {
        fitnessSum = 0;
        for (int i = 0; i < dots.size(); i++) {
            fitnessSum += dots.get(i).fitness;
        }
    }

    Dot getParent() {
        double rand = Math.random() * fitnessSum;
        double runningSum = 0;

        for (int i = 0; i < dots.size(); i++) {
            runningSum += dots.get(i).fitness;
            if (runningSum > rand) {
                return dots.get(i);
            }
        }

        return null;
    }

    void setBestDot() {
        double maxFitness = 0;
        int maxIndex = 0;
        for (int i = 0; i < dots.size(); i++) {
            if (dots.get(i).fitness > maxFitness) {
                maxFitness = dots.get(i).fitness;
                maxIndex = i;
            }
        }

        bestDot = maxIndex;
        if (dots.get(bestDot).reachedGoal) {
            minStep = dots.get(bestDot).brain.step;
            System.out.println("generation: " + generation + " step: " + minStep);
        }
    }


}
