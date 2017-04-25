package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.states.StateManager;
import com.mygdx.game.states.LevelState;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.screenWidth;

/**
 * Created by Rabithole on 1/28/2017.
 */

public class RyzeGame extends Game {

    private SpriteBatch batcher;
    public boolean fuck;
    private StateManager manager;
    private Music music;
    Stage stage;
    float elTime;
    public static final String title ="Ryzesu";

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    //private TweenManager tweenmng;
    LosuGame game;

    @Override
    public void create(){
        batcher = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        manager = new StateManager(game);
        Gdx.gl.glClearColor(0, .2f, 0, 1);
        manager.push(new LevelState(manager));
    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elTime+=Gdx.graphics.getDeltaTime();
        manager.update(Gdx.graphics.getDeltaTime());
        //manager.render(batcher);
        manager.render(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void dispose() {
        super.dispose();
        //music.dispose();
    }

    /*public void setScreenTransition(Screen screen){
        FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888,WIDTH, HEIGHT, false);
        buffer.begin();
        render();

        Sprite nextScreenSprite = new Sprite(buffer.getColorBufferTexture());
        nextScreenSprite.setPosition(WIDTH,0);

        Tween.to(nextScreenSprite,)

    }*/


}
