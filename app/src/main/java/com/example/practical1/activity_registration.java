package com.example.practical1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class activity_registration extends AppCompatActivity {
    Button regbtn;
    EditText name, emailId, pass, cpass, gotoLoginPagebtn;
    private DBHandler dbHandler;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regbtn = (Button) findViewById(R.id.regbtn);
        name = findViewById(R.id.name);
        emailId = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
//        gotoLoginPagebtn = findViewById(R.id.gotoLogin);
        dbHandler = new DBHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        regbtn.setOnClickListener(view -> {
            String Name = name.getText().toString();
            String Email = emailId.getText().toString();
            String Password = pass.getText().toString();
            String CPassword = cpass.getText().toString();
            boolean flag = false;

            String regex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(Password);
            if(Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || CPassword.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please Enter the Data", Toast.LENGTH_SHORT).show();
            } else {
                if(!Password.equals(CPassword)){
                    Toast.makeText(getApplicationContext(), "Please enter the same password.", Toast.LENGTH_SHORT).show();
                } else {
                    if(Name.length() < 5 || Name.length() > 8){
                        Toast.makeText(getApplicationContext(), "Please Enter username between 5 to 8 char", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!m.matches()){
                            Toast.makeText(getApplicationContext(), "Please Enter correct password", Toast.LENGTH_SHORT).show();
                        } else {
                            flag = true;
                        }
                    }
                }
            }
            if (flag) {
                User user = new User(name.getText().toString(), emailId.getText().toString(), pass.getText().toString());
                dbHandler.addNewRecord(user);
                Intent i = new Intent(activity_registration.this, MainActivity.class);
                startActivity(i);
                finish();
                name.setText("");
                emailId.setText("");
                pass.setText("");
                cpass.setText("");
            }
        });
    }

//    public void clickOngotoLogin() {
//        Intent i = new Intent(activity_registration.this, MainActivity.class);
//    };
}