package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Wave {
    int waveNumber;
    float waveTimer;
    float waveTime;
    int subWaves;
    int lastSubWave;
    int enemiesForSubWave;
    float timeBetweenSubWaves;

    public Wave( int waveNum, float timer, float Time, int sub_waves, int enemiesForSub_wave){
        waveNumber = waveNum;
        waveTimer = timer;
        waveTime = Time;
        subWaves = sub_waves;
        enemiesForSubWave = enemiesForSub_wave;
        lastSubWave = 0;
        timeBetweenSubWaves = waveTime / subWaves;
    }
    public void update(Array<Enemy> Enemies){
        waveTimer += Gdx.graphics.getDeltaTime();
        if (waveTimer >= waveTime) {endOfWave();}
        else if (waveTimer - lastSubWave >= timeBetweenSubWaves){spawnEnemies(Enemies);}
    }
    public void spawnEnemies(Array<Enemy> Enemies){
        for(int i = 0; i < enemiesForSubWave; i++) Enemies.add(new Enemy());
    }
    public void endOfWave(){
        //do something
    }
}
