package com.example.a4thyearproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;

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
        passwordInfo = String.valueOf(emailInput.getText());
        User newUser = new User(emailInfo, passwordInfo, 1);
        db.addUser(newUser);


    }
}
