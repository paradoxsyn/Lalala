package com.mygdx.game;

import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Created by Rabithole on 1/28/2017.
 */

public class Ryze extends AndroidApplication  {
    public boolean level1;
    public boolean level2;
    public boolean level3;
    public boolean level4;
    public boolean level5;


    @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
            config.useWakelock = true;
            config.useImmersiveMode = true;
           /* DeviceInfo.info = new DeviceInfo() {

                @Override
                public String getInfo() {
                    return
                            "BOARD: "+Build.BOARD
                                    +"\nFINGERPRINT: "+Build.FINGERPRINT
                                    +"\nHOST: "+Build.HOST
                                    +"\nMODEL: "+Build.MODEL
                                    +"\nINCREMENTAL: "+Build.VERSION.INCREMENTAL
                                    +"\nRELEASE: "+ Build.VERSION.RELEASE

                            ;

                }

            };

            */

            // create an instance of MyGame, and set the callback
            RyzeGame game = new RyzeGame();
            // Since AndroidLauncher implements MyGame.MyGameCallback, we can just pass 'this' to the callback setter.
            //RyzeGame2 game = new RyzeGame2();
            initialize(game, config);
        }
}
