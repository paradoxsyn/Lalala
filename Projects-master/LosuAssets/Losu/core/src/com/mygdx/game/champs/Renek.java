package com.mygdx.game.champs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.LosuGame;
import com.mygdx.game.resources.Assets;

/**
 * Created by Paradox on 3/4/2017.
 */

public class Renek {
    private final String TAG = getClass().getSimpleName();

    private Vector3 position;
    private Vector3 velocity;
    private Vector2 distanceT;
    private Vector2 distanceA;
    private float distancebet;

    private com.badlogic.gdx.scenes.scene2d.ui.Image ren;
    LosuGame game;

    private Rectangle bounds;

    public Renek(float x, float y, int align, int sizeX, int sizeY){

        //ren = new com.badlogic.gdx.scenes.scene2d.ui.Image(game.manager.get("Renekton.png", Texture.class));
        ren = new Image(Assets.renekton);

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        ren.setPosition(x,y,align);
        ren.setSize(sizeX,sizeY);
        //bounds.setPosition(x,y);
        //bounds.setSize(sizeX,sizeY);
        bounds = new Rectangle(position.x,position.y,ren.getWidth(),ren.getHeight());
    }

    public Image actorRen(){
        return ren;
    }

    public Rectangle renBounds(){
        return this.bounds;
    }

    public void update(float dt){
        bounds.setPosition(ren.getX(),ren.getY());
    }
}

