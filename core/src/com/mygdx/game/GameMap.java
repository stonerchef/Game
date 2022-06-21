package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.Random;

public class GameMap {
    public Array<Enemy> enemies;
    public Array<Bullet> bullets;
    public Player player;
    public Weapon weapon;
    private TextureRegion textureRegion;
    private final float mapSizeWidth = 2280;
    private final float mapSizeHeight = 1528;
    private final Rectangle border;
    public GameMap(Player p){
        createTextureRegion("background1.png");
        border = new Rectangle(0,0, mapSizeWidth, mapSizeHeight);

        player = p;

        bullets = new Array<>();

        weapon = new Weapon(player, 0.1f);

        enemies = new Array<>();
        Random randInt = new Random();
        for (int i = 0; i < 10; i++){ enemies.add(new Enemy());}
    }
    public void update(){

        checkHit();

        for (Enemy enemy : enemies){
            enemy.update(player);
        }

        weapon.update(enemies);

        if(enemies.notEmpty() && weapon.isShooting()){
            bullets.add(new Bullet(weapon.getTarget(enemies), 1000, weapon));
        }
        for (Iterator<Bullet> iterBullet = bullets.iterator(); iterBullet.hasNext();){
            Bullet bullet = iterBullet.next();
            if(bullet.getBody().overlaps(border)) bullet.update();
            else iterBullet.remove();
        }
    }

    public void checkHit(){
        for(Iterator<Bullet> iterBullet = bullets.iterator(); iterBullet.hasNext();){
            Bullet bullet = iterBullet.next();
            for(Iterator<Enemy> iterEnemy = enemies.iterator(); iterEnemy.hasNext();){
                Enemy enemy = iterEnemy.next();
                if(bullet.getBody().overlaps(enemy.getBody()))
                {
                    enemy.setHp(enemy.getHp() - bullet.getDamage());
                    iterBullet.remove();

                    if(enemy.isDead()){
                        iterEnemy.remove();
                    }
                }
            }
        }
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(textureRegion, 0, 0);
        batch.end();

        player.draw(batch);

        weapon.draw(batch, weapon.getX(), weapon.getY(), weapon.getWidth() / 2, weapon.getHeight() / 2, weapon.getWidth(), weapon.getHeight(), 1, 1, weapon.getAngle());

        for (Enemy enemy : enemies){
            enemy.draw(batch);
        }

        for (Bullet bullet : bullets){
            bullet.draw(batch, bullet.getX(), bullet.getY(), bullet.getWidth() / 2, bullet.getHeight() / 2, bullet.getWidth(), bullet.getHeight(), 2,2, bullet.getAngle());
        }
    }

    public float getMapSizeWidth(){
        return mapSizeWidth;
    }

    public float getMapSizeHeight(){
        return mapSizeHeight;
    }

    public void createTextureRegion(String imagePath){
        textureRegion = new TextureRegion(new Texture(imagePath));
    }
}
