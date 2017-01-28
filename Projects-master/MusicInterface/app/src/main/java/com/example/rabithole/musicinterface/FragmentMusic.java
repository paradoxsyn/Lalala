package com.example.rabithole.musicinterface;

/**
 * Created by Rabithole on 10/1/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.rabithole.musicinterface.R.layout.activity_main;


public class FragmentMusic extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate
        return inflater.inflate(activity_main, container, false);
    }
}
