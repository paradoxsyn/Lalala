package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.resources.MusicPlayer;
import com.mygdx.game.states.StateManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class LosuScrn extends ScreenAdapter {

	LosuGame game;
	Rectangle reactionBounds;
	Rectangle aboutBounds;
	Rectangle rangeBounds;
	Rectangle psychBounds;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	Stage stage;
	private BitmapFont font;
	private TextureAtlas buttonsAtlas; //** image of buttons **//
	private Skin buttonSkin; //** images are used as skins of the button **//
	private TextButton button;
	private TextButton button2;
	private TextButton button3;
	private TextButton button4;
	float w;
	float h;
	Image logo;
	float elapsedTime = 0f;
	float stateTime;
	Vector2 startPosition = new Vector2(), targetPosition = new Vector2(), position = new Vector2();
	com.mygdx.game.animations.AnimatedImage test;

	private TextureAtlas poroatlas;
	private TextureRegion poroo;
	private TextureRegion poroeye;
	private Animation<TextureRegion> poroanim;

	public LosuScrn(LosuGame game){
		this.game = game;
	}

	@Override
	public void show() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/IndieFlower.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		poroatlas = game.manager.get("porodata.txt", TextureAtlas.class);
		poroo = poroatlas.findRegion("0001");
		poroeye = poroatlas.findRegion("0002");
		poroanim = new Animation<TextureRegion>(1.5f,poroo,poroeye);

		buttonsAtlas = new TextureAtlas("button.pack"); //**button atlas image **//
		buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
		fontParameter.size = 54;
		font = fontGenerator.generateFont(fontParameter);
		//font = new BitmapFont(Gdx.files.internal("new.fnt"),false); //** font **//
		stage = new Stage();        //** window is stage **//
		//Image bg = new Image(com.mygdx.game.resources.Assets.losubackground);
		Image bg = new Image(game.manager.get("heimertitle.jpg",Texture.class));
		stage.clear();
		bg.setBounds(0,0,w,h);
		stage.addActor(bg);
		Gdx.input.setInputProcessor(stage); //** stage is responsive **//

		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
		style.up = buttonSkin.getDrawable("buttonOff");
		style.down = buttonSkin.getDrawable("buttonOn");

		style.font = font;

		button = new TextButton("Reactions", style);
		button2 = new TextButton("Psych",style);
		button3 = new TextButton("Range",style);
		button4 = new TextButton("About",style);
		//** Button text and style **//
		button.setHeight(Gdx.graphics.getHeight()/3); //** Button Height **//
		button.setWidth(Gdx.graphics.getWidth()/3); //** Button Width **//
		button2.setHeight(h/3);
		button2.setWidth(w/3);
		button3.setHeight(h/3);
		button3.setWidth(w/3);
		button4.setHeight(h/3);
		button4.setWidth(w/3);

		button.setPosition(Gdx.graphics.getWidth()/3+button.getWidth()/2, Gdx.graphics.getHeight()/6+ Gdx.graphics.getHeight()/18);
		button2.setPosition(Gdx.graphics.getWidth()/4+button.getWidth(), Gdx.graphics.getHeight()/36+ Gdx.graphics.getHeight()/108);
		button3.setPosition(Gdx.graphics.getWidth()/3-button.getWidth()/2, Gdx.graphics.getHeight()/6+ Gdx.graphics.getHeight()/18);
		button4.setPosition(Gdx.graphics.getWidth()/4-button.getWidth()/2, Gdx.graphics.getHeight()/36+ h/108);

		//logo = new Image(com.mygdx.game.resources.Assets.logo);
		logo = new Image(game.manager.get("losulogo.png",Texture.class));
		logo.setPosition(w/2-w/8,h/2+h/3,Align.center);
		logo.setScale(1.5f,1.5f);

		final MoveToAction poromoveup = new MoveToAction();
		poromoveup.setPosition(w-200,h/2-h/2);
		poromoveup.setDuration(1.0f);

		final MoveToAction poromovedown = new MoveToAction();
		poromovedown.setPosition(w-200,h/2-h/2-200);
		poromovedown.setDuration(1.0f);


		MoveToAction movedown = new MoveToAction();
		movedown.setPosition(w/2-w/8, h/2+h/4+h/10, Align.center);
		movedown.setDuration(0.8f);

		MoveToAction moveup = new MoveToAction();
		moveup.setPosition(w/2-w/8, h/2+h/3,Align.center);
		moveup.setDuration(0.8f);

		SequenceAction seq = new SequenceAction(moveup,movedown);
		SequenceAction poroseq = new SequenceAction(poromoveup,poromovedown);

		RepeatAction logmove = new RepeatAction();
		logmove.setAction(seq);
		logmove.setCount(RepeatAction.FOREVER);


		logo.addAction(logmove);


		button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
				return true;

			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("my app", "Rggggggeleased");

				game.bounceback = true;
				game.callBackListener();

				//stage.clear();
				//stage.dispose();


			}
		});

		//test = new com.mygdx.game.animations.AnimatedImage(com.mygdx.game.resources.Assets.poroanim);
		test = new com.mygdx.game.animations.AnimatedImage(poroanim);
		test.addAction(repeat(RepeatAction.FOREVER,sequence(delay(30f),poromoveup,delay(4f),poromovedown,delay(30f))));

		test.setPosition(w/2+300, 0-200);
		stage.addActor(test);
		stage.addActor(button);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		stage.addActor(logo);



	}


	public void update() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		//if(Gdx.input.justTouched()) {

			//guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));


			//if (reactionBounds.contains(touchPoint.x, touchPoint.y)) {
			//	System.out.println("FK");
			//}
		//}

	}


	public void draw() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//guiCam.update();
		game.batcher.begin();
		stage.draw();
		stage.act();
		game.batcher.end();

	}
	@Override
	public void render(float delta) {
		update();
		draw();

	}

}

