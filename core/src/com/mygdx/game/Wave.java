package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import java.util.Random;

public class Wave {
    //public Array<Enemy> enemies;
    int number;
    float timer;
    float subTimer;
    float time;

    public Wave(){
        //enemies = new Array<Enemy>();
        number = 1;
        timer = 0;
        subTimer = 0;
        time = 90;

    }
    public void update(Array<Enemy> enemies){
        float delta = Gdx.graphics.getDeltaTime();
        timer += delta;
        subTimer += delta;

        //if (timer >= time) endOfWave();
        if (subTimer >= 5){
            subTimer = 0;
            spawnEnemies(enemies);
        }
    }
    public static void spawnEnemies(Array<Enemy> enemies){
        //int a = 4 + number * number / 2;
        //int b = 8 + number * number / 2;
        //Random random = new Random();
        //int n = a + random.nextInt() * (b - a);
        for(int i = 0; i < 10; i++) enemies.add(new Enemy());
    }
    public void endOfWave(){
        number += 1;
    }

   // public Array<Enemy> getEnemies() {
   //     return enemies;
   // }
}
