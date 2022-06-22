package com.mygdx.game;

public abstract class Item extends Body{
    public Item() {
        createTextureRegion("item.png");
        createBody(0, 0, 32, 32);
    }
    public Item(float x,  float y, String name) {
        createTextureRegion(name);
        createBody(x, y, 32, 32);
    }
    public void addVal(Player player){}
}
