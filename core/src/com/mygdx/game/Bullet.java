package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Bullet extends Body{

    private float speed;
    private float velocityX;
    private float velocityY;
    private Enemy target;
    private Weapon weapon;

    public Bullet(Enemy target, float speed, Weapon weapon){

        this.target = target;
        this.weapon = weapon;
        createBody(weapon.getX(), weapon.getY(), 32,32);
        createTextureRegion("bullet.png");
        this.speed = speed;
        setAngle(weapon.getAngle());
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

        float random = getRandomFloatBetweenAB(-0.2f, 0.2f);

        velocityX += random;
        velocityY -= random;

    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        setX(getX() + (velocityX * speed * delta));
        setY(getY() + (velocityY * speed * delta));
    }


}
