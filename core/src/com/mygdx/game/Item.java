package com.mygdx.game;

public class Item extends Body{
    public Item(float x,  float y, String name) {
        createTextureRegion(name);
        createBody(x, y, 32, 32);
    }


}
