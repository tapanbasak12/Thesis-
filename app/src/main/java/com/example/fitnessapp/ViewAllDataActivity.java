package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitnessapp.adapters.MyAdapter;
import com.example.fitnessapp.logger.Log;
import com.example.fitnessapp.objects.People;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        TextView textView = findViewById(R.id.dislaytextdata);


        String url= "http://192.168.1.113:3000/api/txn/all";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {

                            ArrayList<People> peopleList = new ArrayList<>();
                            //textView.setText(response.getString("message"));
                            JSONArray jsonarray=new JSONArray(response.getString("message"));
                            for (int i=0; i<jsonarray.length();i++){
                                JSONObject jsonObject=jsonarray.getJSONObject(i);

                                String key=jsonObject.getString("Key");
                                JSONObject record=jsonObject.getJSONObject("Record");
                                String cid=record.getString("data");
                                String uid=record.getString("uid");
                               // textView.append(key+cid+uid);
                                peopleList.add(new People(key,cid,uid));

                            }
                            MyAdapter adapter = new MyAdapter(getApplicationContext(), peopleList);
                            ListView listView = findViewById(R.id.list_view);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "hocchena");
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);



    }
}
