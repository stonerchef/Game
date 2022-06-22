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
    GameMap map;

    public Wave(GameMap map){
        //enemies = new Array<Enemy>();
        this.map = map;
        number = 1;
        timer = 0;
        subTimer = 3;
        time = 90;

    }
    public void update(Array<Enemy> enemies){
        float delta = Gdx.graphics.getDeltaTime();
        timer += delta;
        subTimer += delta;

        if (timer >= time) endOfWave();
        if (subTimer >= 2){
            subTimer = 0;
            spawnEnemies();
        }
    }
    public void spawnEnemies(){
        int a = 4 + number * number / 4;
        int b = 8 + number * number / 4;
        Random random = new Random();
        int n = a + random.nextInt() % b;
        for(int i = 0; i < n; i++) map.enemies.add(new Enemy());
    }
    public void endOfWave(){
        number += 1;
    }

   // public Array<Enemy> getEnemies() {
   //     return enemies;
   // }
}
