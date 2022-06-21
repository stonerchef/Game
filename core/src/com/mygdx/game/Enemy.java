package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;


public class Enemy extends Body{

    private float movementSpeedPreSec;
    private float hp;

    public Enemy() {
        float x = getRandomFloatBetweenAB(0, getMapSizeWidth() - 64);
        float y = getRandomFloatBetweenAB(0, getMapSizeHeight() - 64);
        createBody(x, y, 64, 64);
        movementSpeedPreSec = 60;
        createTextureRegion("player1.png");
        hp = 100;
    }

    public void update(Player target){
        float delta = Gdx.graphics.getDeltaTime();
        double angle = Math.atan2(target.getY() - this.getY(), target.getX() - this.getX());
        double temp = Math.cos(angle) * movementSpeedPreSec * delta;

        //if(temp < 0){enemy_img =  new Texture("left_enemy.png");}
        //else {enemy_img =  new Texture("right_enemy.png");}
        // = new Texture("hit_boxenemy.png");

        this.setX(this.getX() + (float)temp);
        this.setY(this.getY() + (float)(Math.sin(angle) * movementSpeedPreSec * delta));
    }

    public float getHp(){
        return hp;
    }

    public void setHp(float hp){
        this.hp = hp;
    }

    public boolean isDead(){
        return hp <= 0;
    }

}
