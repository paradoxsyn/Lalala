package com.mygdx.game.champs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.LosuGame;
import com.mygdx.game.resources.Assets;

import org.w3c.dom.css.Rect;

/**
 * Created by Paradox on 2/26/2017.
 */

public class TahmK {
    private final String TAG = getClass().getSimpleName();

    private Vector3 position;
    private Vector3 velocity;
    private Vector2 distanceT;
    private Vector2 distanceA;
    private float distancebet;

    LosuGame game;

    private com.badlogic.gdx.scenes.scene2d.ui.Image tahm;

    private Rectangle bounds;

    public TahmK(float x, float y, int align, int sizeX, int sizeY){

        //tahm = new com.badlogic.gdx.scenes.scene2d.ui.Image(game.manager.get("TahmKench.png", Texture.class));
        tahm = new Image(Assets.tahm);

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        tahm.setPosition(x,y,align);
        tahm.setSize(sizeX,sizeY);
        //bounds.setPosition(x,y);
        //bounds.setSize(sizeX,sizeY);
        bounds = new Rectangle(position.x,position.y,tahm.getWidth(),tahm.getHeight());
    }

    public Image actorTahm(){
        return tahm;
    }

    public Rectangle tahmBounds(){
        return this.bounds;
    }

    public float getWidth(){
        return tahm.getWidth();
    }

    public float getHeight(){
        return tahm.getHeight();
    }

    public void update(float dt){
        bounds.setPosition(tahm.getX(),tahm.getY());
    }
}
