package com.example.myapplication.requests;

import android.content.Context;
import android.view.View;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MasterCarService {

    public static String testUrl = "https://reqres.in/api/users?page=2";

    public void sendRequest(Context context, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
        requestQueue.start();
    }

    public void sendAnotherRequest(Context context, String url) {
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);


    }

    public StringRequest sendTestRequest(Context context,final View view) {
        String url = "https://reqres.in/api/users/2";

        MasterCarRequestQueue masterCarRequestQueue = MasterCarRequestQueue.getInstance(context);

        RequestQueue requestQueue = masterCarRequestQueue.getRequestQueue();

        requestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        Snackbar.make(view, jo.toString(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        // plus do
                    }
                } catch (JSONException jse) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        return stringRequest;


    }

    /*public void JSONParseOn () {
        super.onPostExecute(strJson);
        final ListView lview = findViewById(R.id.listRes);
        String[] from = {"name_item"};
        int[] to = {R.id.name_item};
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hashMap;

        try {
            JSONObject json = new JSONObject(strJson);
            JSONArray jsonArray = json.getJSONArray("platform");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject friend = jsonArray.getJSONObject(i);

                String nameOS = friend.getString("name");
                Log.d("name_item",""+nameOS);
                hashMap = new HashMap<String, String>();
                hashMap.put("name_item", ""+nameOS);
                arrayList.add(hashMap);
            }
            final SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, arrayList, R.layout.item, from, to);
            lview.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
