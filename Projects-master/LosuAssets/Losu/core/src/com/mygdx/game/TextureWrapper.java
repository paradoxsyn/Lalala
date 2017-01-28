package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rabithole on 1/27/2017.
 */

public class TextureWrapper{
    public TextureRegion textureRegion;
    public Texture texture;
    public Vector2 Position;
    int width;
    int height;
    float scaleX;
    float scaleY;
    int originX;
    int originY;
    public TextureWrapper(TextureAtlas atlas, String regionName, Vector2 pos){
        //For above textures reigon names would be name of png like Animation1,Animation2 etc
        textureRegion = atlas.findRegion(regionName);
        Position=pos;
        //width=texture.getRegionWidth()/2;
        //height=texture.getRegionHeight()/2;
        //Origin is the pivot for rotation. width/2 and height/2 set image's center as pivot for rotation
        originX=width/2;
        originY=height/2;
        scaleX=1;
        scaleY=1;
    }

    public void Draw(SpriteBatch sp){
        sp.draw(texture,
                Position.x-width/2,   //To position it in center horizontally
                Position.y-height/2,  //To position it in center vertically
                originX,
                originY,
                width,    //width of texture
                height,    //height of texture
                scaleX,   //Negative value will flip the texture horizontally
                scaleY   //Negative value will flip the texture vertically
                //Rotation //Rotation angle in degrees
        );
    }

}