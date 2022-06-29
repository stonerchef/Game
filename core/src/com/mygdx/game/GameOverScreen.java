package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameOverScreen implements Screen {

    final MainGame game;

    OrthographicCamera camera;

    Label score;
    private Stage stage;

    public GameOverScreen(MainGame game, Label score) {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.score = score;
        this.score.setBounds(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 125, 250, 250);
        stage = new Stage();


        stage.addActor(score);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0.0f, 0.0f,1);

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
