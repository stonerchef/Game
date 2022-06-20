package com.mygdx.game;

public class Enemy extends Body{

    private float movementSpeed;

    public Enemy(){
        createBody(40, 40, 64, 64);
        creatTextureRegion("enemy.png");
        movementSpeed = 120;
    }

    public void update(Player target, float delta){
        double angle = Math.atan2(target.getY() - getY(), target.getX() - getX());
        double temp = Math.cos(angle) * movementSpeed * delta;


        //if(temp < 0){enemy_img =  new Texture("left_enemy.png");}
        //else {enemy_img =  new Texture("right_enemy.png");}

        setX(getX() + (float)temp);
        setY(getY() + ((float)Math.sin(angle) * movementSpeed * delta));

    }

}
