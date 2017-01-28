package com.example.rabithole.musicinterface;

import android.app.Activity;
import android.app.Dialog;
import android.os.Environment;
import android.widget.TextView;

import java.util.ArrayList;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * Created by Rabithole on 10/5/2016.
 */

public class SongsManager extends Activity {

    // SDCard Path
    //choose your path for me i choose sdcard
    String MEDIA_PATH = "/mnt/";
    private ArrayList songsList = new ArrayList<>();

    // Constructor
    public SongsManager(){

    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public ArrayList getPlayList(){
        File home = new File(MEDIA_PATH);
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap song = new HashMap();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());

                // Adding each song to SongList
                songsList.add(song);
            }
        } else {
           setContentView(R.layout.activity_main);
        }
        // return songs list array
        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    //you can choose the filter for me i put .mp3
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}
