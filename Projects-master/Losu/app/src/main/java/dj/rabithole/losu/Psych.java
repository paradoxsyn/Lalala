package dj.rabithole.losu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rabithole on 11/29/2016.
 */

public class Psych extends AppCompatActivity {
    // json object response url
    //private String urlJsonObj = "http://74.215.9.207:3000/todos";

    // json array response url
    private String urlJsonArry = "http://74.215.9.207:3000/todos";

    private static String TAG = Psych.class.getSimpleName();
    private Button writeReponse, viewResponse;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;
    private TextView unlockone;
    private TextView unlocktwo;

    // temporary string to show the parsed response
    private String jsonResponse;

    private JSONObject jsonBody = new JSONObject();
    Gson gson = new Gson();

    String input;
    String inputtwo;

    public String id = "__id";
    public String name = "name";
    public String v = "__v";
    public String upvote = "upvote";
    public String note = "note";
    long cost;

    long crystals;
    public static final String crypref = "MyPrefsFile";


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_hurt);

        writeReponse = (Button) findViewById(R.id.writeResponse);
        viewResponse = (Button) findViewById(R.id.viewResponse);
        txtResponse = (TextView) findViewById(R.id.crystals);
        unlockone = (TextView) findViewById(R.id.unlockone);
        unlocktwo = (TextView) findViewById((R.id.unlocktwo));

        SharedPreferences prefs = getSharedPreferences(crypref, MODE_PRIVATE);
        long idName = prefs.getLong("crystals", 0); //0 is the default value.
        System.out.println("This is cry" + idName);
        crystals = idName;
        cost = crystals / 3;

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        txtResponse.setText("Crystals: "+crystals);
        txtResponse.setTextColor(Color.GREEN);

        unlockone.setText("Gimme sum dat shiny stuff...like dis much: " + cost);
        unlockone.setTextColor(Color.GREEN);
        unlocktwo.setText("r u prepared? :)");
        unlocktwo.setTextColor(Color.GREEN);



        writeReponse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                //makeJsonObjectRequest();
                if(crystals < cost){
                    Toast.makeText(Psych.this, "Check yo digits", Toast.LENGTH_LONG).show();
                }else{
                    crystals = crystals - cost;
                    txtResponse.setText("Crystals: " +crystals);
                    myTextDialog();
                }



            }
        });

        viewResponse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json array request
                //makeJsonArrayRequest();
                Intent intent = new Intent(getApplicationContext(),dj.rabithole.losu.PsychResponse.class);
                startActivity(intent);
            }
        });
    }
    /**
     * Method to make json object request where json response starts wtih {
     * */
    /*private void makeJsonObjectRequest() {
        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Home: " + home + "\n\n";
                    jsonResponse += "Mobile: " + mobile + "\n\n";

                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        PsychController.getInstance().addToRequestQueue(jsonObjReq);
    }
*/


    private void postJsonArrayRequest() throws JSONException {
        final String names = input;
        final String notes = inputtwo;
        final String cool = "[{" + input + "}]";

        StringRequest sr = new StringRequest(Request.Method.POST, urlJsonArry,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Psych.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Psych.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(name, names);
                params.put(note, notes);
                return params;
            }

        };
        PsychController.getInstance().addToRequestQueue(sr);
    }





    private void myTextDialog(){
        LayoutInflater inflater = LayoutInflater.from(Psych.this);
        final View yourCustomView = inflater.inflate(R.layout.psych_dialog, null);
        final TextView etName = (EditText) yourCustomView.findViewById(R.id.response);
        final TextView etNameTwo = (EditText) yourCustomView.findViewById(R.id.responsetwo);

       new AlertDialog.Builder(Psych.this)
                .setTitle("Let us begin")
                .setView(yourCustomView)

                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // send
                        input = etName.getText().toString().trim();
                        inputtwo = etNameTwo.getText().toString().trim();
                        try {
                            postJsonArrayRequest();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }})
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // send
                     dialog.dismiss();
                       crystals = crystals + cost;
                       txtResponse.setText("Crystals: " +crystals);
                   }})


                .show();
    }

    /**
     * Method to make json array request where response starts with [
     * */
    private void makeJsonArrayRequest() {
        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("name");
                                String email = person.getString("note");
                                //JSONObject phone = person.getJSONObject("phone");
                                //String home = phone.getString("home");
                                //String mobile = phone.getString("mobile");

                                jsonResponse += "Meme: " + name + "\n\n";
                                jsonResponse += "Response: " + email + "\n\n";
                                //jsonResponse += "Home: " + home + "\n\n";
                                //jsonResponse += "Mobile: " + mobile + "\n\n\n";

                            }

                            txtResponse.setText(jsonResponse);
                            txtResponse.setTextColor(Color.CYAN);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        PsychController.getInstance().addToRequestQueue(req);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
