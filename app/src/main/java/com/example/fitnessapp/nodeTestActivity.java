package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class nodeTestActivity extends AppCompatActivity {


    private Button RequestButton;
    private TextView DisplayText;
    private  TextView editText;
    private EditText url;
    //public static final String value= "Hi Sakib! I'm communicating through wifi !!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_test);

        RequestButton = (Button) findViewById(R.id.btnRequest);
        DisplayText= findViewById(R.id.tvDisplay);
        url= findViewById(R.id.urlID);
        Intent intent= getIntent();
        String value= intent.getStringExtra("value");


        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        RequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<String,String>();
                params.put("data", value); // the entered data as the JSON body.

                JsonObjectRequest jsObjRequest = new
                        JsonObjectRequest(Request.Method.POST,
                        url.getText().toString(),
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    DisplayText.setText(response.getString("message"));
                                    DisplayText.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String inURL= "http://192.168.1.113:8080/ipfs/"+DisplayText.getText().toString();
                                            Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse(inURL) );

                                            startActivity( browse );
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DisplayText.setText("That didn't work!");
                    }
                });
                queue.add(jsObjRequest);
            }
        });

    }
}
