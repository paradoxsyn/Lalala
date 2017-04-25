package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.ui.SimpleDirectionGestureDetector;
import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

import org.w3c.dom.Text;


/**
 * Created by Paradox on 3/22/2017.
 */

public class ScoreScrn3 extends State{

    Stage stage;
    com.badlogic.gdx.scenes.scene2d.ui.Image bg;
    Image title;
    Image finish;
    Image sidepoint;
    Image sidepointrev;
    Image nomultitime,nomultifocus,nomultistyle;
    Image ranka,rankb,rankc,ranks;
    Preferences prefs = Gdx.app.getPreferences("Ryze"); //add name string
    long crystals;
    long crystals2;
    long crystals3;
    long crystals4;

    private long multiplier;
    private long multiplier2;
    private long multiplier3;

    private ShapeRenderer renderer;

    private BitmapFont font;
    private BitmapFont font2;
    private Skin skin;
    private TextField text;
    private TextField text2;
    private TextField text3;
    private TextField text4;
    private TextField text5;
    private TextField score1;
    private TextField score2;
    private TextField score3;
    private TextField score4;
    private TextField.TextFieldStyle style;
    private TextField.TextFieldStyle style2;


    public ScoreScrn3(final StateManager manager){
        super(manager);
        stage = new Stage();
        renderer = new ShapeRenderer();
        bg = new Image(Assets.scorebg);
        title = new Image(Assets.scoretitle);
        finish = new Image(Assets.finishbutton);
        sidepoint = new Image(Assets.point);
        sidepointrev = new Image(Assets.reversepoint);
        nomultitime = new Image(Assets.noextras);
        nomultifocus = new Image(Assets.noextras);
        nomultistyle = new Image(Assets.noextras);
        ranka = new Image(Assets.ranka);
        rankb = new Image(Assets.rankb);
        rankc = new Image(Assets.rankc);
        ranks = new Image(Assets.ranks);
        font = new BitmapFont();
        font2 = new BitmapFont();
        bg.setBounds(0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        title.setPosition(Gdx.graphics.getWidth()/2-250,Gdx.graphics.getHeight()/2+500);
        title.setSize(500,350);
        title.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
        finish.setSize(300,300);
        finish.setPosition(title.getX()+100,title.getY()-1300);
        //Gdx.input.setInputProcessor(stage);
        InputMultiplexer multi = new InputMultiplexer();
        SimpleDirectionGestureDetector list = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onLeft() {
                manager.set(new ScoreScrn4(manager));
            }

            @Override
            public void onRight() {
                //System.out.println("thisworkeds");
                //you may actually swipe right
                manager.set(new ScoreScrn2(manager));
            }

            @Override
            public void onUp() {

            }

            @Override
            public void onDown() {

            }
        });
        sidepoint.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //manager.set(new GameState(manager,speed,lvl));
                manager.set(new ScoreScrn4(manager));
                //System.out.println("Thisworked");
                return false;
            }
        });
        sidepointrev.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //manager.set(new GameState(manager,speed,lvl));
                manager.set(new ScoreScrn2(manager));
                //System.out.println("Thisworked");
                return false;
            }
        });

        multi.addProcessor(stage);
        multi.addProcessor(list);
        Gdx.input.setInputProcessor(multi);

        crystals = prefs.getLong("crystals",0);
        crystals2 = prefs.getLong("crystals2",0);
        crystals3 = prefs.getLong("crystals3",0);
        crystals4 = prefs.getLong("crystals4",0);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/IndieFlower.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 120;
        FreeTypeFontGenerator fontGenerator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/IndieFlower.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter2.size = 80;
        font = fontGenerator.generateFont(fontParameter);
        font2 = fontGenerator2.generateFont(fontParameter2);
        style = new TextField.TextFieldStyle();
        style.font = font;
        style.fontColor = Color.WHITE;
        style2 = new TextField.TextFieldStyle();
        style2.font = font2;
        style2.fontColor = Color.SLATE;

        text = new TextField("Scene 3:",style);
        text2 = new TextField("Crystals",style2);
        text3 = new TextField("Time",style2);
        text4 = new TextField("Focus",style2);
        text5 = new TextField("Style",style2);

        score1 = new TextField(""+crystals3,style2);
        //score2 = new TextField("x"+multiplier,style2);
        //score3 = new TextField("x"+multiplier2,style2);
        //score4 = new TextField("x"+multiplier3,style2);

        text.setSize(500,100);
        text2.setSize(500,100);
        text3.setSize(500,100);
        text4.setSize(500,100);
        text5.setSize(500,100);

        sidepoint.setPosition(bg.getWidth()-100,bg.getHeight()/2);
        sidepoint.setSize(30,30);
        sidepoint.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.scaleBy(.5f,.5f,1f),Actions.scaleBy(-.5f,-.5f,1f))));

        sidepointrev.setPosition(bg.getX()+100,bg.getHeight()/2);
        sidepointrev.setSize(30,30);
        sidepointrev.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.scaleBy(.5f,.5f,1f),Actions.scaleBy(-.5f,-.5f,1f))));

        score1.setSize(500,100);
        //score2.setSize(500,100);
        //score3.setSize(500,100);
        //score4.setSize(500,100);

        text.setPosition(title.getX()+50,title.getY()-100); //scene
        text2.setPosition(text.getX()-150,text.getY()-200); //crystals
        text3.setPosition(text2.getX()+100,text2.getY()-300); //time
        text4.setPosition(text3.getX(),text3.getY()-100); //focus
        text5.setPosition(text4.getX(),text4.getY()-100); //style

        score1.setPosition(text2.getX()+500,text2.getY()); //crystals
        //score2.setPosition(text3.getX()+350,text3.getY()); //time
        nomultitime.setPosition(text3.getX()+350,text3.getY());
        //score3.setPosition(text4.getX()+350,text4.getY()); //focus
        nomultifocus.setPosition(text4.getX()+350,text4.getY());
        //score4.setPosition(text5.getX()+350,text5.getY());
        nomultistyle.setPosition(text5.getX()+350,text5.getY());


        text.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2.5f)));
        text2.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2.8f)));
        text3.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3.1f)));
        text4.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3.4f)));
        text5.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3.4f)));

        score1.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2.5f)));
        nomultitime.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2.8f)));
        nomultifocus.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3.1f)));
        nomultistyle.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(3.4f)));

        finish.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //manager.set(new GameState(manager,speed,lvl));
                manager.set(new LevelState(manager));
                return false;
            }
        });

        stage.addActor(bg);
        stage.addActor(title);
        stage.addActor(text);
        stage.addActor(sidepoint);
        stage.addActor(sidepointrev);
        stage.addActor(text2);
        stage.addActor(text3);
        stage.addActor(text4);
        stage.addActor(text5);
        stage.addActor(score1);
        stage.addActor(nomultitime);
        stage.addActor(nomultifocus);
        stage.addActor(nomultistyle);
        stage.addActor(finish);
    }


    @Override
    public void handleInput(){

    }

    @Override
    public void update(float delta){
        stage.act(delta);

        //if(score4.getColor().a == 1){

        //}

    }

    public void drawLines(){
        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.rect(bg.getX()+150,score1.getHeight()+400,bg.getWidth()-300,bg.getHeight()-1150,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE);
        renderer.line(title.getX()-200,title.getY()-100,title.getX()+750,title.getY()-100,Color.WHITE,Color.WHITE);
        renderer.end();
    }

    @Override
    public void render(){
        stage.draw();
        drawLines();
    }

    @Override
    public void dispose(){

    }

}
