package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import org.w3c.dom.Text;


/**
 * Created by Rabithole on 1/22/2017.
 */

public class Assets {

    public static Texture losubackground;
    public static Texture react;
    public static TextureRegion backgroundRegion;
    public static Drawable but1;
    public static Texture logo;
    public static Texture poro;
    public static TextureAtlas poroall;
    public static TextureRegion poroeye;
    public static TextureRegion poroo;
    public static Animation<TextureRegion> poroanim;
    float w;
    float h;


    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {

        losubackground = loadTexture("heimertitle.jpg");
        react = loadTexture("ryzeicon.jpg");
        logo = loadTexture("losulogo.png");
        //poro = loadTexture("poroo.png");
        poroall = new TextureAtlas(Gdx.files.internal("porodata.txt"));
        poroo = poroall.findRegion("0001");
        poroeye = poroall.findRegion("0002");
        poroanim = new Animation<TextureRegion>(0.33f,poroo,poroeye);
        but1 = new TextureRegionDrawable(new TextureRegion(react));
        backgroundRegion = new TextureRegion(losubackground, 0, 0, 0, 0);

    }
}
