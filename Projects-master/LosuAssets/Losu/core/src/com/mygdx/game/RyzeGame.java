package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Rabithole on 1/22/2017.
 */

public class RyzeGame extends Game {
    public SpriteBatch batcher;

        @Override
        public void create(){
            batcher = new SpriteBatch();
            Assets.load();
            setScreen(new Ryze(this));
        }

        @Override
        public void render() {

            super.render();
        }

}
