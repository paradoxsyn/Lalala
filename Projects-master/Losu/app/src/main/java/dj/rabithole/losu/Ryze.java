package dj.rabithole.losu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by Rabithole on 11/5/2016.
 */

public class Ryze extends AndroidApplication {

    boolean started = false;
    boolean isqRunning = false;
    boolean iswRunning = false;
    boolean iseRunning = false;
    boolean isrRunning = false;
    boolean toastrun = false;
    boolean timerrun = false;

    Handler handler = new Handler();
    public static final String crypref = "MyPrefsFile";

    ImageButton r1;
    ImageButton r2;
    ImageButton r3;
    ImageButton r4;
    ImageView rune;

    int ryzeQ;
    int ryzeW;
    int ryzeE;
    int ryzeR;

    long crystals;
    TextView score;

    Button cooldown1;
    Button cooldown2;
    Button cooldown3;
    Button cooldown4;
    Button ryzeinfo;

    Toast lastToast;
    LayoutInflater ryzeToast;
    View toastlay;

    AnimationDrawable qanim = new AnimationDrawable();
    AnimationDrawable wanim = new AnimationDrawable();
    AnimationDrawable eanim = new AnimationDrawable();
    AnimationDrawable ranim = new AnimationDrawable();

    CountDownTimer bombq;
    CountDownTimer bombw;
    CountDownTimer bombe;
    CountDownTimer bombr;
    CountDownTimer runetimer;
    CountDownTimer test;


    TextView timerq;
    TextView timerw;
    TextView timere;
    TextView timerr;

    Handler timerHandler = new Handler();
    int timeq = 0;
    int timew = 0;
    int timee = 0;

    Thread t;


    Runnable timerrunQ = new Runnable() {

        @Override
        public void run() {
            //long millis = System.currentTimeMillis() - startTime;
            //int seconds = (int) (millis / 1000);
            //int minutes = seconds / 60;
            //seconds = seconds % 60;

            if(timeq < 3){
                timeq++;
                timerHandler.postDelayed(timerrunQ,500);
            }
            if(timeq == 3){
                timerHandler.removeCallbacks(timerrunQ);
                timerrun = false;
                timeq = 0;
            }
            System.out.println(""+timeq);


        }
    };
    Runnable timerrunW = new Runnable() {

        @Override
        public void run() {
            //long millis = System.currentTimeMillis() - startTime;
            //int seconds = (int) (millis / 1000);
            //int minutes = seconds / 60;
            //seconds = seconds % 60;

            if(timew < 3){
                timew++;
                timerHandler.postDelayed(timerrunW,500);
            }
            if(timew == 3){
                timerHandler.removeCallbacks(timerrunW);
                timerrun = false;
                timew = 0;
            }
            System.out.println(""+timew);


        }
    };
    Runnable timerrunE = new Runnable() {

        @Override
        public void run() {
            //long millis = System.currentTimeMillis() - startTime;
            //int seconds = (int) (millis / 1000);
            //int minutes = seconds / 60;
            //seconds = seconds % 60;

            if(timee < 3){
                //timerrun = true;
                timee++;
                timerHandler.postDelayed(timerrunE,500);
            }
            if(timee == 3){
                timerHandler.removeCallbacks(timerrunE);
                timerrun = false;
                timee = 0;
            }
            System.out.println(""+timee);


        }
    };


    public Runnable runone = new Runnable() {
        @Override
        public void run() {
            r1 = (ImageButton) findViewById(R.id.ryze_1);
            r2 = (ImageButton) findViewById(R.id.ryze_2);
            r3 = (ImageButton) findViewById(R.id.ryze_3);
            r4 = (ImageButton) findViewById(R.id.ryze_4);

            if(started){
                r1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        r1.setClickable(false);
                        handler.postDelayed(runone, ryzeQ);
                        animateButtonQ();
                        if(rune.getTag().toString().equals("rune2")){
                            rune.setTag("rune0");
                            rune.setImageResource(R.drawable.ryzeicon);
                            System.out.println("Bonus damage and shield done");
                        }
                        if(timerrun && timeq == 1 || timeq == 2 ){
                            crystals = crystals+500;
                        }
                        timerrun = true;

                    }
                });r1.setBackgroundResource(R.drawable.overload);
                r1.setClickable(true);
                timerHandler.post(timerrunQ);



            }

        }
    };

    public Runnable runtwo = new Runnable() {
        @Override
        public void run() {
            r1 = (ImageButton) findViewById(R.id.ryze_1);
            r2 = (ImageButton) findViewById(R.id.ryze_2);
            r3 = (ImageButton) findViewById(R.id.ryze_3);
            r4 = (ImageButton) findViewById(R.id.ryze_4);

            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qanim.stop();
                    r1.setBackgroundResource(R.drawable.overload);
                    r1.setClickable(true);
                    handler.removeCallbacks(runone);
                    r2.setClickable(false);
                    handler.postDelayed(runtwo, ryzeW);
                    animateButtonW();
                    setruneTag();
                    runeChange();
                    if (isqRunning == true){
                        timerq.setText("");
                        bombq.cancel();
                    }
                    timerrun = true;
                    if(timerrun = true && timew == 1 || timew == 2 ){
                        crystals = crystals+500;
                    }
                }
            });r2.setBackgroundResource(R.drawable.runeprison);
            r2.setClickable(true);
            timerHandler.post(timerrunW);
        }
    };

    public Runnable runthree = new Runnable() {
        @Override
        public void run() {
            r1 = (ImageButton) findViewById(R.id.ryze_1);
            r2 = (ImageButton) findViewById(R.id.ryze_2);
            r3 = (ImageButton) findViewById(R.id.ryze_3);
            r4 = (ImageButton) findViewById(R.id.ryze_4);

            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qanim.stop();
                    r1.setBackgroundResource(R.drawable.overload);
                    r1.setClickable(true);
                    timerHandler.removeCallbacks(timerrunQ);
                    handler.removeCallbacks(runone);
                    r3.setClickable(false);
                    handler.postDelayed(runthree, ryzeE);
                    animateButtonE();
                    setruneTag();
                    runeChange();
                    if (isqRunning == true){
                        timerq.setText("");
                        bombq.cancel();
                    }
                    timerrun = true;
                    if(timerrun = true && timee == 1 || timee == 2 ){
                        crystals = crystals+500;
                    }
                }
            });r3.setBackgroundResource(R.drawable.spellflux);
            r3.setClickable(true);
            timerHandler.post(timerrunE);
        }
    };

    public Runnable runfour = new Runnable() {
        @Override
        public void run() {
            r1 = (ImageButton) findViewById(R.id.ryze_1);
            r2 = (ImageButton) findViewById(R.id.ryze_2);
            r3 = (ImageButton) findViewById(R.id.ryze_3);
            r4 = (ImageButton) findViewById(R.id.ryze_4);

            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.postDelayed(runfour, ryzeR);
                    animateButtonR();
                }
            });r4.setBackgroundResource(R.drawable.realmwarp);
        }
    };

    public void stop() {
        started = false;

        r1.setBackgroundResource(R.drawable.overload);
        r2.setBackgroundResource(R.drawable.runeprison);
        r3.setBackgroundResource(R.drawable.spellflux);
        r4.setBackgroundResource(R.drawable.realmwarp);

        handler.removeCallbacks(runone);
        handler.removeCallbacks(runtwo);
        handler.removeCallbacks(runthree);
        handler.removeCallbacks(runfour);

        timerHandler.removeCallbacks(timerrunQ);
        timerHandler.removeCallbacks(timerrunW);
        timerHandler.removeCallbacks(timerrunE);

        r1.setClickable(false);
        r2.setClickable(false);
        r3.setClickable(false);
        r4.setClickable(false);

        ryzeQ = 6000;
        ryzeW = 13000;
        ryzeE = 2250;
        ryzeR = 120000;

        r1.clearAnimation();
        r2.clearAnimation();
        r3.clearAnimation();
        r4.clearAnimation();

        t.interrupt();

        if(isqRunning==true){
            bombq.cancel();
            timerq.setText("");
        }
        if(iswRunning==true){
            bombw.cancel();
            timerw.setText("");
        }
        if(iseRunning==true){
            bombe.cancel();
            timere.setText("");
        }
        if(isrRunning==true){
            bombr.cancel();
            timerr.setText("");
        }
        isqRunning=false;
        iswRunning=false;
        iseRunning=false;
        isrRunning=false;

        if(toastrun = toastlay != null){
            toastlay.setVisibility(View.GONE);
        }

        rune.setImageResource(R.drawable.ryzeicon);

        //crystals = 0;


    }

    public void start() {
        started = true;
        t.start();

        handler.post(runone);
        handler.post(runtwo);
        handler.post(runthree);
        handler.post(runfour);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ryze_layout);

        SharedPreferences prefs = getSharedPreferences(crypref, MODE_PRIVATE);
        long idName = prefs.getLong("crystals", 0); //0 is the default value.
        System.out.println("This is cry" + idName);
        crystals = idName;

        ryzeQ = 6000;
        ryzeW = 13000;
        ryzeE = 2250;
        ryzeR = 120000;


        timerq=(TextView) findViewById(R.id.ryzetimer1);
        timerw=(TextView) findViewById(R.id.ryzetimer2);
        timere=(TextView) findViewById(R.id.ryzetimer3);
        timerr=(TextView) findViewById(R.id.ryzetimer4);
        score = (TextView) findViewById(R.id.score);

        Toolbar ryzebar = (Toolbar) findViewById(R.id.ryze_bar);
        //setSupportActionBar(ryzebar);
        //getSupportActionBar().setTitle("Ryze");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toastrun = false;

        setSpinner();

        final Button start = (Button) findViewById(R.id.ryze_start);
        final Button stop = (Button) findViewById(R.id.ryze_stop);

        r1 = (ImageButton) findViewById(R.id.ryze_1);
        r2 = (ImageButton) findViewById(R.id.ryze_2);
        r3 = (ImageButton) findViewById(R.id.ryze_3);
        r4 = (ImageButton) findViewById(R.id.ryze_4);
        cooldown1 = (Button) findViewById(R.id.cooldown10);
        cooldown2 = (Button) findViewById(R.id.cooldown20);
        cooldown3 = (Button) findViewById(R.id.cooldown30);
        cooldown4 = (Button) findViewById(R.id.cooldown40);
        rune = (ImageView) findViewById(R.id.rune);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopButton();
            }
        });

        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                crystals = crystals + 1;
                                score.setText(""+crystals);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };


        SharedPreferences preferences = this.getSharedPreferences("Ryze", 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("startTime", System.currentTimeMillis() );
        editor.commit();
    }


    //@Override
    public boolean onSupportNavigateUp(){
        finish();
        // or call onBackPressed()
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ryze_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.cooldown10){
            //has to be running
            if(started==false){
                startButton();
            }

            ryzeQ = 5400;
            ryzeW = 11700;
            ryzeE = 2000;
            ryzeR = 108000;

        }
        else if(id == R.id.cooldown20){
            //has to be running
            if(started==false){
                startButton();
            }

            ryzeQ = 4800;
            ryzeW = 10400;
            ryzeE = 1800;
            ryzeR = 96000;
        }
        else if(id == R.id.cooldown30){
            //has to be running
            if(started==false){
                startButton();
            }

            ryzeQ = 4200;
            ryzeW = 9100;
            ryzeE = 1575;
            ryzeR = 84000;
        }
        else if(id == R.id.cooldown40){
            //has to be running
            if(started==false){
                startButton();
            }

            ryzeQ = 3600;
            ryzeW = 7800;
            ryzeE = 1325;
            ryzeR = 72000;
        }

        else if(id == R.id.information){
            Intent intent = new Intent(getApplicationContext(), dj.rabithole.losu.RyzeWeb.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //if (ryzeWeb.canGoBack()){
           // ryzeWeb.goBack();
       // }
       // else
            //super.onBackPressed();
            finish();
    }

    public void startButton()
    {

        start();
        RunAnimationButton();
        SharedPreferences preferences = this.getSharedPreferences("Ryze", 0);
        long start = preferences.getLong( "startTime",System.currentTimeMillis());

        long currentTime = System.currentTimeMillis() - start;
        System.out.println(currentTime);

    }


    public void setSpinner()
    {
        Spinner spinnerq = (Spinner) findViewById(R.id.q_spinner);
        Spinner spinnerw = (Spinner) findViewById(R.id.w_spinner);
        Spinner spinnere = (Spinner) findViewById(R.id.e_spinner);
        Spinner spinnerr = (Spinner) findViewById(R.id.r_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.levels_array, R.layout.spinner_title);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
// Apply the adapter to the spinner
        spinnerq.setAdapter(adapter);
        spinnerw.setAdapter(adapter);
        spinnere.setAdapter(adapter);
        spinnerr.setAdapter(adapter);

        spinnerw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(position == 1 && started == true){
                    ryzeW = ryzeW - 1000;
                }
                else if(position == 2 && started == true){
                    ryzeW = ryzeW - 2000;
                }
                else if(position == 3 && started == true){
                    ryzeW = ryzeW - 3000;
                }
                else if(position == 4 && started == true){
                    ryzeW = ryzeW - 4000;
                }
                else if(started == false && position != 0){
                    Toast.makeText(Ryze.this, "Please press start first ", Toast.LENGTH_SHORT).show();
                    spinnerReset();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //stuff maybe
            }

        });
        spinnerq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (started == false && position != 0) {
                    Toast.makeText(Ryze.this, "Please press start first ", Toast.LENGTH_SHORT).show();
                    spinnerReset();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        spinnere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (started == false && position != 0) {
                    Toast.makeText(Ryze.this, "Please press start first ", Toast.LENGTH_SHORT).show();
                    spinnerReset();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //stuff maybe
            }
        });
        spinnerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (started == false && position != 0) {
                    Toast.makeText(Ryze.this, "Please press start first ", Toast.LENGTH_SHORT).show();
                    spinnerReset();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
    public void spinnerReset()
    {
        Spinner spinnerq = (Spinner) findViewById(R.id.q_spinner);
        Spinner spinnerw = (Spinner) findViewById(R.id.w_spinner);
        Spinner spinnere = (Spinner) findViewById(R.id.e_spinner);
        Spinner spinnerr = (Spinner) findViewById(R.id.r_spinner);

        spinnerq.setSelection(0);
        spinnerw.setSelection(0);
        spinnere.setSelection(0);
        spinnerr.setSelection(0);
    }

    public void animateButtonQ(){

        r1.setBackgroundResource(R.drawable.overload_cooldown);

        qanim = (AnimationDrawable) r1.getBackground();
        qanim.setEnterFadeDuration(ryzeQ);

        qanim.start();


        bombq = new CountDownTimer(ryzeQ, 1000)
        {
            final TextView timerq =(TextView) findViewById(R.id.ryzetimer1);
            public void onTick(long millisUntilFinished) {timerq.setText("CD: "+millisUntilFinished / 1000);}
            public void onFinish() {
                timerq.setText("");
            }

        }.start();
        isqRunning = true;
    }
    public void animateButtonW(){

        r2.setBackgroundResource(R.drawable.runeprison_cooldown);

        wanim = (AnimationDrawable) r2.getBackground();
        wanim.setEnterFadeDuration(ryzeW);
        bombw = new CountDownTimer(ryzeW, 1000)
        {
            final TextView timerw =(TextView) findViewById(R.id.ryzetimer2);
            public void onTick(long millisUntilFinished) {timerw.setText("CD: "+millisUntilFinished / 1000);}
            public void onFinish() {
                timerw.setText("");
            }

        }.start();
        wanim.start();
        iswRunning = true;
    }
    public void animateButtonE(){

        r3.setBackgroundResource(R.drawable.spellflux_cooldown);

        eanim = (AnimationDrawable) r3.getBackground();
        eanim.setEnterFadeDuration(ryzeE);
        bombe = new CountDownTimer(ryzeE, 1000)
        {
            final TextView timere =(TextView) findViewById(R.id.ryzetimer3);
            public void onTick(long millisUntilFinished) {timere.setText("CD: "+millisUntilFinished / 1000);}
            public void onFinish() {
                timere.setText("");
            }

        }.start();
        eanim.start();
        iseRunning = true;
    }
    public void animateButtonR(){

        r4.setBackgroundResource(R.drawable.realmwarp_cooldown);

        ranim = (AnimationDrawable) r4.getBackground();
        ranim.setEnterFadeDuration(ryzeR);
        bombr = new CountDownTimer(ryzeR, 1000)
        {
            final TextView timerr =(TextView) findViewById(R.id.ryzetimer4);
            public void onTick(long millisUntilFinished) {timerr.setText("CD: "+millisUntilFinished / 1000);}
            public void onFinish() {
                timerr.setText("");
            }

        }.start();
        ranim.start();
        isrRunning = true;
    }
    public void runeChange()
    {
        if(rune.getTag() != null && rune.getTag().toString().equals("rune0")){
            rune.setImageResource(R.drawable.ryzeonerune);
             runetimer = new CountDownTimer(4000,1000)
             {
                 public void onTick(long millisUntilFinished){};
                public void onFinish() {
                    rune.setImageResource(R.drawable.ryzeicon);
                    rune.setTag("rune0");
                }
            }.start();


        }
        else if(rune.getTag() != null && rune.getTag().toString().equals("rune1")) {
            rune.setImageResource(R.drawable.ryzeonerune);
            runetimer = new CountDownTimer(4000, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                ;

                public void onFinish() {
                    rune.setImageResource(R.drawable.ryzeicon);
                    rune.setTag("rune0");
                }
            }.start();
        }
        else if (rune.getTag() != null && rune.getTag().toString().equals("rune2")){
            rune.setImageResource(R.drawable.ryzerunetwo);
            if(runetimer != null)
            {
                runetimer.cancel();
            }

            runetimer = new CountDownTimer(4000,1000)
            {
                public void onTick(long millisUntilFinished){};
                public void onFinish() {
                    rune.setImageResource(R.drawable.ryzeicon);
                    rune.setTag("rune0");
                }
            }.start();
        }
    }
    public void setruneTag()
    {
        if(rune.getTag().toString().equals("rune0")){
            rune.setTag("rune1");
        }
        else if(rune.getTag().toString().equals("rune1")){
            rune.setTag("rune2");
        }
        else if(rune.getTag().toString().equals("rune2")){
            Toast.makeText(Ryze.this, "Max runes! ", Toast.LENGTH_SHORT).show();
        }
    }
    public void stopButton()
    {
        stop();
        spinnerReset();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences(crypref, MODE_PRIVATE).edit();

        editor.putLong("crystals", crystals);
        editor.commit();
    }

    private void RunAnimationButton(){
        Animation a = AnimationUtils.loadAnimation(this, R.anim.ryzebuttonanim);
        a.reset();
        ImageButton bt1 = (ImageButton) findViewById(R.id.ryze_1);
        ImageButton bt2 = (ImageButton) findViewById(R.id.ryze_2);
        ImageButton bt3 = (ImageButton) findViewById(R.id.ryze_3);
        ImageButton bt4 = (ImageButton) findViewById(R.id.ryze_4);
        bt1.clearAnimation();
        bt1.startAnimation(a);
        bt2.startAnimation(a);
        bt3.startAnimation(a);
        bt4.startAnimation(a);

    }
}



