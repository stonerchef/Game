package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

public class Bullet extends Body{

    private float speed;
    private float velocityX;
    private float velocityY;
    private Enemy target;

    public Bullet(Weapon weapon, Array<Enemy> targets){

        this.target = weapon.getTarget(targets);
        createBody(weapon.getX(), getY(), 8,8);
        creatTextureRegion("bullet.png");
        speed = 100;
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

    public void update(float delta){
        setX(getX() + (velocityX * speed * delta));
        setY(getY() + (velocityY * speed * delta));
    }

}
