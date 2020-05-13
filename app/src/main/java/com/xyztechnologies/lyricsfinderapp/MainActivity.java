package com.xyztechnologies.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText edtArtistName, edtSongName;
    TextView txtLyrics;
    Button btnGetLyrics;
    ScrollView scrollView;

  //  https://api.lyrics.ovh/v1/Nf/When%20i%20grow%20up#

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtArtistName = findViewById(R.id.edtArtistName);
        edtSongName  = findViewById(R.id.edtSongName);
        txtLyrics = findViewById(R.id.txtLyrics);
        btnGetLyrics = findViewById(R.id.btnGetLyrics);
        scrollView = findViewById(R.id.scrollView2);


        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtSongName.getText().toString();
                url.replace("","20%");
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            txtLyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(MainActivity.this,"Invalid Artist_Name or Song_Name: "+error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });


                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}
