package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.lang.Math;

public class Weapon extends Body{
    private Player player;
    private Enemy target;
    private float angle;
    public Weapon(Player p){
        player = p;
        createBody(player.getX(), player.getX(), 64, 128);
        createTextureRegion("sword.png");
        target = new Enemy();
    }
    public Enemy getTarget(Array<Enemy> Targets){
        double val_min = Double.MAX_VALUE;
        int index_min = 0;
        int i = 0;
        for (Enemy enemy : Targets){
            double temp = Math.pow(enemy.getX() - player.getX(), 2) +
                    Math.pow(enemy.getY() - player.getY(), 2);
            if(temp < val_min){
                val_min = temp;
                index_min = i;
            }
            i++;
        }
        return Targets.get(index_min);
    }
    private float calculateAngle(){
        double angleTemp = Math.atan2(target.getY() - player.getY(), target.getX() - player.getX());
        return (float) Math.toDegrees(angleTemp);
    }
    public void update(Array<Enemy> Targets){
        setX(player.getX()+ (float)1.2 * player.getWidth());
        setY(player.getY() + player.getHeight() / 2);
        if(Targets.notEmpty()) {
            target = getTarget(Targets);
            angle = calculateAngle();
        }
    }
    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch, getX(), getY(), getWidth() / 2, getHeight() / 2  ,
                getWidth(), getHeight(), 1, 1, calculateAngle());
    }
}
