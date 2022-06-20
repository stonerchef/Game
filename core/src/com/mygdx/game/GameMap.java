package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class GameMap extends Body{
    public Array<Enemy> enemies;
    public Array<Bullet> bullets;
    public Player player;
    public Weapon weapon;
    public GameMap(Player p){
        createBody(0,0, 3000, 2000);
        createTextureRegion("gameMap.png");

        player = p;

        bullets = new Array<>();

        weapon = new Weapon(player, 0.1f);

        enemies = new Array<>();
        Random randInt = new Random();
        for (int i = 0; i < 10; i++){ enemies.add(new Enemy(randInt.nextInt(1000) - 500, randInt.nextInt(1000) - 500));}
    }
    public void update(){
        for (Enemy enemy : enemies){
            enemy.update(player);
        }

        weapon.update(enemies);

        if(enemies.notEmpty() && weapon.is_shoting()){
            bullets.add(new Bullet(weapon.getTarget(enemies), weapon.getX(), weapon.getY(), 1000));
        }
        for (Bullet bullet : bullets){
            bullet.update();
        }
    }
    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);

        player.draw(batch);

        weapon.draw(batch);

        for (Enemy enemy : enemies){
            enemy.draw(batch);
        }

        for (Bullet bullet : bullets){
            bullet.draw(batch);
        }
    }
}
