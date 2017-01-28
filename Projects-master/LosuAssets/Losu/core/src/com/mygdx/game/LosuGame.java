package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sun.rmi.runtime.Log;

/**
 * Created by Rabithole on 1/22/2017.
 */

public class LosuGame extends Game {

    public SpriteBatch batcher;
    public BitmapFont font;
    public OrthographicCamera camera;
    public boolean fuck;

    @Override
    public void create(){
        batcher = new SpriteBatch();
        Assets.load();
        setScreen(new Losu(this));
    }
    // Define an interface for your various callbacks to the android launcher
    public interface MyGameCallback {
        public void onStartActivityA();
        public void onStartActivityB();
        public void onStartSomeActivity(int someParameter, String someOtherParameter);
    }

    // Local variable to hold the callback implementation
    public MyGameCallback myGameCallback;

    // ** Additional **
    // Setter for the callback
    public void setMyGameCallback(MyGameCallback callback) {
        myGameCallback = callback;
    }

    public void someMethod() {
        // check the calling class has actually implemented MyGameCallback
        if (myGameCallback != null) {

            // initiate which ever callback method you need.
            if (fuck == true) {
                myGameCallback.onStartActivityA();
                //} else if () {
                //    myGameCallback.onStartActivityB();
                //} else {
                //    myGameCallback.onStartSomeActivity(someInteger, someString);
                //}

            }
            //else {
            //Log.e("MyGame", "To use this class you must implement MyGameCallback!")
        }
    }


    @Override
    public void render() {

        super.render();
    }
}
