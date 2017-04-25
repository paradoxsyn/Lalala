package com.mygdx.game.champs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.LosuGame;
import com.mygdx.game.resources.Assets;

/**
 * Created by Paradox on 2/25/2017.
 */

public class Talon {

    private final String TAG = getClass().getSimpleName();

    private Vector3 position;
    private Vector3 velocity;
    private Vector2 distanceT;
    private Vector2 distanceA;
    private float distancebet;
    private float speedup = 7f;
    private boolean isJumped = false;
    private boolean isRake = false;

    private com.badlogic.gdx.scenes.scene2d.ui.Image talon;

    private Rectangle bounds;

    public Talon(float x, float y, int align, int sizeX, int sizeY){

        //talon = new com.badlogic.gdx.scenes.scene2d.ui.Image(game.manager.get("Talon.png", Texture.class));
        talon = new Image(Assets.talon);

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        talon.setPosition(x,y,align);
        talon.setSize(sizeX,sizeY);
        //bounds.setPosition(x,y);
        //bounds.setSize(sizeX,sizeY);
        bounds = new Rectangle(position.x,position.y,talon.getWidth(),talon.getHeight());
    }

    public void chase(Actor actor){
        talon.addAction(Actions.moveTo(actor.getX(),actor.getY(),speedup));

    }

    public Image actorTalon(){
        return talon;
    }

    public void jump(Actor actor){
        distanceT = new Vector2(talon.getX(),talon.getY());
        distanceA = new Vector2(actor.getX(),actor.getY());
        distancebet = distanceT.dst(distanceA);

        //System.out.println(distancebet);

        if(distancebet <= 300){
            talon.addAction(Actions.moveTo(actor.getX(),actor.getY()));
            if(distancebet==0){
                isJumped = true;
            }
        }
    }

    public void rake(Actor actor){
        distanceT = new Vector2(talon.getX(),talon.getY());
        distanceA = new Vector2(actor.getX(),actor.getY());
        distancebet = distanceT.dst(distanceA);

        if(distancebet<=500){
            //talon.addAction(Actions.rotateBy(360,2f));
            //maybe make it rotate
            isRake=true;
        }

    }

    public boolean isJumped(){
        return isJumped;
    }
    public boolean isRaked() { return isRake;}

    public void trap(Actor actor){

        System.out.println("trapped");
        //runepris anim here
    }

    public void speedup(float speed){
        speedup = speedup - speed;
        //talon.clearActions();
        System.out.println(speedup);
    }

    public void slowdown(float speed){
        speedup = speedup + speed;
    }

    public Rectangle talonBounds(){
        return this.bounds;
    }

    public void update(float dt){

        bounds.setPosition(talon.getX(),talon.getY());


    }


}
