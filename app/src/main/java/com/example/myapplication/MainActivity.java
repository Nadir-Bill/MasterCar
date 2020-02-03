package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;//IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


import java.util.Calendar;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText editTextReserche;
    private Button recherche;
    private TextView indication;
    public Spinner choixDepart;
    private Spinner choixArrivee;
    public TextView dateDepart;
    public TextView heureDepart;
    public Button departBtn;
    public Button heureBtn;
    HttpURLConnection conn;
    String urlAdress;
    public int mYear, mMonth, mDay, mHour, mMinute;
    public String villeDeDepartSelected = "";
    public String villeDarriveeselected = "";
    public String dateDeDepartSelected = "";
    public String heureDeDepartSelected = "";
    public String nbrDePlaceSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choixDepart = findViewById(R.id.choixDepart);
        choixArrivee = findViewById(R.id.choixArrive);
        recherche = findViewById(R.id.recherche);
        dateDepart = findViewById(R.id.dateDepart);
        heureDepart = findViewById(R.id.dateArrive);
        departBtn = findViewById(R.id.departBtn);
        heureBtn = findViewById(R.id.heureBtn);


/**
 * spinner Depart
 */
        ArrayList<String> departList = new ArrayList<>();
        departList.add("Paris");
        departList.add("Marseille");
        departList.add("Lille");
        departList.add("Metz");
        departList.add("Lyon");
        departList.add("Reims");
        departList.add("Annaba");
        departList.add("Constantine");
        ArrayAdapter<String> arrayAdapterDep = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departList);
        arrayAdapterDep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixDepart.setAdapter(arrayAdapterDep);
        choixDepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /**
         * spinner Arrivee
         */
        ArrayList<String> arriveeList = new ArrayList<>();
        arriveeList.add("...");
        arriveeList.add("Paris");
        arriveeList.add("Marseille");
        arriveeList.add("Lille");
        arriveeList.add("Metz");
        arriveeList.add("Lyon");
        arriveeList.add("Reims");
        ArrayAdapter<String> arrayAdapterArriv = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arriveeList);
        arrayAdapterArriv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixArrivee.setAdapter(arrayAdapterArriv);
        choixArrivee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        /**
         *
         */


        /**
         * mettre la valeur du spinner dans une variable
         */
        choixDepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                villeDeDepartSelected = selectedItemText;
                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        choixArrivee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                villeDarriveeselected = selectedItemText;
                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultPage();
            }
        });

        departBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b();
            }
        });
        heureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();
            }
        });

    }
        public void openResultPage(){
            Intent intent = new Intent(this, Main2Activity.class);
           // dateDeDepartSelected = (String) dateDepart.getText();
            //heureDeDepartSelected = (String) heureDepart.getText();
            //new parseTask().execute();
            //sendPost();

            startActivity(intent);
            //new parseTask().execute();
        }
        public void a(){
            final Calendar calandar =  Calendar.getInstance();
            mHour = calandar.get(Calendar.HOUR_OF_DAY);
            mMinute = calandar.get(Calendar.MINUTE);
            TimePickerDialog datePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    heureDepart.setText(hourOfDay+":"+minute);
                }
            }, mHour, mMinute, false);
            datePickerDialog.show();
        }

        public void b(){
            final Calendar calandar = Calendar.getInstance();
            mYear = calandar.get(Calendar.YEAR);
            mMonth = calandar.get(Calendar.MONTH);
            mDay = calandar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateDepart.setText(dayOfMonth + "-" + month + 1 + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    public void sendPost() {
       // Thread thread = new Thread(new Runnable() {
          //  @Override
          //  public void run() {
                try {
                    URL url = new URL(urlAdress);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("villeDeDepartSelected",villeDeDepartSelected);
                    jsonParam.put("villeDarriveeselected", villeDarriveeselected);
                    jsonParam.put("dateDeDepartSelected", dateDeDepartSelected);
                    jsonParam.put("heureDeDepartSelected",heureDeDepartSelected);
                    jsonParam.put("nbrDePlaceSelected",nbrDePlaceSelected);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    //conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        //});

       // thread.start();
    }

/**
    private class parseTask extends AsyncTask<Void, Void, String>{
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {


           // String url_json = "";
            try {
                URL url = new URL("http://localhost:3000/posts/1");
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                //System.out.println("Connected.......");
                //sendPost();
                InputStream inputStream = conn.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                resultJson = buffer.toString();
                Log.d("FOR_LOG", resultJson);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


           // return resultJson;

        }

        @Override
        protected void onPostExecute(String strJson) {
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
   // }


//}




