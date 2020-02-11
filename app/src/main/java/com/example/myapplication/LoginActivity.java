package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.requests.MasterCarService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // i do not know who put this here
            }
        });

        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean loginTrue = false;
                EditText login = findViewById(R.id.loginField);
                EditText pwd    = findViewById(R.id.PasswordField);

                // TODO execute login send request

                MasterCarService mcService = new MasterCarService();
                mcService.sendRequest(view.getContext(), MasterCarService.testUrl);
                if (loginTrue) {
                    Intent intent = new Intent(view.getContext(), Main2Activity.class);
                    startActivity(intent);
                } else {
                    MasterCarService masterCarService = new MasterCarService();
                    StringRequest res = masterCarService.sendTestRequest(view.getContext(), view);

                    Snackbar.make(view, res.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });


    }

}
