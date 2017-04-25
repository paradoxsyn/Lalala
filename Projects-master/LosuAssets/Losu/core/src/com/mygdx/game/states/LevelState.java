package com.mygdx.game.states;

/**
 * Created by Paradox on 2/4/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.animations.AnimatedImage;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.resources.BodyEditorLoader;
import com.mygdx.game.resources.MusicPlayer;
import com.mygdx.game.resources.PEXML;

import static com.mygdx.game.RyzeGame.HEIGHT;
import static com.mygdx.game.RyzeGame.WIDTH;



public class LevelState extends State {
    private final String TAG = getClass().getSimpleName();
    private Image bg;
    private Image lvl1;
    private Vector3 touchPoint;
    private Rectangle lvl1bounds;
    private Vector2 bodypos;
    private Vector2 bodyposOrigin;

    SpriteBatch batch;

    private Image runebuttonconvert;
    private Image runebutton2convert;
    private Image runebutton3convert;
    private Image runeletter1;
    private Image runeletter2;
    private Image runeletter3;
    private Image runeletter4;
    private Image runeletter5;
    private Image runeletter6;
    private Image runeletterlvl2,runeletterlvl3,runeletterlvl4;
    private ImageButton runebutton;
    private Image runebutton2;
    private Image runebutton3;
    private Image secretbutton;
    private Rectangle runebound1;
    private Rectangle runebound2;
    private Rectangle runebound3;
    private Rectangle runebound4;
    private Rectangle letterbounds;

    //Box2d Init
    World world;
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    float accumulator = 0;
    Box2DDebugRenderer debugRenderer;
    BodyEditorLoader physicsBodies;
    Body runeModel;

    private boolean click = false;
    private boolean secret = false;
    private boolean visible = false;
    private boolean level2 = false;
    private boolean level3,level4 = false;
    private boolean level2click = false;
    private boolean level3click,level4click = false;

    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    Stage stage;

    Preferences prefs = Gdx.app.getPreferences("Ryze");

    public LevelState(final StateManager manager) {
        super(manager);
        Assets.load(); //Seperate into champ memory management in different methods
        //Box2D.init();
        level2 = prefs.getBoolean("unlocklevel2");
        //level2=true;
        level3 = prefs.getBoolean("unlocklevel3");
        //level3=true;
        level4 = prefs.getBoolean("unlocklevel4");
        //level4=true;
        secret = prefs.getBoolean("unlocksecret");
        //secret=true;
        batch = new SpriteBatch();
        touchPoint = new Vector3();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        bg = new Image(Assets.ryzebackground);
        System.out.println(level2);
        if(!level2){
            runebuttonconvert = new Image(Assets.runebutton);
        }
        else if(level2){
            runebuttonconvert = new Image(Assets.runebuttonfull);
        }
        if(!level3) {
            runebutton2 = new Image(Assets.runebutton2);
        }
        else if(level3){
            runebutton2 = new Image(Assets.runebutton2full);
        }
        if(!level4){
            runebutton3 = new Image(Assets.runebutton3);
        }
        else if(level4){
            runebutton3 = new Image(Assets.runebutton3full);
        }

        runeletter1 = new Image(Assets.rune1);
        runeletter2 = new Image(Assets.rune2);
        runeletter3 = new Image(Assets.rune3);
        runeletter4 = new Image(Assets.rune4);
        runeletter5 = new Image(Assets.rune3);
        runeletter6 = new Image(Assets.rune5);
        runeletterlvl2 = new Image(Assets.rune6);
        runeletterlvl3 = new Image(Assets.rune7);
        runeletterlvl4 = new Image(Assets.rune8);
        runebutton = new ImageButton(runebuttonconvert.getDrawable());
        //runebutton2 = new Image(Assets.runebutton2);
        //runebutton3 = new Image(Assets.runebutton3);
        runeletter2.setPosition(bg.getX()-20,bg.getY()+50);
        runeletter3.setPosition(bg.getX()+100,bg.getY());
        runeletter4.setPosition(bg.getX()+200,bg.getY());
        runeletter5.setPosition(bg.getX()+300,bg.getY());
        runeletter6.setPosition(bg.getX()+400,bg.getY()+20);
        runeletter1.setPosition(bg.getX()+550,bg.getY()+20);
        runeletterlvl2.setPosition(bg.getX()+550,bg.getY()+20);
        runeletterlvl3.setPosition(bg.getX()+550,bg.getY()+20);
        runeletterlvl4.setPosition(bg.getX()+550,bg.getY()+20);
        runeletter1.setSize(300,300);
        runeletter2.setSize(300,300);
        runeletter3.setSize(300,300);
        runeletter4.setSize(300,300);
        runeletter5.setSize(300,300);
        runeletter6.setSize(300,300);
        runeletterlvl2.setSize(300,300);
        runeletterlvl3.setSize(250,250);
        runeletterlvl4.setSize(250,250);
        bg.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //runebutton.setSize(600,600);
        //runebutton2.setSize(200,200);
        //runebutton3.setSize(1000,1000);
        runebutton.setPosition(Gdx.graphics.getWidth()/2-runebutton.getWidth(),Gdx.graphics.getHeight()/2);
        runebutton2.setPosition(runebutton.getX(),runebutton.getY()-300);
        runebutton3.setPosition(runebutton2.getX(),runebutton2.getY()-200);
        runebound1 = new Rectangle(runebutton.getX()-20,runebutton.getY()+200,(runebutton.getWidth()/2)-30,runebutton.getHeight()-250);
        runebound2 = new Rectangle(runebutton.getX()+100,runebutton.getY()+200,runebutton.getWidth()/2-30,runebutton.getHeight()-300);
        letterbounds = new Rectangle(runeletter2.getX(),runeletter2.getY(),runeletter1.getWidth()*2.5f,runeletter1.getHeight());

        if(level3){
            runebutton2.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    //manager.set(new GameState(manager,speed,lvl));
                    //manager.set(new PlayState3(manager,2,2));
                    if(!click) {
                        letterActions3();
                        click=true;
                    }
                    return false;
                }
            });
        }
        if(level4){
            runebutton3.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    //manager.set(new GameState(manager,speed,lvl));
                    //manager.set(new PlayState3(manager,2,2));
                    if(!click) {
                        letterActions4();
                        click=true;
                    }
                    return false;
                }
            });
        }

        runeActions();
        //world = new World(new Vector2(WIDTH,HEIGHT),true);
        //BodyDef bodyDef = new BodyDef();
        //bodyDef.type = BodyDef.BodyType.StaticBody;
        //bodyDef.position.set(runebutton.getX(),runebutton.getY());
        //debugRenderer = new Box2DDebugRenderer();
        //physicsBodies = new BodyEditorLoader(Gdx.files.internal("physics/realmcircle"));
        //FixtureDef fd = new FixtureDef();
        //fd.density = 1;
        //fd.friction = 0.5f;
        //fd.restitution = 0.3f;
        //runeModel = world.createBody(bodyDef);
        //runeModel.setUserData(runebutton);

        //physicsBodies.attachFixture(runeModel, "rune",fd,400);
        //bodyposOrigin = physicsBodies.getOrigin("rune",400).cpy();
        //bodypos = runeModel.getPosition().sub(bodyposOrigin);

        //runebutton.setPosition(runeModel.getPosition().x,runeModel.getPosition().y);
        //runebutton.setSize(runeModel);

        runeletter1.addAction(Actions.hide());
        runeletter2.addAction(Actions.hide());
        runeletter3.addAction(Actions.hide());
        runeletter4.addAction(Actions.hide());
        runeletter5.addAction(Actions.hide());
        runeletter6.addAction(Actions.hide());
        runeletterlvl2.addAction(Actions.hide());
        runeletterlvl3.addAction(Actions.hide());
        runeletterlvl4.addAction(Actions.hide());

        stage.addActor(bg);
        stage.addActor(runebutton);
        stage.addActor(runebutton2);
        stage.addActor(runebutton3);
        stage.addActor(runeletter1);
        stage.addActor(runeletter2);
        stage.addActor(runeletter3);
        stage.addActor(runeletter4);
        stage.addActor(runeletter5);
        stage.addActor(runeletter6);
        stage.addActor(runeletterlvl2);
        stage.addActor(runeletterlvl3);
        stage.addActor(runeletterlvl4);

        if(secret){
            secretbutton = new Image(Assets.secretbutton);
            secretbutton.setSize(200,200);
            secretbutton.setOrigin(secretbutton.getWidth()/2,secretbutton.getHeight()/2);
            secretbutton.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-200);
            secretbutton.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.rotateBy(360,5f))));

            secretbutton.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    manager.set(new SecretState(manager,1,1));
                    //manager.set(new BattleState(manager,1,1));
                    return false;
                }
            });

            stage.addActor(secretbutton);
        }



    }

    public void runeActions(){
        runebutton.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveBy(0,-25,1.5f,Interpolation.swingOut),Actions.moveBy(0,25,1.5f,Interpolation.swingIn))));
        runebutton2.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveBy(0,-25,1.3f,Interpolation.circleIn),Actions.moveBy(0,25,1.3f))));
        runebutton3.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveBy(0,-25,1.1f,Interpolation.pow4Out),Actions.moveBy(0,25,1.1f,Interpolation.linear))));
    }


    @Override
    protected void handleInput() {


        //if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) {
            //manager.set(new PlayState(manager));
          //  System.out.println("Test");
           // return;
        //}

        if(Gdx.input.justTouched()){
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            stage.getViewport().unproject(touchPoint);
            System.out.println(touchPoint.x + " " + touchPoint.y);
            System.out.println(runebound2);

            //world.QueryAABB();
            //use query
            if(!click){
                if(runebound1.contains(touchPoint.x,touchPoint.y)){
                    letterActions();
                    click=true;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            click = false;
                            visible = false;
                        }
                    },8f);
                }
                else if(runebound2.contains(touchPoint.x,touchPoint.y) && level2){
                    letterActions2();
                    level2click=true;
                    click=true;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            click = false;
                            visible = false;
                        }
                    },8f);
                }
            }

            if(visible){
                if(letterbounds.contains(touchPoint.x,touchPoint.y) && !level2click && !level3click && !level4click){
                    manager.set(new PlayState(manager,5f,1));
                    //manager.set(new ScoreScrn(manager));
                }
                else if(letterbounds.contains(touchPoint.x,touchPoint.y)&&level2click){
                    manager.set(new PlayState2(manager,2,2));
                }
                else if(letterbounds.contains(touchPoint.x,touchPoint.y)&&level3click){
                    manager.set(new PlayState3(manager,2,2));
                }
                else if(letterbounds.contains(touchPoint.x,touchPoint.y)&&level4click){
                    manager.set(new PlayState4(manager,2,2));
                }
                //add levels 2,3,4,5
            }
        }



        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    public void letterActions(){
        visible = true;
        runeletter1.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.2f),Actions.delay(4f),Actions.fadeOut(2f),Actions.hide()));
        runeletter2.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.4f),Actions.delay(4f),Actions.fadeOut(1.8f),Actions.hide()));
        runeletter3.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.6f),Actions.delay(4f),Actions.fadeOut(1.6f),Actions.hide()));
        runeletter4.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.8f),Actions.delay(4f),Actions.fadeOut(1.4f),Actions.hide()));
        runeletter5.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        runeletter6.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                click = false;
                visible = false;
            }
        },8f);
    }
    public void letterActions2(){
        visible = true;
        runeletterlvl2.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.2f),Actions.delay(4f),Actions.fadeOut(2f),Actions.hide()));
        runeletter2.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.4f),Actions.delay(4f),Actions.fadeOut(1.8f),Actions.hide()));
        runeletter3.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.6f),Actions.delay(4f),Actions.fadeOut(1.6f),Actions.hide()));
        runeletter4.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.8f),Actions.delay(4f),Actions.fadeOut(1.4f),Actions.hide()));
        runeletter5.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        runeletter6.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                click = false;
                visible = false;
                level2click = false;
            }
        },8f);
    }

    public void letterActions3(){
        visible = true;
        level3click=true;
        runeletterlvl3.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.2f),Actions.delay(4f),Actions.fadeOut(2f),Actions.hide()));
        runeletter2.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.4f),Actions.delay(4f),Actions.fadeOut(1.8f),Actions.hide()));
        runeletter3.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.6f),Actions.delay(4f),Actions.fadeOut(1.6f),Actions.hide()));
        runeletter4.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.8f),Actions.delay(4f),Actions.fadeOut(1.4f),Actions.hide()));
        runeletter5.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        runeletter6.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                click = false;
                visible = false;
                level3click = false;
            }
        },8f);
    }

    public void letterActions4(){
        visible = true;
        level4click=true;
        runeletterlvl4.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.2f),Actions.delay(4f),Actions.fadeOut(2f),Actions.hide()));
        runeletter2.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.4f),Actions.delay(4f),Actions.fadeOut(1.8f),Actions.hide()));
        runeletter3.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.6f),Actions.delay(4f),Actions.fadeOut(1.6f),Actions.hide()));
        runeletter4.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(1.8f),Actions.delay(4f),Actions.fadeOut(1.4f),Actions.hide()));
        runeletter5.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        runeletter6.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2f),Actions.delay(4f),Actions.fadeOut(1.2f),Actions.hide()));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                click = false;
                visible = false;
                level4click = false;
            }
        },8f);
    }

    public void stepWorld(){
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME,VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }


    @Override
    public void update(float dt) {
        //camera.update();
        handleInput();
        stage.act();
    }

    @Override
    public void render() {
        //batch.setProjectionMatrix(camera.combined);
        //batch.setProjectionMatrix(stage.getCamera().combined);
        //stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        //stepWorld();

        //batch.begin();
        //debugRenderer.render(world, stage.getCamera().combined);
        //batch.end();
    }


    @Override
    public void dispose() {
        stage.dispose();
        Gdx.app.log(TAG, "disposed");
    }

}
