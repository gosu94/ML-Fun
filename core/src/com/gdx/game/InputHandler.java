package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {

    static boolean isDrawing = false;
    static int rectStartX;
    static int rectStartY;
    static int rectWidth;
    static int rectHeight;
    boolean once = false;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!once && button == Input.Buttons.LEFT) {
            rectStartX = screenX;
            rectStartY = Gdx.graphics.getHeight() - screenY;
            rectWidth = 0;
            rectHeight = 0;
            isDrawing = true;
            once = true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (button == Input.Buttons.LEFT) {
            once = false;
            isDrawing = false;
            GdxGame.obstacles.add(new Obstacle(new Vector2(rectStartX, rectStartY), new Vector2(rectWidth, rectHeight)));
            GdxGame.resetPopulation();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (isDrawing) {

            rectWidth = screenX - rectStartX;
            rectHeight = Gdx.graphics.getHeight() - screenY - rectStartY;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
