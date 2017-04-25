package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.mygdx.game.animations.AnimatedImage2;
import com.mygdx.game.resources.Assets;

/**
 * Created by Paradox on 4/5/2017.
 */

public class RuneBawss extends Actor {


    private float posX,posY;
    private AnimatedImage2 bawss;

    public RuneBawss(float posX, float posY, Stage stg){

        bawss = new AnimatedImage2(Assets.runeboss);
        this.posX = posX;
        this.posY = posY;
        bawss.setPosition(posX,posY);
        bawss.setSize(300,300);
        bawss.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.moveBy(0,-50,1.5f, Interpolation.exp5),Actions.moveBy(0,50,1.5f,Interpolation.exp5))));
        stg.addActor(bawss);
    }

    public void moveAround(float posX, float posY, float duration, Interpolation inter){
        bawss.addAction(Actions.sequence(Actions.repeat(RepeatAction.FOREVER,Actions.moveTo(posX,posY,duration,inter)),Actions.moveTo(-posX,-posY,duration,inter)));
    }

    public AnimatedImage2 getImage(){
        return bawss;
    }


}
