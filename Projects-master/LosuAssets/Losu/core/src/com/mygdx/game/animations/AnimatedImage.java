package com.mygdx.game.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Paradox on 1/27/2017.
 */
public class AnimatedImage extends Image
    {
        protected Animation<TextureRegion> animation = null;
        private float stateTime = 0;

        public AnimatedImage(Animation<TextureRegion> animation) {
            super(animation.getKeyFrame(0));
            this.animation = animation;
        }


        @Override
        public void act(float delta)
        {
            ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
            super.act(delta);

        }

        public void act2(float delta, float counter){
           //if(event) {
            ((TextureRegionDrawable) getDrawable()).setRegion(animation.getKeyFrame((Interpolation.linear.apply(counter * delta)), true));
           //}
            super.act(delta);
            //actor.addAction(Actions.sequence(Actions.moveBy(0,-25),Actions.moveBy(0,25)));
        }


        public Animation<TextureRegion> getAnimation(){
            return this.animation;
        }

        public float getStateTime(){
            return stateTime;
        }

    }

