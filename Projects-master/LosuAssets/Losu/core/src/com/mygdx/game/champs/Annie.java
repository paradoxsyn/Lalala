package com.mygdx.game.champs;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.LosuGame;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.ui.MultiGestureDetector;

/**
 * Created by Paradox on 3/4/2017.
 */

public class Annie {
    private final String TAG = getClass().getSimpleName();

    private Vector3 position;
    private Vector3 velocity;
    private Vector2 distanceT;
    private Vector2 distanceA;
    private float distancebet;

    LosuGame game;

    private com.badlogic.gdx.scenes.scene2d.ui.Image ann;

    private Rectangle bounds;

    public Annie(float x, float y, int align, int sizeX, int sizeY){

        //ann = new com.badlogic.gdx.scenes.scene2d.ui.Image(game.manager.get("Annie_Splash_1.jpg", Texture.class));
        ann = new Image(Assets.annie);

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        ann.setPosition(x,y,align);
        ann.setSize(sizeX,sizeY);
        //bounds.setPosition(x,y);
        //bounds.setSize(sizeX,sizeY);
        bounds = new Rectangle(position.x,position.y,ann.getWidth(),ann.getHeight());
    }

    public Image actorAnn(){
        return ann;
    }

    public Rectangle annBounds(){
        return this.bounds;
    }

    public void update(float dt){
        bounds.setPosition(ann.getX(),ann.getY());
    }
}