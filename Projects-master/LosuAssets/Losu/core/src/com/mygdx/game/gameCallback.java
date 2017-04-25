package com.mygdx.game;

import com.mygdx.game.resources.MusicPlayer;

/**
 * Created by Paradox on 2/4/2017.
 */

// Define an interface for your various callbacks to the android launcher
public interface gameCallback {

        public void onStartActivityA(MusicPlayer player);

        public void onStartActivityB();

        public void onStartSomeActivity(int someParameter, String someOtherParameter);

        public float getPos();

    }

