package com.example.rabithole.musika;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import static android.provider.MediaStore.*;

public class Musika extends ListActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public ArrayList songsList = new ArrayList();

    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        display = (Button) findViewById(R.id.activity_musika);

        final ArrayList songsListData = new ArrayList<>();


        SongsManager plm = new SongsManager();
        this.songsList = plm.getPlayList();

        // Adding menuItems to ListView
        final ListAdapter adapter = new SimpleAdapter(this, songsListData,
                R.layout.activity_listview, new String[] { "songTitle" }, new int[] {
                R.id.songTitle });



        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);
        //ListView music = (ListView) findViewById(R.id.list_item);


        //music.setAdapter(adapter);



        // looping through playlist
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap song = (HashMap) songsList.get(i);

            // adding HashList to ArrayList
            songsListData.add(song);
        }



        setListAdapter(adapter);

    }



    }




