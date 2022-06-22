package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player extends Body{

    private float movementSpeed;
    public float hp;
    public int hpMax;


    public Player(){
        createBody(getMapSizeWidth() / 2, getMapSizeHeight() / 2, 48, 48);
        createTextureRegion("player.png");
        movementSpeed = 240;
        hpMax = 100;
        hp = hpMax;
    }

    public void moveLeft(OrthographicCamera camera, float delta){
        if(getX() > 1) setX(getX() - delta * movementSpeed);
        camera.position.x = getX();
    }

    public void moveRight(OrthographicCamera camera, float delta){
        if(getX() + getWidth() < getMapSizeWidth() - 1) setX(getX() + delta * movementSpeed);
        camera.position.x = getX();
    }

    public void moveUp(OrthographicCamera camera, float delta){
        if(getY() + getHeight() < getMapSizeHeight() - 1)setY(getY() + delta * movementSpeed);
        camera.position.y = getY();
    }

    public void moveDown(OrthographicCamera camera, float delta){
        if(getY() > 1)setY(getY() - delta * movementSpeed);
        camera.position.y = getY();
    }

    public void increaseMovementSpeed(float speed){

        this.movementSpeed += speed;

    }

    public void decreaseMovementSpeed(float speed){

        this.movementSpeed -= speed;

    }

}
