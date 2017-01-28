package dj.rabithole.losu;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dj.rabithole.losu.R;

/**
 * Created by Rabithole on 12/8/2016.
 */

public class PsychResponse extends AppCompatActivity{

    String urlJsonArry = "http://74.215.9.207:3000/todos";
    private static String TAG = Psych.class.getSimpleName();
    private ProgressDialog pDialog;
    private String jsonResponse;
    private TextView txtResponse;

    String name;
    String email;

    ArrayList<String> listItems=new ArrayList<String>();
    responseAdapter adapter;
    ListView listV;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psych_fields);

        makeJsonArrayRequest();
        listV = (ListView) findViewById(R.id.list);

        //txtResponse = (TextView) findViewById(R.id.txtResponse);
        adapter = new responseAdapter(this, android.R.layout.simple_list_item_1, listItems);
        listV.setAdapter(adapter);

    }

    private void makeJsonArrayRequest() {
        //showpDialog();

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

                                name = person.getString("name");
                                email = person.getString("note");
                                //JSONObject phone = person.getJSONObject("phone");
                                //String home = phone.getString("home");
                                //String mobile = phone.getString("mobile");

                                jsonResponse += "Meme: " + name + "\n\n";
                                jsonResponse += "Response: " + email + "\n\n";
                                //jsonResponse += "Home: " + home + "\n\n";
                                //jsonResponse += "Mobile: " + mobile + "\n\n\n";
                                adapter.add("Meme: " + name);
                                adapter.add("Response: " + email);




                            }

                            //txtResponse.setText(jsonResponse);
                            //txtResponse.setTextColor(Color.CYAN);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        //hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                //hidepDialog();
            }
        });

        // Adding request to request queue
        PsychController.getInstance().addToRequestQueue(req);
    }
    //private void showpDialog() {
      //  if (!pDialog.isShowing())
        //    pDialog.show();
    //}

    //private void hidepDialog() {
      //  if (pDialog.isShowing())
        //    pDialog.dismiss();
    //}
    public class responseAdapter extends ArrayAdapter<String> {

        public responseAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);}

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            if (position % 2 == 1) {
                view.setBackgroundColor(Color.WHITE);
            } else {
                view.setBackgroundColor(Color.BLACK);
                view.setTextColor(Color.WHITE);
            }

            return view;
        }
    }

}



