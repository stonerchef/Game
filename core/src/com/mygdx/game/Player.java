package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Player extends Body{

    private float movementSpeed;
    private float hp;
    private int hpMax;
    private float experience;

    public Player(){
        createBody(getMapSizeWidth() / 2, getMapSizeHeight() / 2, 48, 48);
        createTextureRegion("left_player.png");
        movementSpeed = 240;
        hpMax = 100;
        hp = hpMax;
        experience = 0;
    }

    public void moveLeft(OrthographicCamera camera, float delta){
        setTextureRegion("left_player.png");
        if(getX() > 1) setX(getX() - delta * movementSpeed);
        camera.position.x = getX();
    }

    public void moveRight(OrthographicCamera camera, float delta){
        setTextureRegion("right_player.png");
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

    public float getExperience(){
        return experience;
    }

    public void setExperience(float val){
        experience = val;
    }

    public float getHp(){
        return hp;
    }

    public void setHp(float val){
        hp = val;
    }

    public int getHpMax(){
        return hpMax;
    }

    public void setHpMax(int val){
        hpMax = val;
    }

    public void increaseMovementSpeed(float speed){

        this.movementSpeed += speed;

    }

    public void decreaseMovementSpeed(float speed){

        this.movementSpeed -= speed;

    }

}
