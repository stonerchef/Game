package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Weapon extends Body{

    private Player player;

    public Weapon(Player player){
        createBody(0, 0, 16, 32);
        creatTextureRegion("weapon.png");
        this.player = player;
    }

    public Enemy getTarget(Array<Enemy> targets){
        double minVal = Double.MAX_VALUE;
        int minIndex = 0;
        int i = 0;
        double temp;

        for(Enemy enemy : targets){
            temp = Math.pow((double)enemy.getX() - (double)player.getX(), 2) + Math.pow((double)enemy.getY() - (double)player.getY(), 2);
            if(temp < minVal){
                minVal = temp;
                minIndex = i;
            }
            i++;
        }

        return targets.get(minIndex);

    }

    private float CalculateAngle(Enemy target){

        double angleTemp = Math.atan2(target.getY() - player.getY(), target.getX() - player.getX());
        return (float) Math.toDegrees(angleTemp);

    }

    public void update(Array<Enemy> targets){
        if(targets.notEmpty())
        setAngle(CalculateAngle(getTarget(targets)));

        setX(player.getX() + (1.5f * player.getWidth()));
        setY(player.getY() + (player.getHeight() / 2));
    }

}
