package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Created by Rabithole on 1/28/2017.
 */

public class Ryze extends AndroidApplication  {
        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
            config.useWakelock = true;

            // create an instance of MyGame, and set the callback
            RyzeGame game = new RyzeGame();
            // Since AndroidLauncher implements MyGame.MyGameCallback, we can just pass 'this' to the callback setter.

            initialize(game, config);
        }
}
