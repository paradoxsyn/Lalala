package com.mygdx.game.ui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Created by Paradox on 3/9/2017.
 */

public class Glist implements MultiGestureDetector.GestureListener,InputProcessor {

    Actor a,b,c,d;
    Rectangle talon,tahm,ren,ann;
    boolean boola,boolb,boolc,boold;
    private Stage stage;

    public Glist(Stage stage,Actor a, Actor b, Actor c, Actor d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.stage = stage;

        talon = new Rectangle();
        tahm = new Rectangle();
        ren = new Rectangle();
        ann = new Rectangle();




    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    public void update(){
        talon.set(a.getX(),a.getY(),a.getWidth(),a.getHeight());
        tahm.set(b.getX(),b.getY(),b.getWidth(),b.getHeight());
        ren.set(c.getX(),c.getY(),c.getWidth(),c.getHeight());
        ann.set(d.getX(),d.getY(),d.getWidth(),d.getHeight());
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        a.addAction(Actions.moveBy(velocityX,velocityY,3));
        b.addAction(Actions.moveBy(velocityX,velocityY,3));
        c.addAction(Actions.moveBy(velocityX,velocityY,3));
        d.addAction(Actions.moveBy(velocityX,velocityY,3));

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){

        return false;
    }

    @Override
    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
      return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
