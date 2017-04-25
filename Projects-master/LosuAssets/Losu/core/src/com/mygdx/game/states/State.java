package com.mygdx.game.states;

/**
 * Created by Paradox on 2/4/2017.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.LosuGame;

import static com.mygdx.game.RyzeGame.HEIGHT;
import static com.mygdx.game.RyzeGame.WIDTH;

public abstract class State {
    protected FitViewport viewPort;
    //protected ScalingViewport viewPort;
    protected OrthographicCamera camera;
    protected StateManager manager;

    public State(StateManager manager) {
        this.manager = manager;
        camera = new OrthographicCamera();
        this.viewPort = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        //this.viewPort = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    //public abstract void resize(int width, int height);
    //public abstract void render(float dt);
    //public abstract void render(SpriteBatch batch);
    public abstract void render();
    public abstract void dispose();



}
