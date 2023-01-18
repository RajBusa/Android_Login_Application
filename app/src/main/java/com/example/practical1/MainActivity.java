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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                String regex = "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(str2);
                if(str1.isEmpty() || str2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter the Data", Toast.LENGTH_SHORT).show();
                }
                if(str1.length() < 5 || str1.length() > 8){
                    Toast.makeText(getApplicationContext(), "Please Enter username between 5 to 8 char", Toast.LENGTH_SHORT).show();
                }
                if(!m.matches()){
                    Toast.makeText(getApplicationContext(), "Please Enter correct password", Toast.LENGTH_SHORT).show();
                }
                if(str1.equals("20IT011") && str2.equals("RAJraj@12345")){
                    Intent i = new Intent(MainActivity.this, activity_home.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}