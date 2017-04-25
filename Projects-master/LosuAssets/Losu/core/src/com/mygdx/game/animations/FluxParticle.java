package com.mygdx.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Paradox on 3/22/2017.
 */

public class FluxParticle extends Image {

    ParticleEffect effect;
    ParticleEmitter emitter;

    public FluxParticle(TextureAtlas atlas){
        effect = new ParticleEffect();
        emitter = new ParticleEmitter();
        effect.load(Gdx.files.internal("buttonemitter"), atlas);
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

    public ParticleEffect getEffect(){
        return effect;
    }

    public ParticleEmitter getEmitter(){
        return emitter;
    }

}
