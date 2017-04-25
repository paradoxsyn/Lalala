package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.resources.MusicPlayer;
import com.mygdx.game.states.StateManager;
import com.mygdx.game.ui.Loader;

/**
 * Created by Rabithole on 1/22/2017.
 */

public class LosuGame extends Game {

    public SpriteBatch batcher;
    public OrthographicCamera camera;
    public static boolean bounceback;
    public AssetManager manager;
    public MusicPlayer player;
    public float getPlayerPostition;
    public gameCallback gameCallback;

    public LosuGame(gameCallback callback){
        this.gameCallback = callback;
    }

    @Override
    public void create(){
        manager = new AssetManager();
        batcher = new SpriteBatch();
        //setScreen(new LosuScrn(this));
        setScreen(new Loader(this));
        player = new MusicPlayer();
        getPlayerPostition = gameCallback.getPos();
        player.setPosition(getPlayerPostition);
        player.play();
    }

    @Override
    public void render() {

        super.render();
    }

    // Local variable to hold the callback implementation

    // ** Additional **
    // Setter for the callback
    public void setMyGameCallback(gameCallback callback) {
        gameCallback = callback;
    }

    public void callBackListener() {
        // check the calling class has actually implemented MyGameCallback
        if (gameCallback != null) {
            // initiate which ever callback method you need.
            if (bounceback == true) {
                gameCallback.onStartActivityA(player);
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


}
