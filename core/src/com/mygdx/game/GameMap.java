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
    private Array<Item> items;
    public Player player;
    public Weapon weapon;
    private Bullet bullet;
    private Item item;
    private Enemy enemy;
    private Wave wave;
    private TextureRegion textureRegion;
    private final float mapSizeWidth = 2280;
    private final float mapSizeHeight = 1528;
    private final Rectangle border;
    public GameMap(Player player){
        createTextureRegion("background1.png");
        border = new Rectangle(0,0, mapSizeWidth, mapSizeHeight);

        wave = new Wave(this);

        this.player = player;

        bullets = new Array<>();

        weapon = new Weapon(player, 0.1f);

        enemies = new Array<>();

        items = new Array<>();

    }
    public void update(){
        wave.update(enemies);

        checkHit();
        playerHit();
        collectItems();

        for (Enemy enemy : enemies){
            enemy.update(player);
        }

        weapon.update(enemies);

        if(movingEnemies() > 0 && weapon.isShooting()){
            bullets.add(new Bullet(weapon.getTarget(enemies), 1000, weapon));
        }

        for (Iterator<Bullet> iterBullet = bullets.iterator(); iterBullet.hasNext();){
            bullet = iterBullet.next();
            if(bullet.getBody().overlaps(border)) bullet.update();
            else iterBullet.remove();
        }
    }
    public int movingEnemies(){
        if(enemies.isEmpty()) return 0;
        int num = 0;
        for(Enemy enemy : enemies){
            if(!enemy.isSpawning) num+=1;
        }
        return num;
    }

    public void checkHit(){
        for(Iterator<Bullet> iterBullet = bullets.iterator(); iterBullet.hasNext();){
             bullet = iterBullet.next();
            for(Iterator<Enemy> iterEnemy = enemies.iterator(); iterEnemy.hasNext();){
                enemy = iterEnemy.next();
                if(bullet.getBody().overlaps(enemy.getBody()))
                {
                    enemy.setHp(enemy.getHp() - bullet.getDamage());
                    iterBullet.remove();

                    if(enemy.isDead()){
                        items.add(new Item(enemy.getX(), enemy.getY(), "exp.png"));
                        iterEnemy.remove();
                    }
                    break;
                }
            }
        }
    }

    public void playerHit(){
        for(Enemy enemy : enemies){
            if(enemy.getBody().overlaps(player.getBody())) player.hp -= 0.5f;
        }
    }

    public void collectItems(){
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();){
            item = iterator.next();
            if(item.getBody().overlaps(player.getBody())){
                iterator.remove();
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

        for(Item item : items){
            item.draw(batch);
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
