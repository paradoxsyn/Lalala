package dj.rabithole.losu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Rabithole on 11/2/2016.
 */

public class PickChamp extends AppCompatActivity{
    GridView grid;
    int[] imageIDs = {R.drawable.ryzebutton, R.drawable.anniebutton};
    String[] text = {"Ryze","Annie"};

    String newString;
    String response;



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



            Bundle extras = getIntent().getExtras();
            newString = extras.getString("Res");


    }

    public void ifSelect(String[] text, int position, long id) {


        if(position==0 && newString.equals("Reaction")) {
            Intent intent = new Intent(getApplicationContext(), dj.rabithole.losu.Ryze.class);
            startActivity(intent);
        }
        else if(position==0 && newString.equals("Range")) {
            Intent intent = new Intent(getApplicationContext(), dj.rabithole.losu.Range.class);
            startActivity(intent);
        }
    }


}





