package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Rabithole on 1/22/2017.
 */

public class Ryze extends ScreenAdapter {
    RyzeGame game;
    Rectangle runeBounds;
    Vector3 touchPoint;

    public Ryze(RyzeGame game){
        this.game = game;
        runeBounds = new Rectangle(0, 500, 500, 500);
        touchPoint = new Vector3();

    }

    public void update() {
        if(Gdx.input.justTouched()) {
            if (runeBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("This worked!");
                return;
            }
        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.losubackground, 0, 0, 1300, 1800);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.react, 0, 500, 500, 500);
        game.batcher.end();
    }

    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {
        Settings.save();
    }
}