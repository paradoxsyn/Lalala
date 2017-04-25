package com.mygdx.game.resources;

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
    public static Texture ryzebackground;
    public static Texture ryzebacktwo;
    public static Texture ryzebacktwo2;
    public static Texture ryzebacktwo3;
    public static Texture ryzebacktwo4;
    public static Texture react;
    public static Texture point;
    public static Texture noextras;
    public static Texture reversepoint;
    public static Texture overload;
    public static TextureRegion backgroundRegion;
    public static Drawable but1;
    public static Texture logo;
    public static Texture poro;
    public static Texture signpost;
    public static Texture runepris;
    public static Texture spellflux;
    public static Texture realmwarp;
    //public static Texture realmcircle;
    public static Texture scorebg;
    public static Texture scoretitle;
    public static Texture finishbutton;
    public static Texture ranka,rankb,rankc,ranks;
    public static Texture talonwall1,talonwall2;
    public static Texture secretbutton;

    public static Texture talon;
    public static Texture tahm;
    public static Texture renekton;
    public static Texture annie;
    public static Texture aurelion;
    public static Texture ryze;
    public static Texture rengar;

    public static TextureAtlas poroall;
    public static TextureAtlas tahmall;
    public static TextureAtlas prisall;
    public static TextureAtlas loader;
    public static TextureAtlas spellfluxall;
    public static TextureAtlas realmcircleall;
    public static TextureAtlas tahmbubbleall;
    public static TextureAtlas talondagsall;
    public static TextureAtlas talonultall;
    public static TextureAtlas runeprisall;
    public static TextureAtlas overloadall;
    public static TextureAtlas lightbeamall;
    public static TextureAtlas auraall;
    public static TextureAtlas clawall;
    public static TextureAtlas tahmultall;
    public static TextureAtlas realmwarpall;
    public static TextureAtlas ashearrowall;
    public static TextureAtlas runebossall;
    public static TextureAtlas bossbeamall;
    public static TextureAtlas laserall;
    public static TextureAtlas runedamageall;

    public static TextureRegion poroeye;
    public static TextureRegion poroo;
    public static TextureRegion ryzeflip,ryzeflip2,ryzeflip3,ryzeflip4,ryzeflip5;
    public static TextureRegion loaderempty;
    public static TextureRegion loaderfull;

    public static TextureRegion realmcircle;
    public static TextureRegion realmcircle2;
    public static TextureRegion realmcircle3;
    public static TextureRegion realmcircle4;
    public static TextureRegion realmcircle5;
    public static TextureRegion realmcircle6;
    public static TextureRegion realmcircle7;
    public static TextureRegion realmcircle8;
    public static TextureRegion realmcircle9;
    public static TextureRegion realmcircle10;
    public static TextureRegion realmcircle11;
    public static TextureRegion realmcircle12;
    public static TextureRegion realmcircle13;

    //Animations
    public static TextureRegion tahm1;
    public static TextureRegion tahm2;
    public static TextureRegion tahm3;
    public static TextureRegion tahm4;

    public static TextureRegion pris1,pris2,pris3,pris4,pris5;
    public static TextureRegion loader1,loader2,loader3,loader4,loader5,loader6,loader7,loader8,loader9,loader10,loader11,loader12,loader13,loader14,loader15,loader16,loader17,loader18,loader19,loader20,loader21,loader22,loader23,loader24,loader25,loader26,loader27,loader28,loader29,loader30,loader31,loader32,loader33,loader34,loader35,loader36,loader37;
    public static TextureRegion flux1,flux2;
    public static TextureRegion runepris1,runepris2;
    public static TextureRegion overload1,overload2;
    public static TextureRegion realmwarp1,realmwarp2;
    public static TextureRegion nightskyparallax;
    public static Texture runebattle;

    public static Texture rune1;
    public static Texture rune2;
    public static Texture rune3;
    public static Texture rune4;
    public static Texture rune5;
    public static Texture rune6,rune7,rune8;
    public static Texture startbutton;
    //Before levels are unlocked
    public static Texture runebutton;
    public static Texture runebutton2;
    public static Texture runebutton3;
    //After levels are unlocked
    public static Texture runebuttonfull;
    public static Texture runebutton2full;
    public static Texture runebutton3full;
    public static Texture ryzesecret;
    public static Texture nightsky;
    public static Texture runescroll;

    public static Animation<TextureRegion> poroanim;
    public static Animation<TextureRegion> tahmanim;
    public static Animation<TextureRegion> prisanim;
    public static Animation<TextureRegion> loaderanim;
    public static Animation<TextureRegion> fluxanim;
    public static Animation<TextureRegion> realmanim;
    public static Animation<TextureAtlas.AtlasRegion> tahmbubble;
    public static Animation<TextureAtlas.AtlasRegion> talondags;
    public static Animation<TextureAtlas.AtlasRegion> talonult;
    public static Animation<TextureAtlas.AtlasRegion> lightbeam;
    public static Animation<TextureAtlas.AtlasRegion> aura;
    public static Animation<TextureAtlas.AtlasRegion> claw;
    public static Animation<TextureAtlas.AtlasRegion> tahmult;
    public static Animation<TextureAtlas.AtlasRegion> ashearrow;
    public static Animation<TextureAtlas.AtlasRegion> runeboss;
    public static Animation<TextureAtlas.AtlasRegion> bossbeam;
    public static Animation<TextureAtlas.AtlasRegion> laser;
    public static Animation<TextureAtlas.AtlasRegion> runedamage;

    float w;
    float h;

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {

        losubackground = loadTexture("heimertitle.jpg");
        runescroll = loadTexture("runescroll.png");
        ryzebackground = loadTexture("ryzeclassic.jpg");
        startbutton = loadTexture("runes/ryzebuttondownrune.png");
        nightsky = loadTexture("nightsky.png");
        runepris = loadTexture("runeprispng.png");
        scorebg = loadTexture("ScoreScrn.png");
        point = loadTexture("followpoint.png");
        ryzesecret = loadTexture("ryzesecret.png");
        noextras = loadTexture("nomulti.png");
        ranka = loadTexture("ranka.png");
        rankb = loadTexture("rankb.png");
        rankc = loadTexture("rankc.png");
        ranks = loadTexture("ranks.png");
        reversepoint = loadTexture("followpointreverse.png");
        finishbutton = loadTexture("finishbutton.png");
        scoretitle = loadTexture("ScoreTitle.png");
        ryzebacktwo = loadTexture("ryzerange.jpg");
        ryzebacktwo2 = loadTexture("ryzerangeyellow.png");
        ryzebacktwo3 = loadTexture("ryzerangered.png");
        ryzebacktwo4 = loadTexture("ryzerangesolar.png");
        overload = loadTexture("overloadpng.png");
        spellflux = loadTexture("spellfluxpng.png");
        realmwarp = loadTexture("realmwarppng.png");
        //realmcircle = loadTexture("realmcircle.png");
        secretbutton = loadTexture("questionmark.png");
        react = loadTexture("ryzeicon.jpg");
        logo = loadTexture("losulogo.png");
        talon = loadTexture("Talon.png");
        tahm = loadTexture("TahmKench.png");
        renekton = loadTexture("Renekton.png");
        aurelion = loadTexture("Aurelion.png");
        rengar = loadTexture("Rengar.png");
        ryze = loadTexture("Ryze.png");
        annie = loadTexture("Annie.png");
        rune1 = loadTexture("runes/rune1lvl.png");
        rune2 = loadTexture("runes/runeLlvl.png");
        rune3 = loadTexture("runes/runeElvl.png");
        rune4 = loadTexture("runes/runeVlvl.png");
        rune5 = loadTexture("runes/runeL2lvl.png");
        rune6 = loadTexture("runes/rune2lvl.png");
        rune7 = loadTexture("runes/rune3lvl.png");
        rune8 = loadTexture("runes/rune4lvl.png");
        talonwall1 = loadTexture("talonwalls/talonwall_1.png");
        talonwall2 = loadTexture("talonwalls/talonwall_2.png");
        runebutton = loadTexture("runes/rune3blackhighlight.png");
        runebutton2 = loadTexture("runes/rune8blackhighlight.png");
        runebutton3 = loadTexture("runes/rune9blackhighlight.png");
        runebuttonfull = loadTexture("runes/rune3glow.png");
        runebutton2full = loadTexture("runes/rune8glow.png");
        runebutton3full = loadTexture("runes/rune9glow.png");
        poroall = new TextureAtlas(Gdx.files.internal("porodata.txt"));
        tahmall = new TextureAtlas(Gdx.files.internal("tahmdata.txt"));
        prisall = new TextureAtlas(Gdx.files.internal("runes/runeprisanim/runepris.txt"));
        loader =  new TextureAtlas(Gdx.files.internal("runes/runebaranim/runeload.txt"));
        tahmbubbleall = new TextureAtlas(Gdx.files.internal("tahmbubble/tahmbubble.txt"));
        spellfluxall = new TextureAtlas(Gdx.files.internal("spellfluxbutton.txt"));
        realmcircleall = new TextureAtlas(Gdx.files.internal("runes/realmcircleanim/realmcircle.txt"));
        talondagsall = new TextureAtlas(Gdx.files.internal("talondags/talondagsanim.txt"));
        talonultall = new TextureAtlas(Gdx.files.internal("talondags/talonult.txt"));
        lightbeamall = new TextureAtlas(Gdx.files.internal("lightbeam/lightbeam.txt"));
        auraall = new TextureAtlas(Gdx.files.internal("aura/aura.txt"));
        clawall = new TextureAtlas(Gdx.files.internal("rengclaw/claw.txt"));
        tahmultall = new TextureAtlas(Gdx.files.internal("tahmult/tahmult.txt"));
        realmwarpall = new TextureAtlas(Gdx.files.internal("realmwarp.txt"));
        runeprisall = new TextureAtlas(Gdx.files.internal("runeprisbutton.txt"));
        overloadall = new TextureAtlas(Gdx.files.internal("overloadbutton.txt"));
        ashearrowall = new TextureAtlas(Gdx.files.internal("ashearrow/ashearrow.txt"));
        runebossall = new TextureAtlas(Gdx.files.internal("runes/bigruneanim/runeboss.txt"));
        bossbeamall = new TextureAtlas(Gdx.files.internal("runes/bigruneanim/bossbeam.txt"));
        laserall = new TextureAtlas(Gdx.files.internal("runes/bigruneanim/lasers.txt"));
        runedamageall = new TextureAtlas(Gdx.files.internal("runes/bigruneanim/runedamage.txt"));
        runepris1 = new TextureRegion(runeprisall.findRegion("runeprispng"));
        runepris2 = new TextureRegion(runeprisall.findRegion("runeprispngdark"));
        overload1 = new TextureRegion(overloadall.findRegion("overloadpng"));
        overload2 = new TextureRegion(overloadall.findRegion("overloadpngdark"));
        realmwarp1 = new TextureRegion(realmwarpall.findRegion("realmwarppng",-1));
        realmwarp2 = new TextureRegion(realmwarpall.findRegion("realmwarppng",2));
        nightskyparallax = new TextureRegion(nightsky);
        runebattle = loadTexture("runebattlebackground.png");
        poroo = poroall.findRegion("0001");
        poroeye = poroall.findRegion("0002");
        tahm1 = tahmall.findRegion("TahmKenchCrack");
        tahm2 = tahmall.findRegion("TahmKenchCrack2");
        tahm3 = tahmall.findRegion("TahmKenchCrack3");
        tahm4 = tahmall.findRegion("TahmKenchCrack4");
        pris1 = prisall.findRegion("prisanim1");
        pris2 = prisall.findRegion("prisanim2");
        pris3 = prisall.findRegion("prisanim3");
        pris4 = prisall.findRegion("prisanim4");
        pris5 = prisall.findRegion("prisanim5");
        flux1 = spellfluxall.findRegion("spellfluxpng",-1);
        flux2 = spellfluxall.findRegion("spellfluxpng",2);
        loader1 = loader.findRegion("loadbar1");
        loader2 = loader.findRegion("loadbar2");
        loader3 = loader.findRegion("loadbar3");
        loader4 = loader.findRegion("loadbar4");
        loader5 = loader.findRegion("loadbar4");
        loader6 = loader.findRegion("loadbar5",-1);
        loader7 = loader.findRegion("loadbar5",1);
        loader8 = loader.findRegion("loadbar5",2);
        loader9 = loader.findRegion("loadbar5",3);
        loader10 = loader.findRegion("loadbar6");
        loader11 = loader.findRegion("loadbar8",-1);
        loader12 = loader.findRegion("loadbar8",1);
        loader13 = loader.findRegion("loadbar9",-1);
        loader14 = loader.findRegion("loadbar9",1);
        loader15 = loader.findRegion("loadbar11",1);
        loader16 = loader.findRegion("loadbar11",2);
        loader17 = loader.findRegion("loadbar11",3);
        loader18 = loader.findRegion("loadbar11",4);
        loader19 = loader.findRegion("loadbar11",5);
        loader20 = loader.findRegion("loadbar12");
        loader21 = loader.findRegion("loadbar14",-1);
        loader22 = loader.findRegion("loadbar14",1);
        loader23 = loader.findRegion("loadbar14",2);
        loader24 = loader.findRegion("loadbar14",3);
        loader25 = loader.findRegion("loadbar15",-1);
        loader26 = loader.findRegion("loadbar15",1);
        loader27 = loader.findRegion("loadbar15",2);
        loader28 = loader.findRegion("loadbar16");
        loader29 = loader.findRegion("loadbar17");
        loader30 = loader.findRegion("loadbar18");
        loader31 = loader.findRegion("loadbar19");
        loader32 = loader.findRegion("loadbar20");
        loader33 = loader.findRegion("loadbar22");
        loader34 = loader.findRegion("loadbar23");
        loader35 = loader.findRegion("loadbar24");
        loader36 = loader.findRegion("loadbar25");
        loader37 = loader.findRegion("loadbar26");
        loaderempty = loader.findRegion("loadbar");
        loaderfull = loader.findRegion("loadbarfull");

        realmcircle = realmcircleall.findRegion("realmcircle",1);
        realmcircle2 = realmcircleall.findRegion("realmcircle",2);
        realmcircle3 = realmcircleall.findRegion("realmcircle",3);
        realmcircle4 = realmcircleall.findRegion("realmcircle",4);
        realmcircle5 = realmcircleall.findRegion("realmcircle",5);
        realmcircle6 = realmcircleall.findRegion("realmcircle",6);
        realmcircle7 = realmcircleall.findRegion("realmcircle",7);
        realmcircle8 = realmcircleall.findRegion("realmcircle",8);
        realmcircle9 = realmcircleall.findRegion("realmcircle",9);
        realmcircle10 = realmcircleall.findRegion("realmcircle",10);
        realmcircle11 = realmcircleall.findRegion("realmcircle",11);
        realmcircle12 = realmcircleall.findRegion("realmcircle",12);
        realmcircle13 = realmcircleall.findRegion("realmcircle",13);

        poroanim = new Animation<TextureRegion>(1.5f,poroo,poroeye);
        tahmanim = new Animation<TextureRegion>(0.5f,tahm1,tahm2,tahm3,tahm4);
        prisanim = new Animation<TextureRegion>(.1f,pris1,pris2,pris3,pris4,pris5);
        loaderanim = new Animation<TextureRegion>(.2f,loaderempty,loader1,loader2,loader3,loader4,loader5,loader6,loader7,loader8,loader9,loader10,loader11,loader12,loader13,loader14,loader15,loader16,loader17,loader18,loader19,loader20,loader21,loader22,loader23,loader24,loader25,loader26,loader27,loader28,loader29,loader30,loader31,loader32,loader33,loader34,loader35,loader36,loader37,loaderfull);
        fluxanim = new Animation<TextureRegion>(1f,flux1,flux2);
        realmanim = new Animation<TextureRegion>(.2f,realmcircle,realmcircle2,realmcircle3,realmcircle4,realmcircle5,realmcircle6,realmcircle7,realmcircle8,realmcircle9,realmcircle10,realmcircle11,realmcircle12,realmcircle13);
        tahmbubble = new Animation<TextureAtlas.AtlasRegion>(.1f,tahmbubbleall.findRegions("tahmbubble"));
        talondags = new Animation<TextureAtlas.AtlasRegion>(.3f, talondagsall.findRegions("talondags"));
        talonult = new Animation<TextureAtlas.AtlasRegion>(.3f, talonultall.findRegions("talonult"));
        lightbeam = new Animation<TextureAtlas.AtlasRegion>(.1f, lightbeamall.findRegions("Lightbeam"));
        aura = new Animation<TextureAtlas.AtlasRegion>(.2f, auraall.findRegions("aura"));
        claw = new Animation<TextureAtlas.AtlasRegion>(.2f, clawall.findRegions("claw"));
        tahmult = new Animation<TextureAtlas.AtlasRegion>(.2f, tahmultall.findRegions("tahmult"));
        ashearrow = new Animation<TextureAtlas.AtlasRegion>(.2f, ashearrowall.findRegions("ashearrow"));
        runeboss = new Animation<TextureAtlas.AtlasRegion>(.2f, runebossall.findRegions("bigrune"));
        bossbeam = new Animation<TextureAtlas.AtlasRegion>(.2f, bossbeamall.findRegion("bossbeam",6),bossbeamall.findRegion("bossbeam",8),bossbeamall.findRegion("bossbeam",8),bossbeamall.findRegion("bossbeam",8),bossbeamall.findRegion("bossbeam",7));
        runedamage = new Animation<TextureAtlas.AtlasRegion>(.1f, runedamageall.findRegions("bigrune"));
        but1 = new TextureRegionDrawable(new TextureRegion(react));
        backgroundRegion = new TextureRegion(losubackground, 0, 0, 0, 0);
        ryzeflip = new TextureRegion(ryzebacktwo,0,0,ryzebacktwo.getWidth(),ryzebacktwo.getHeight());
        ryzeflip2 = new TextureRegion(ryzebacktwo2,0,0,ryzebacktwo2.getWidth(),ryzebacktwo2.getHeight());
        ryzeflip3 = new TextureRegion(ryzebacktwo3,0,0,ryzebacktwo3.getWidth(),ryzebacktwo3.getHeight());
        ryzeflip4 = new TextureRegion(ryzebacktwo4,0,0,ryzebacktwo4.getWidth(),ryzebacktwo4.getHeight());
        ryzeflip4.flip(true,false);
        ryzeflip3.flip(true,false);
        ryzeflip2.flip(true,false);
        ryzeflip.flip(true,false);

    }
}
