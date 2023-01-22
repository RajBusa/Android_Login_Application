package com.example.practical1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    EditText emailId, pass;
    private DBHandler dbHandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbtn =(Button) findViewById(R.id.loginbtn);
        emailId = findViewById(R.id.emailId);
        pass = findViewById(R.id.pass);
        loginbtn.setOnClickListener(v -> {
            String str1 = emailId.getText().toString();
            String str2 = pass.getText().toString();
            String regex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str2);
            if(str1.isEmpty() || str2.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please Enter the Data", Toast.LENGTH_SHORT).show();
            } else {
                if(!m.matches()){
                    Toast.makeText(getApplicationContext(), "Please Enter correct password", Toast.LENGTH_SHORT).show();
                } else {

                    boolean result = dbHandler.checkusernamepassword(str1, str2);

                    if(result){
                        Toast.makeText(getApplicationContext(), "Correct password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Correct not password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}