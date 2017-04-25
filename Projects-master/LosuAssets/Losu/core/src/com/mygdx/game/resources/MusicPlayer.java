package com.mygdx.game.resources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Paradox on 3/27/2017.
 */

public class MusicPlayer implements Music{

    private Music music;

    public MusicPlayer()
    {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/Speedrun.wav"));
        music.setLooping(true);
        music.setVolume(0.9f);
    }

    @Override
    public void dispose(){
        music.dispose();
    }

    @Override
    public void setOnCompletionListener(Music.OnCompletionListener listener){

    }

    @Override
    public void setPan(float pan, float volume){

    }

    @Override
    public void pause(){
        music.pause();
    }

    @Override
    public void play(){
        music.play();
    }

    public void playPos(){
        music.setPosition(music.getPosition());
        music.play();
    }

    @Override
    public boolean isPlaying(){
        return music.isPlaying();
    }

    @Override
    public void setPosition(float position){
        music.setPosition(position);
    }

    @Override
    public float getPosition(){
        return music.getPosition();
    }

    @Override
    public void setVolume(float volume){
        music.setVolume(volume);
    }

    @Override
    public void stop(){

    }

    @Override
    public float getVolume(){
        return music.getVolume();
    }

    @Override
    public boolean isLooping(){
        return music.isLooping();
    }

    @Override
    public void setLooping(boolean isLooping){

    }


}