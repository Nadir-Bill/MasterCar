/**package com.example.myapplication;

import android.os.AsyncTask;

import org.json.JSONObject;
import android.widget.ListView;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.nio.channels.AsynchronousChannelGroup;

public class AsyncJasonData extends AsyncTask<String, Void, JSONObject> {

    private MainActivity controller;

    public AsyncJasonData(MainActivity controller){

        this.controller = controller;
    }


    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json; // returns the result
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();

        // Extracting the JSON object from the String
        String jsonextracted = sb.substring("jsonFlickrFeed(".length(), sb.length() - 1);
        //Log.i("CIO", jsonextracted);
        return jsonextracted;
    }

    @Override
    protected void onPostExecute(JSONObject s) {

        ListView list = (ListView)myActivity.findViewById(R.id.listview);
        BitmapAdapter tableau = new BitmapAdapter(list.getContext());
        list.setAdapter(tableau);

        // For testing purpose
        //Bitmap largeIcon = BitmapFactory.decodeResource(myActivity.getResources(), R.drawable.test);
        //tableau.add(largeIcon);

        try {
            JSONArray items = s.getJSONArray("items");
            for (int i = 0; i<items.length(); i++)
            {
                JSONObject flickr_entry = items.getJSONObject(i);
                String urlmedia = flickr_entry.getJSONObject("media").getString("m");
                Log.i("CIO", "URL media: " + urlmedia);

                // Downloading image
                AsyncBitmapDownloader abd = new AsyncBitmapDownloader(tableau);
                abd.execute(urlmedia);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

    /**
     *
     */


/**
public void sendPost() {
        Thread thread = new Thread(new Runnable() {
@Override
public void run() {
        try {
        URL url = new URL(urlAdress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setRequestProperty("Accept","application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("timestamp", 1488873360);
        jsonParam.put("uname", message.getUser());
        jsonParam.put("message", message.getMessage());
        jsonParam.put("latitude", 0D);
        jsonParam.put("longitude", 0D);

        Log.i("JSON", jsonParam.toString());
        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
        //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
        os.writeBytes(jsonParam.toString());

        os.flush();
        os.close();

        Log.i("STATUS", String.valueOf(conn.getResponseCode()));
        Log.i("MSG" , conn.getResponseMessage());

        conn.disconnect();
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        });

        thread.start();
        }
public void receive(){
        try (BufferedReader br = new BufferedReader(
        new InputStreamReader(con.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
        }
        System.out.println(response.toString());
        }
        }
        }
        */


