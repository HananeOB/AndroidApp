package com.example.context_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {




    public Context context;
    public static String room;
    public Button button = null;
    public static String  Level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonCheck);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                room = ((EditText) findViewById(R.id.editText1)).getText().toString();
                retrieveRoomContextState(room);
            }

        });

    }
    public void onUpdate(RoomContextState state){

        ImageView image = (ImageView) findViewById(R.id.imageView1);
        ((TextView) findViewById(R.id.textViewLightValue)).setText(Integer
                .toString(state.getLight()));

        if (state.getStatus().equals("ON"))
            image.setImageResource(R.drawable.ic_bulb_on);
        else
            image.setImageResource(R.drawable.ic_bulb_off);







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void switchLevel(View view) {
        Level = ((EditText) findViewById(R.id.editText5)).getText().toString();
        room = ((EditText) findViewById(R.id.editText1)).getText().toString();
        SwitchRoomContextStateLevel(room,Level);
    }

    public void switchLight(View view) {
        room = ((EditText) findViewById(R.id.editText1)).getText().toString();
        SwitchRoomContextState(room);
    }


    private void retrieveRoomContextState(String room) {

        String CONTEXT_SERVER_URL = "https://hananeapp.cleverapps.io/api/lights/";
        String url = CONTEXT_SERVER_URL + "/" + room+ "/";
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("tyuu",url);
        //get room sensed context
        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id").toString();
                            int lightLevel = Integer.parseInt(response.getString("level").toString());
                            String lightStatus = response.get("status").toString();

                            RoomContextState State = new RoomContextState(id, lightStatus, lightLevel );
                            Log.d("hhhh", lightStatus);
                            onUpdate(State);

                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                        Log.d("pok", "jjj");
                    }
                });


        queue.add(contextRequest);





    }

    private void SwitchRoomContextState(String room) {

        String CONTEXT_SERVER_URL = "https://hananeapp.cleverapps.io/api/Lights/";
        String url = CONTEXT_SERVER_URL + "/" + room+ "/switch";
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("tyuu",url);
        //get room sensed context
        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id").toString();
                            int lightLevel = Integer.parseInt(response.getString("level").toString());
                            String lightStatus = response.get("status").toString();

                            RoomContextState State = new RoomContextState(id, lightStatus, lightLevel );
                            Log.d("hhhh", lightStatus);
                            onUpdate(State);

                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                        Log.d("pok", "jjj");
                    }
                });


        queue.add(contextRequest);





    }


    private void SwitchRoomContextStateLevel(String room, String Level) {

        String CONTEXT_SERVER_URL = "https://hananeapp.cleverapps.io/api/Lights/";
        String url = CONTEXT_SERVER_URL + "/" + room+ "/" +Level;
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("tyuu",url);
        //get room sensed context
        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id").toString();
                            int lightLevel = Integer.parseInt(response.get("level").toString());
                            String lightStatus = response.get("status").toString();

                            RoomContextState State = new RoomContextState(id, lightStatus, lightLevel );
                            Log.d("hhhh", lightStatus);
                            onUpdate(State);

                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                        Log.d("pok", "jjj");
                    }
                });


        queue.add(contextRequest);





    }





}
