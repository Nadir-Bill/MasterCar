package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mreRecyclerView;
    private RequestQueue mRequestQueue;
    private ExampleAdapter mExampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mreRecyclerView = findViewById(R.id.recycler_view);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        String url = "https://api.myjson.com/bins/unkz6";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //JSONArray jsonArray = response.getJSONArray("emp");
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject hit = response.getJSONObject(i);

                        mExampleList.add((new ExampleItem(hit.getString("url"), hit.getString("artists"), hit.getString("song"))));
                    }
                    mExampleAdapter = new ExampleAdapter(Main2Activity.this, mExampleList);
                    mreRecyclerView.setAdapter(mExampleAdapter);

                }catch(JSONException e){

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}
