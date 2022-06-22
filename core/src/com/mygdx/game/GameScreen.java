package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    final MainGame game;

    OrthographicCamera camera;

    private Stage stage;

    private Player player;
    private HpBar hpBar;
    //private Weapon weapon;
    private GameMap gameMap;

    public enum State{
        PAUSE,
        RUN,
        STOPPED
    }

    private State state = State.RUN;

    public GameScreen(MainGame game) {
        this.game = game;

        player = new Player();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = player.getX();
        camera.position.y = player.getY();

        stage = new Stage();

        hpBar = new HpBar(player);


        stage.addActor(hpBar);

        //weapon = new Weapon(player, 0.5f);

        gameMap = new GameMap(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        switch (state){
            case RUN:
                update(delta);
                draw();
                break;
            case PAUSE:
                pause();
                break;
            case STOPPED:
                Gdx.app.exit();
                break;
            default:
                break;
        }

    }

    public void update(float delta){
        ScreenUtils.clear(0, 0, 0.25f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        gameMap.update();
        //weapon.update(gameMap.enemies);

        hpBar.update();

        inputHandler(delta);
    }

    public void draw(){
        gameMap.draw(game.batch);

        //weapon.draw(game.batch);

        stage.draw();
        stage.act();
    }

    public void setGameState(State state){
        this.state = state;
    }

    public void inputHandler(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveLeft(camera, delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight(camera, delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.moveUp(camera, delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown(camera, delta);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            setGameState(State.PAUSE);
        }
    }

    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        setGameState(State.PAUSE);
        game.setScreen(new PauseScreen(game, this));
    }

    @Override
    public void resume() {
        setGameState(State.RUN);
        game.setScreen(this);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
