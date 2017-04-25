package com.mygdx.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.states.PlayState;

/**
 * Created by Paradox on 3/16/2017.
 */

public class EyeParticle extends Image {

    ParticleEffect effect;
    ParticleEmitter emitter;
    public EyeParticle(TextureAtlas atlas){
        //super(new Texture(".png"));
        effect = new ParticleEffect();
        emitter = new ParticleEmitter();
        effect.load(Gdx.files.internal("runes/ryzeyes"), atlas);
        effect.start();
        emitter = effect.getEmitters().first();
        effect.setPosition(this.getWidth()+this.getX(),this.getHeight()+this.getY());
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        effect.update(delta);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        effect.setPosition(this.getWidth()+this.getX(),this.getHeight()+this.getY());

    }

    @Override
    public void setY(float y) {
        super.setY(y);
        effect.setPosition(this.getWidth()+this.getX(),this.getHeight()+this.getY());

    }

    public void flipXY(){
        emitter.getWind().setHigh(250f);
        emitter.getWind().setLow(250);
        emitter.getGravity().setHigh(-100);
        emitter.getGravity().setLow(-200);
        emitter.getScale().setHigh(30);
        //emitter.getTransparency().setHigh(.5f);
    }

    public ParticleEffect getEffect(){
        return effect;
    }

    public ParticleEmitter getEmitter(){
        return emitter;
    }



}
