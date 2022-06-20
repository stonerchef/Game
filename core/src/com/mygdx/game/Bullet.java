package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Bullet extends Body{

    private float speed;
    private float velocityX;
    private float velocityY;
    private Enemy target;

    public Bullet(Enemy target, float x, float y, float speeed){

        this.target = target;
        createBody(x, y, 8,8);
        createTextureRegion("bullet.png");
        speed = speeed;
        Direction();
    }

    private void Direction(){
        float movementTotal = 1;
        float distanceX = Math.abs(target.getX() - getX());
        float distanceY = Math.abs(target.getY() - getY());
        float distanceTotal = distanceX + distanceY;

        float movementPrecentX = distanceX / distanceTotal;
        float movementPrecentY = movementTotal - movementPrecentX;

        velocityX = movementPrecentX;
        velocityY = movementPrecentY;

        if(target.getX() < getX()) velocityX *= -1;
        if(target.getY() < getY()) velocityY *= -1;
    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        setX(getX() + (velocityX * speed * delta));
        setY(getY() + (velocityY * speed * delta));
    }

}
