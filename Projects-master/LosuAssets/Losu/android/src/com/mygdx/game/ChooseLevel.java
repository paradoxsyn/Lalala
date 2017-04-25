package com.mygdx.game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rabithole on 1/28/2017.
 */

public class ChooseLevel extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level);

        final Button but1 = (Button) findViewById(R.id.button_1);
        final Button but2 = (Button) findViewById(R.id.button_2);
        final Button but3 = (Button) findViewById(R.id.button_3);
        final Button but4 = (Button) findViewById(R.id.button_4);

        Bundle extras = getIntent().getExtras();

        Typeface customFont1 = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        but1.setTypeface(customFont1);
        but2.setTypeface(customFont1);
        but3.setTypeface(customFont1);
        but4.setTypeface(customFont1);

        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ryze.class);
                startActivity(intent);
            }
        });
    }
}
