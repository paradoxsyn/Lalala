package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.animations.Background;
import com.mygdx.game.animations.EyeParticle;
import com.mygdx.game.animations.ShakeScrn;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.resources.BodyEditorLoader;
import com.mygdx.game.resources.SwipeHandler;
import com.mygdx.game.resources.SwipeTriStrip;

import java.sql.Time;

import static com.mygdx.game.RyzeGame.HEIGHT;
import static com.mygdx.game.RyzeGame.WIDTH;

/**
 * Created by Paradox on 4/3/2017.
 */

public class SecretState extends State implements InputProcessor{

    private static final double DEGREES_TO_RADIANS = (double)(Math.PI/180);
    float angle = (float) (-25*DEGREES_TO_RADIANS);

    Stage stage;
    Image bg;
    Image scroll;
    Box2DDebugRenderer debugRenderer;
    SpriteBatch batch;
    SwipeHandler swipe,swipe2,swipe3,swipe4;
    Texture tex;
    ShapeRenderer shapes;
    public static SwipeTriStrip tris,tris2,tris3,tris4;
    private int inputindex=0;
    World world;
    BodyDef bodyDef;
    BodyEditorLoader loadBody;
    FixtureDef fd;
    Body runemodel;
    ShakeScrn shake;
    private float baseX, baseY;
    float delta;
    private boolean checkdraw=false;
    private boolean playonce=false;
    private boolean shakeonce=false;


    public SecretState(final StateManager manager, final float speed, int level) {
        super(manager);
        //camera.setToOrtho(false, WIDTH/2, HEIGHT/2);
        stage = new Stage();
        //the triangle strip renderer
        tris = new SwipeTriStrip();
        //a swipe handler with max # of input points to be kept alive
        swipe = new SwipeHandler(30);
        //minimum distance between two points
        swipe.minDistance = 10;
        shake = new ShakeScrn(3f,1000f);
        tex = new Texture("swipegradient.png");
        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shapes = new ShapeRenderer();
        batch = new SpriteBatch();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //handle swipe input
        Gdx.input.setInputProcessor(swipe);
        bg = new Image(Assets.ryzesecret);
        scroll = new Image(Assets.runescroll);
        scroll.setPosition(Gdx.graphics.getWidth()/2-350,Gdx.graphics.getHeight()/2-200);
        scroll.setSize(700,600);
        bg.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //bg.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        bg.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveBy(0,-25,1.5f,Interpolation.sineOut),Actions.moveBy(0,25,1.5f,Interpolation.sineOut))));
        stage.addActor(new Background(Assets.nightskyparallax));
        stage.addActor(bg);
        stage.addActor(scroll);

        baseX = camera.position.x;
        baseY = camera.position.y;


        world = new World(new Vector2(WIDTH,HEIGHT),true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(scroll.getWidth()/2-200,scroll.getHeight());
        debugRenderer = new Box2DDebugRenderer();
        loadBody = new BodyEditorLoader(Gdx.files.internal("physics/rune"));
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;
        runemodel = world.createBody(bodyDef);
        //runemodel.setUserData();
        loadBody.attachFixture(runemodel, "Name",fd,800);
        //runemodel.setTransform(runemodel.getPosition(),angle);
        //bodyposOrigin = physicsBodies.getOrigin("rune",400).cpy();
        //bodypos = runeModel.getPosition().sub(bodyposOrigin);

        //runebutton.setPosition(runeModel.getPosition().x,runeModel.getPosition().y);
        //runebutton.setSize(runeModel);


    }

    @Override
    protected void handleInput() {


    }


    @Override
    public void update(float dt) {

        handleInput();
        delta+=Gdx.graphics.getDeltaTime();
        //camera.update();
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
        stage.draw();
        stage.act();
        tex.bind();
        //System.out.println(SwipeHandler.inputindex);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //the endcap scale
        tris.endcap = 20f;
        //the thickness of the line
        tris.thickness = 80f;
        //generate the triangle strip from our path
        tris.update(swipe.path());
        //the vertex color for tinting, i.e. for opacity
        tris.color = Color.WHITE;
        //render the triangles to the screen
        tris.draw(camera);

        //System.out.println(swipe.inputPoints);
        /*if(SwipeHandler.inputindex==0){
            Gdx.input.setInputProcessor(swipe);
        }
        if(SwipeHandler.inputindex==1){
            Gdx.input.setInputProcessor(swipe2);
        }
        if(SwipeHandler.inputindex==2){
            Gdx.input.setInputProcessor(swipe3);
        }
        if(SwipeHandler.inputindex==3){
            Gdx.input.setInputProcessor(swipe4);

        }*/

        /*tris2.thickness = 100f;
        tris2.update(swipe2.path());
        tris2.color = Color.WHITE;
        tris2.draw(camera);

        tris3.thickness = 100f;
        tris3.update(swipe3.path());
        tris3.color = Color.WHITE;
        tris3.draw(camera);

        tris4.thickness = 100f;
        tris4.update(swipe4.path());
        tris4.color = Color.WHITE;
        tris4.draw(camera);*/


        //uncomment to see debug lines
        //drawDebug();
        //shapes();
        checkDraw();
        //batch.begin();
        //debugRenderer.render(world, stage.getCamera().combined);
        //batch.end();

        //stage.draw();
        //stage.act();
    }

    void checkDraw(){
        Array<Vector2> input = swipe.input();
        for(int i=0;i<input.size-1;i++){
            Vector2 p = input.get(i);
            //Vector2 p2 = p2.set(500,1000);
            if(p.x > 325 && p.y > 850 && p.x < 750 && p.y < 1300){
                if(input.size == 20 && !playonce){
                    //stage.getRoot().addAction(Actions.fadeOut(5f));
                    bg.addAction(Actions.fadeOut(5f));
                    scroll.addAction(Actions.fadeOut(5f));
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            //tris.color.a=0;
                        }
                    },3f);
                    playonce=true;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            manager.set(new BattleState(manager,1,1));
                        }
                    },5f);
                    //camera.position.x = baseX;
                    //camera.position.y = baseY;
                    //shake.update(delta, camera);
                }
                checkdraw=true;
               // System.out.println("Drawn");
            }else{
               // System.out.println("not drawn");
            }
        }
    }

    //optional debug drawing..
    void drawDebug() {
        Array<Vector2> input = swipe.input();

        //draw the raw input
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(Color.GRAY);
        for (int i=0; i<input.size-1; i++) {
            Vector2 p = input.get(i);
            Vector2 p2 = input.get(i+1);
            shapes.line(p.x, p.y, p2.x, p2.y);
        }
        shapes.end();

        //draw the smoothed and simplified path
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(Color.RED);
        Array<Vector2> out = swipe.path();
        for (int i=0; i<out.size-1; i++) {
            Vector2 p = out.get(i);
            Vector2 p2 = out.get(i+1);
            shapes.line(p.x, p.y, p2.x, p2.y);
        }
        shapes.end();


        //render our perpendiculars
        shapes.begin(ShapeRenderer.ShapeType.Line);
        Vector2 perp = new Vector2();

        for (int i=1; i<input.size-1; i++) {
            Vector2 p = input.get(i);
            Vector2 p2 = input.get(i+1);

            shapes.setColor(Color.LIGHT_GRAY);
            perp.set(p).sub(p2).nor();
            perp.set(perp.y, -perp.x);
            perp.scl(10f);
            shapes.line(p.x, p.y, p.x+perp.x, p.y+perp.y);
            perp.scl(-1f);
            shapes.setColor(Color.BLUE);
            shapes.line(p.x, p.y, p.x+perp.x, p.y+perp.y);
        }
        shapes.end();
    }

    public void shapes(){
        shapes.setAutoShapeType(true);
        shapes.begin();
        shapes.rotate(scroll.getX(),scroll.getY(),0,90f);
        shapes.rect(scroll.getX(),scroll.getY(),scroll.getWidth()/2,scroll.getHeight());
        shapes.setColor(Color.BLUE);
        shapes.end();
    }
    @Override
    public void dispose() {
        //Gdx.app.log(TAG, "disposed");
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer){
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        return true;
    }

    @Override
    public boolean keyTyped(char character){
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y){
        return false;
    }

    @Override
    public boolean scrolled (int amount){
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button){

        return true;
    }

    @Override
    public boolean keyUp (int keycode){
        return false;
    }

    @Override
    public boolean keyDown (int keycode){
        return false;
    }


}
