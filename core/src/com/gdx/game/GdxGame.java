package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GdxGame extends ApplicationAdapter {
    static Population population;
    static List<Obstacle> obstacles;
    static Dot goal;
    static ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    BitmapFont font;
    InputHandler inputHandler;

    static void resetPopulation() {
        population = null;
        population = new Population(1000, new Vector2(Gdx.graphics.getWidth() / 2, 50));
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        inputHandler = new InputHandler();
        obstacles = new ArrayList<>();

        population = new Population(1000, new Vector2(Gdx.graphics.getWidth() / 2, 50));
        goal = new Dot(new Vector2(Gdx.graphics.getWidth() / 2, 650), Color.RED);

        font = new BitmapFont();
        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(inputHandler);


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (InputHandler.isDrawing) {
            shapeRenderer.rect(InputHandler.rectStartX, InputHandler.rectStartY, InputHandler.rectWidth, InputHandler.rectHeight);
        }

        for (Obstacle obstacle : obstacles)
            obstacle.draw();

        population.render();
        population.update();
        goal.render();

        shapeRenderer.end();

        if (population.allDotsDead()) {
            population.calculateFitness();
            population.naturalSelection();
            population.mutateBabies();
        }

        //KEYBOARD
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            resetPopulation();
            obstacles.clear();
        }


        batch.begin();
        //HUD
        font.draw(batch, "Generation: " + population.generation, 5, 685);
        font.draw(batch, "Min steps: " + population.minStep, 5, 665);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //shapeRenderer.dispose();
        font.dispose();
    }
}
