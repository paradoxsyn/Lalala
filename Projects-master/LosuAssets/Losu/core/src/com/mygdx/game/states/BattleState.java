package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.animations.AnimatedImage;
import com.mygdx.game.animations.AnimatedImage2;
import com.mygdx.game.animations.Background;
import com.mygdx.game.animations.Background2;
import com.mygdx.game.animations.Laser;
import com.mygdx.game.animations.ShakeScrn;
import com.mygdx.game.bosses.RuneBawss;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.resources.BodyEditorLoader;
import com.mygdx.game.resources.SwipeHandler;
import com.mygdx.game.resources.SwipeTriStrip;
import com.mygdx.game.ui.Glist;
import com.mygdx.game.ui.MultiGestureDetector;

import static com.mygdx.game.RyzeGame.HEIGHT;
import static com.mygdx.game.RyzeGame.WIDTH;

/**
 * Created by Paradox on 4/5/2017.
 */

public class BattleState extends State {

    float delta;
    private InputMultiplexer multi;
    private MultiGestureDetector list;
    Stage stage;
    Background2 bg;
    RuneBawss bawss;
    AnimatedImage2 boss;
    AnimatedImage runetrap;
    AnimatedImage2 runedamage;
    SpriteBatch batch;
    Laser laser;
    ShakeScrn shakescrn;
    Action charge;
    private Rectangle laserBounds;
    private Rectangle warpBounds;
    private Rectangle screenBounds;
    private Rectangle overBounds;
    private Rectangle bossBounds;
    Texture ts1,ts2,tm1,tm2,te1,te2;
    Image overload,runepris,spellflux,realmwarp;
    Sprite lSpriteS1,lSpriteS2,lSpriteM1,lSpriteM2,lSpriteE1,lSpriteE2;
    private float add,deltaTime;
    private float random;
    private boolean fire,playonce=false;
    private boolean lasering,charging=false;
    private boolean bosshit=false;
    private boolean shake=true;
    private boolean trapped=false;
    private boolean cooldown=false;
    private float baseX,baseY;
    private int chargecount,lasercount;
    private int lifeCounter=0,runecounter=0;
    Group g;
    Group buttons;
    ShapeRenderer r = new ShapeRenderer();
    Image test = new Image(Assets.ryzeflip);


    public BattleState(final StateManager manager, final float speed, int level) {
        super(manager);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage();
        batch = new SpriteBatch();
        bg = new Background2(Assets.runebattle);
        g = new Group();
        buttons = new Group();
        overload = new Image(Assets.overload);
        runepris = new Image(Assets.runepris);
        spellflux = new Image(Assets.spellflux);
        realmwarp = new Image(Assets.realmwarp);

        String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "uniform mat4 u_projTrans;\n" //
                + "varying vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "\n" //
                + "void main()\n" //
                + "{\n" //
                + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
                + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
                + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
                + "}\n";
        String fragmentShader = "#ifdef GL_ES\n" //
                + "#define LOWP lowp\n" //
                + "precision mediump float;\n" //
                + "#else\n" //
                + "#define LOWP \n" //
                + "#endif\n" //
                + "varying LOWP vec4 v_color;\n" //
                + "varying vec2 v_texCoords;\n" //
                + "uniform sampler2D u_texture;\n" //
                + "void main()\n"//
                + "{\n" //
                + "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords).a;\n" //
                + "}";

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

        overload.setPosition(100,bg.getY());
        runepris.setPosition(300,bg.getY());
        spellflux.setPosition(500,bg.getY());
        realmwarp.setPosition(700,bg.getY());

        overload.setSize(200,200);
        runepris.setSize(200,200);
        spellflux.setSize(200,200);
        realmwarp.setSize(200,200);


        boss = new AnimatedImage2(Assets.runeboss);
        runetrap = new AnimatedImage(Assets.prisanim);
        runedamage = new AnimatedImage2(Assets.runedamage);
        boss.setPosition(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-500);
        boss.setSize(600,600);
        boss.setOrigin(boss.getWidth()/2,boss.getHeight()/2);
        bossMove();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                shake=false;
            }
        },4f);

        shakescrn = new ShakeScrn(6f,1000f);
        baseX = camera.position.x;
        baseY = camera.position.y;
        multi = new InputMultiplexer();
        list = new MultiGestureDetector(new Glist(stage,overload,runepris,spellflux,realmwarp));
        multi.addProcessor(stage);
        //multi.addProcessor(list);
        Gdx.input.setInputProcessor(multi);

        ts1 = new Texture(Gdx.files.internal("runes/bigruneanim/beamstart1.png"));
        ts2 = new Texture(Gdx.files.internal("runes/bigruneanim/beamstart2.png"));
        tm1 = new Texture(Gdx.files.internal("runes/bigruneanim/beammid1.png"));
        tm2 = new Texture(Gdx.files.internal("runes/bigruneanim/beammid2.png"));
        te1 = new Texture(Gdx.files.internal("runes/bigruneanim/beamend1.png"));
        te2 = new Texture(Gdx.files.internal("runes/bigruneanim/beamend2.png"));

        lSpriteS1 = new Sprite(ts1);
        lSpriteS2 = new Sprite(ts2);
        lSpriteM1 = new Sprite(tm1);
        lSpriteM2 = new Sprite(tm2);
        lSpriteE1 = new Sprite(te1);
        lSpriteE2 = new Sprite(te2);

        laser = new Laser();
        laser.begin1 = lSpriteS1;
        laser.begin2 = lSpriteS2;
        laser.mid1 = lSpriteM1;
        laser.mid2 = lSpriteM2;
        laser.end1 = lSpriteE1;
        laser.end2 = lSpriteE2;
        laser.degrees = 180;
        laser.color = Color.NAVY;
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

        input();
        test.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        laserBounds = new Rectangle(0,0,0,0);
        warpBounds = new Rectangle(0,0,0,0);
        screenBounds = new Rectangle(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        overBounds = new Rectangle(0,0,0,0);
        bossBounds = new Rectangle(0,0,0,0);
        //bawss = new RuneBawss(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,stage);
        //bawss.moveAround(bg.getX(),bg.getHeight(),3f,Interpolation.bounce);
        stage.addActor(bg);
        //stage.addActor(test);
        stage.addActor(buttons);
        //stage.addActor(boss);
        stage.addActor(g);
        //stage.getBatch().setShader(shader);
        g.addActor(boss);
        buttons.addActor(overload);
        buttons.addActor(runepris);
        buttons.addActor(spellflux);
        buttons.addActor(realmwarp);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(MathUtils.random(1,3)==2)
                        charge();
                        charging=true;
                        chargecount++;
                        lasercount=0;
                        if(chargecount>3){
                            boss.removeAction(charge);
                            laserFire();
                        }
            }

        },1f,4f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(MathUtils.random(1,2)==2) {
                    //if(!charging) {
                        laserFire();
                        lasering=true;
                        lasercount++;
                        chargecount=0;
                        if(lasercount>3){
                            laserReset();
                            charge();
                        }
                    //}
                }
            }
        },1f,2f);
    }

    public void bossMove(){
        boss.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveTo(bg.getX(),Gdx.graphics.getHeight()-600,3f, Interpolation.exp5),Actions.delay(random),Actions.moveTo(Gdx.graphics.getWidth()-500,Gdx.graphics.getHeight()-600,3f,Interpolation.exp5),Actions.delay(random))));
    }
    public void laserFire(){
        fire=true;
    }

    public void runeTrap(){
        stage.addActor(runetrap);
        boss.removeAction(charge);
        fire=false;
        runetrap.setSize(boss.getWidth(),boss.getHeight());
        runetrap.setPosition(boss.getX(),boss.getY());
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runetrap.remove();
                bossMove();
            }
        },2f);
    }

    public void input(){
        overload.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                overload.setDrawable(new TextureRegionDrawable(Assets.overload2));
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                overload.setDrawable(new TextureRegionDrawable(Assets.overload1));
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                overload.moveBy(x - overload.getWidth() / 2, y - overload.getHeight() / 2);

            }});

        overload.addListener(new ActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){

                overload.addAction(Actions.moveBy(x,y,3f));
            }

        });
        runepris.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                runepris.setDrawable(new TextureRegionDrawable(Assets.runepris2));
                if(!cooldown) {
                    runeTrap();
                    cooldown=true;
                    trapped=true;
                    runepris.setTouchable(Touchable.disabled);
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        cooldown=false;
                        runepris.setTouchable(Touchable.enabled);
                    }
                },6f);

                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                runepris.setDrawable(new TextureRegionDrawable(Assets.runepris1));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        trapped=false;
                    }
                },2f);
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                //overload.moveBy(x - overload.getWidth() / 2, y - overload.getHeight() / 2);

            }});
        spellflux.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                spellflux.setDrawable(new TextureRegionDrawable(Assets.flux2));
                overload.setColor(Color.CHARTREUSE);

                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                spellflux.setDrawable(new TextureRegionDrawable(Assets.flux1));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        overload.setColor(0,0,0,1);
                    }
                },2f);
            }
        });

        realmwarp.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                realmwarp.setDrawable(new TextureRegionDrawable(Assets.realmwarp2));
                buttons.addAction(Actions.fadeOut(1.5f));
                buttons.setTouchable(Touchable.disabled);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                      buttons.addAction(Actions.fadeIn(1.5f));
                        buttons.setTouchable(Touchable.enabled);
                    }
                },2f);
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                realmwarp.setDrawable(new TextureRegionDrawable(Assets.realmwarp1));

            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                //overload.moveBy(x - overload.getWidth() / 2, y - overload.getHeight() / 2);

            }});
    }

    @Override
    protected void handleInput() {
        random = MathUtils.random(1,4);
        if(overload.getX() < screenBounds.getX()){
            //overload.setPosition(screenBounds.getX(),overload.getY());
           resetOver();
        }
        else if(overload.getX() > screenBounds.getX()+screenBounds.getWidth()){
            //overload.setPosition(screenBounds.getX()+screenBounds.getWidth(), overload.getY());
           resetOver();
        }
        if(overload.getY() < screenBounds.getY()){
            //overload.setPosition(overload.getX(),screenBounds.getY());
           resetOver();
        }
        else if(overload.getY() > screenBounds.getY() + screenBounds.getHeight()){
            //overload.setPosition(overload.getX(),screenBounds.getY() + screenBounds.getHeight());
            resetOver();
        }

    }

    public void bossHit(){
            runedamage.setSize(boss.getWidth(),boss.getHeight());
            boss.removeAction(charge);
            fire=false;
            runedamage.setPosition(boss.getX(),boss.getY());
            stage.addActor(runedamage);
            boss.setVisible(false);
            bosshit=true;
            lifeCounter++;
            if(lifeCounter==10){
                System.out.println("done");
                stage.addAction(Actions.fadeOut(3f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        manager.set(new LevelState(manager));
                    }
                },3f);
            }
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    runedamage.remove();
                    boss.setVisible(true);
                    bosshit=false;
                }
            },2f);

    }

    @Override
    public void update(float dt) {

        handleInput();
        delta+=Gdx.graphics.getDeltaTime();
        camera.update();
        //batch.setProjectionMatrix(camera.combined);
        //stage.getViewport().update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
        //resize(WIDTH,HEIGHT);


    }

    public int getFPS() {
        return Gdx.graphics.getFramesPerSecond();
    }


    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        deltaTime = Gdx.graphics.getDeltaTime();
        //camera.position.x=baseX;
        //camera.position.y=baseY;
        //if(shake) {
          //  shakescrn.update(deltaTime, camera);
        //}
        warpBounds.set(realmwarp.getX(),realmwarp.getY(),realmwarp.getWidth(),realmwarp.getHeight());
        overBounds.set(overload.getX(),overload.getY(),overload.getWidth(),overload.getHeight());
        bossBounds.set(boss.getX(),boss.getY(),boss.getWidth(),boss.getHeight());


        //fire=true;
        stage.draw();
        //stage.act();
        bg.act(deltaTime);
        if(!trapped || !bosshit){
            boss.act(deltaTime);
        }
        buttons.act(deltaTime);
        runetrap.act(deltaTime);
        runedamage.act(deltaTime);



        //batch.setProjectionMatrix(camera.combined);
        //camera.update();



        batch.begin();
        if(fire) {
            add += Gdx.graphics.getDeltaTime();
            laserBounds.set(laser.end1.getX(),laser.end1.getY(),laser.end1.getWidth(),laser.end1.getHeight());
            laser.distance = (100 + add * 500);
            laser.position.set(boss.getX(), boss.getY() + 200);
            laser.begin1.draw(batch);
            laser.begin2.draw(batch);
            laser.mid1.draw(batch);
            laser.mid2.draw(batch);
            laser.end1.draw(batch);
            laser.end2.draw(batch);
            laser.render(deltaTime);
        }
        batch.end();


        if(laserBounds.getY()*.65 > Gdx.graphics.getHeight()){
            laserReset();
            fire=false;
            lasering=false;

        }
        if(laserBounds.overlaps(warpBounds)){
            runecounter++;
            buttonsHit();
            if(runecounter>=3){
                System.out.println("End");
                stage.addAction(Actions.fadeOut(3f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        manager.set(new LevelState(manager));
                    }
                },3f);
            }
            laserReset();
            lasering=false;
            fire=false;
        }

        if(overBounds.overlaps(bossBounds)&&!charging){
            bossHit();
            resetOver();
        }
        drawDebug();
    }

    public void buttonsHit(){
        buttons.setColor(Color.BLACK);
        buttons.setTouchable(Touchable.disabled);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                buttons.setColor(0,0,0,1);
                buttons.setTouchable(Touchable.enabled);
            }
        },3f);
    }


    void drawDebug() {
        r.setAutoShapeType(true);
        r.begin();
        r.rect(laserBounds.getX(),laserBounds.getY(),laserBounds.getWidth(),laserBounds.getHeight());
        r.rect(bossBounds.getX(),bossBounds.getY(),bossBounds.getWidth(),bossBounds.getHeight());
        r.rect(overBounds.getX(),overBounds.getY(),overBounds.getWidth(),overBounds.getHeight());
        r.end();
    }

    public void resetOver(){
        overload.setPosition(100,bg.getY());
        overload.clearActions();
    }

    public void laserReset(){
        laser.distance=0f;
        add=0;
        laserBounds.set(0,0,0,0);
    }

    public void charge(){
        boss.clear();
       charge = Actions.sequence(Actions.moveTo(realmwarp.getWidth()/2,realmwarp.getHeight()-200,2f,Interpolation.pow5In),Actions.fadeOut(1f),Actions.run(new Runnable() {
            @Override
            public void run() {
                boss.setPosition(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-500);
                boss.addAction(Actions.fadeIn(1f));
                bossMove();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        charging=false;
                    }
                },.5f);
            }
        }));
        boss.addAction(charge);
    }

    @Override
    public void dispose() {
        //Gdx.app.log(TAG, "disposed");
    }
}
