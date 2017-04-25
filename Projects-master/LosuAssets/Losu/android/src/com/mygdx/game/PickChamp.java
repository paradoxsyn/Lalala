package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.resources.MusicPlayer;

/**
 * Created by Rabithole on 11/2/2016.
 */

public class PickChamp extends AppCompatActivity implements gameCallback{
    GridView grid;
    int[] imageIDs = {R.drawable.ryzebutton, R.drawable.anniebutton};
    String[] text = {"Ryze","Annie"};

    String newString;
    String response;
    Bundle extras;
    Sound sound;

    float getPlayerPosition;
    //MusicPlayer player = new MusicPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_a_champ);

        ImageAdapter adapter = new ImageAdapter(PickChamp.this, text, imageIDs);
        grid=(GridView)findViewById(R.id.reflexgrid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(PickChamp.this, "You Clicked on " +text[+ position], Toast.LENGTH_SHORT).show();

                ifSelect(text,position,id);


            }
        });
        extras = getIntent().getExtras();
        newString = extras.getString("Res");
        getPlayerPosition = extras.getFloat("Position");

        sound = Gdx.audio.newSound(Gdx.files.internal("music/HeimerWiseCh.ogx"));
        sound.play();
        //System.out.println(getPlayerPosition);

        //player.setPosition(getPlayerPosition);
        //player.setVolume(0.9f);
        //player.play();
    }
    @Override
    protected void onResume(){
        super.onResume();
        //player.play();
    }

    @Override
    protected void onPause(){
        super.onPause();
        //player.pause();
        //extras.putFloat("postback",player.getPosition());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        sound.dispose();
    }

    public void ifSelect(String[] text, int position, long id) {

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;


        if(position==0 && newString.equals("Reaction")) {
            Intent intent = new Intent(getApplicationContext(), Ryze.class);
            startActivity(intent);

        }
        else if(position==0 && newString.equals("Range")) {
            Intent intent = new Intent(getApplicationContext(), com.mygdx.game.Range.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStartActivityA(MusicPlayer player) {
        String strReact = "Reaction";
        Intent intent = new Intent(this, PickChamp.class);
        intent.putExtra("Res",strReact);
        intent.putExtra("Position",player.getPosition());
        startActivity(intent);
    }

    @Override
    public void onStartActivityB(){
        //Intent intent = new Intent(this, ActivityB.class);
        //startActivity(intent);
        //String strRange = "Range";
        //intent.putExtra("Res",strRange);
    }

    @Override
    public void onStartSomeActivity(int someParameter, String someOtherParameter){
        //Intent intent = new Intent(this, ActivityA.class);

        //do whatever you want with the supplied parameters.
        //if (someParameter == 42) {
        //intent.putExtra(MY_EXTRA, someOtherParameter);
        //}
        //startActivity(intent);
    }

    @Override
    public float getPos(){
        //return player.getPosition();
        return 0;
    }


}





