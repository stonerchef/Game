package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player extends Body{

    private float movementSpeed;
    int a;

    public Player(){
        createBody(0, 0, 48, 48);
        creatTextureRegion("player.png");
        movementSpeed = 240;
    }

    public void moveLeft(OrthographicCamera camera, float delta){
        setX(getX() - delta * movementSpeed);
        camera.position.x = getX();
    }

    public void moveRight(OrthographicCamera camera, float delta){
        setX(getX() + delta * movementSpeed);
        camera.position.x = getX();
    }

    public void moveUp(OrthographicCamera camera, float delta){
        setY(getY() + delta * movementSpeed);
        camera.position.y = getY();
    }

    public void moveDown(OrthographicCamera camera, float delta){
        setY(getY() - delta * movementSpeed);
        camera.position.y = getY();
    }

    public void increaseMovementSpeed(float speed){

        this.movementSpeed += speed;

    }

    public void decreaseMovementSpeed(float speed){

        this.movementSpeed -= speed;

    }

}
