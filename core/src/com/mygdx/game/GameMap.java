package com.mygdx.game;

public class GameMap extends Body{
    public GameMap(){
        createBody(0,0, 3000, 2000);
        creatTextureRegion("gameMap.png");
    }
}
