package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

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
                Snackbar.make(view, "wow clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                EditText login = findViewById(R.id.loginField);
                EditText pwd    = findViewById(R.id.PasswordField);

                // TODO execute login

                MasterCarService mcService = new MasterCarService();
                mcService.sendRequest(view.getContext(), MasterCarService.testUrl);
            }
        });


    }

}
