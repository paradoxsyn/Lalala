package com.mygdx.game.states;

/**
 * Created by Paradox on 2/6/2017.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.animations.AnimationEquation;
import com.mygdx.game.animations.EyeParticle;
import com.mygdx.game.animations.ShakeScrn;
import com.mygdx.game.resources.Assets;


public class PlayState4 extends State {

    private final String TAG = getClass().getSimpleName();
    private Image bg;
    private Image overload;
    private Image runepris;
    private Image spellflux;
    private Image realmwarp;
    private Image startbtn;
    private Vector3 touchPoint;
    private float time;
    private float w, h;
    private float baseX, baseY;
    private float[] tint = {0.2f, 0.204f, 1,1, 0.996f, 0,1, 0.2f, 0.2f,0.2f, 1, 0.2f};
    float speed;
    int lvl;
    Stage stage;
    private EyeParticle eye1;
    private EyeParticle eye2;
    ShakeScrn shakeScrn;
    private TextureAtlas runeatlas = new TextureAtlas();
    private Rectangle startBounds;
    SpriteBatch batch;
    private boolean shake;
    private float elapsed;
    private Action over,over2,over3,over4,pris,pris2,pris3,pris4,flux,flux2,flux3,flux4,warp,warp2,warp3,warp4;
    float delta;

    public PlayState4(final StateManager manager, final float speed, int level) {
        super(manager);
        //camera.setToOrtho(false, WIDTH/2, HEIGHT/2);
        batch = new SpriteBatch();
        stage = new Stage(viewPort,batch);
        shakeScrn = new ShakeScrn(3f,1000f);
        bg = new Image(Assets.ryzeflip4);
        startbtn = new Image(Assets.startbutton);
        overload = new Image(Assets.overload);
        runepris = new Image(Assets.runepris);
        spellflux = new Image(Assets.spellflux);
        realmwarp = new Image(Assets.realmwarp);
        startbtn.setScale(4f);
        overload.setScale(3f);
        runepris.setScale(3f);
        spellflux.setScale(3f);
        realmwarp.setScale(3f);
        Gdx.input.setInputProcessor(stage);
        startbtn.setPosition(viewPort.getWorldWidth()/2-100,bg.getY(), Align.center);
        overload.setPosition(Gdx.graphics.getWidth()/2-overload.getWidth(),Gdx.graphics.getHeight()/2-overload.getHeight()*2);
        runepris.setPosition(overload.getX()-150,overload.getY()-150);
        spellflux.setPosition(overload.getX()+150,overload.getY()-150);
        realmwarp.setPosition(spellflux.getX()-150,spellflux.getY()-150);
        bg.setBounds(0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.speed=speed;
        lvl=level;
        touchPoint = new Vector3(0, 0, 0);
        runeatlas.addRegion("particle",new TextureRegion(new Texture("particle.png")));
        eye1 = new EyeParticle(runeatlas);
        eye2 = new EyeParticle(runeatlas);
        eye1.setX(Gdx.graphics.getWidth()/2+65);
        eye1.setY(Gdx.graphics.getHeight()/2+360);
        eye2.setX(Gdx.graphics.getWidth()/2+75);
        eye2.flipXY();
        eye2.setY(Gdx.graphics.getHeight()/2+280);
        eye1.getEmitter().getTint().setColors(tint);
        eye2.getEmitter().getTint().setColors(tint);
        baseX = camera.position.x;
        baseY = camera.position.y;

        runeActions();
        startbtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                manager.set(new GameState4(manager,3,3));
                //manager.set(new ScoreScrn(manager));
                return false;
            }
        });

        runeStart();
        stage.addActor(bg);
        stage.addActor(startbtn);
        stage.addActor(overload);
        stage.addActor(runepris);
        stage.addActor(spellflux);
        stage.addActor(realmwarp);
        stage.addActor(eye1);
        stage.addActor(eye2);

    }

    @Override
    protected void handleInput() {

        //if (Gdx.input.isTouched()) {
        touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // if (startBounds.contains(touchPos.x, touchPos.y)) {

        //   manager.set(new GameState(manager,speed,lvl));
        // }
        //}


    }

    public void runeActions(){
        over = (Actions.moveTo(runepris.getX(),runepris.getY(),3f,Interpolation.exp10));
        over2 =  Actions.moveTo(realmwarp.getX(),realmwarp.getY(),3f,Interpolation.exp10);
        over3 = Actions.moveTo(spellflux.getX(),spellflux.getY(),3f,Interpolation.exp10);
        over4 = Actions.moveTo(overload.getX(),overload.getY(),3f,Interpolation.exp10);

        pris = (Actions.moveTo(realmwarp.getX(),realmwarp.getY(),3f,Interpolation.exp10));
        pris2 =  Actions.moveTo(spellflux.getX(),runepris.getY(),3f,Interpolation.exp10);
        pris3 = Actions.moveTo(overload.getX(),overload.getY(),3f,Interpolation.exp10);
        pris4 = Actions.moveTo(runepris.getX(),runepris.getY(),3f,Interpolation.exp10);

        flux = (Actions.moveTo(overload.getX(),overload.getY(),3f,Interpolation.exp10));
        flux2 = Actions.moveTo(runepris.getX(),runepris.getY(),3f,Interpolation.exp10);
        flux3 = Actions.moveTo(realmwarp.getX(),realmwarp.getY(),3f,Interpolation.exp10);
        flux4 = Actions.moveTo(spellflux.getX(),spellflux.getY(),3f,Interpolation.exp10);

        warp = (Actions.moveTo(spellflux.getX(),spellflux.getY(),3f,Interpolation.exp10));
        warp2 =  Actions.moveTo(overload.getX(),overload.getY(),3f,Interpolation.exp10);
        warp3 = Actions.moveTo(runepris.getX(),runepris.getY(),3f,Interpolation.exp10);
        warp4 = Actions.moveTo(realmwarp.getX(),realmwarp.getY(),3f,Interpolation.exp10);
    }

    public void runeStart(){


        overload.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(over,over2,over3,over4)));
        runepris.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(pris,pris2,pris3,pris4)));
        spellflux.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(flux,flux2,flux3,flux4)));
        realmwarp.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(warp,warp2,warp3,warp4)));


    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float dt) {

        handleInput();
        //camera.update();
        //stage.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
        //resize(WIDTH,HEIGHT);
        stage.act();


    }

    public int getFPS() {
        return Gdx.graphics.getFramesPerSecond();
    }


    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsed+=Gdx.graphics.getDeltaTime();
        // Return back to the original position each time before calling shake update.
        // We don't update the batch here since we're only using the position for calculating shake.
        camera.position.x = baseX;
        camera.position.y = baseY;
        delta = Gdx.graphics.getDeltaTime();
        // Update the shake position, then the camera.

        if(shake) {
            shakeScrn.update(delta, camera);
        }
        //batch.setProjectionMatrix(camera.combined);
        //stage.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
        //camera.update();
        stage.draw();
    }



    @Override
    public void dispose() {
        stage.dispose();
        Gdx.app.log(TAG, "disposed");
    }

}

