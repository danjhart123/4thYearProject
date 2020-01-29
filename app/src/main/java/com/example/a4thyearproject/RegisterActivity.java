package com.example.a4thyearproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailInput;
    private EditText passwordInput;
    private String emailInfo;
    private String passwordInfo;
    private UserRepo db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        db = new UserRepo(this);
        emailInput = findViewById(R.id.registerEmail);
        passwordInput = findViewById(R.id.registerPassword);
    }

    public void registerUser(View view){
        emailInfo = String.valueOf(emailInput.getText());
        passwordInfo = String.valueOf(passwordInput.getText());
        if((validateEmail(emailInfo)) && (!checkDuplicateAccount(emailInfo))) {
            User newUser = new User(emailInfo, passwordInfo, 1);
            db.addUser(newUser);
            Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }


    }

    public boolean checkDuplicateAccount(String email){
        boolean duplicate = false;
        if(db.registerCheck(email)){
            duplicate = true;
        } else {
            duplicate = false;
        }
        return duplicate;
    }
    public boolean validateEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
