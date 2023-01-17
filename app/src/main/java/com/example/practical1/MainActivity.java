package com.example.practical1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    EditText emailId, pass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn =(Button) findViewById(R.id.loginbtn);
        emailId = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = String.valueOf(emailId.getText());
                String str2 = String.valueOf(pass.getText());
                if(str1.equals("20IT011") && str2.equals("123456789")){
                    Intent i = new Intent(MainActivity.this, activity_home.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}