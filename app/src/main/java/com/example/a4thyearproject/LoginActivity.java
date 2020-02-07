package com.example.a4thyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Button registerBtn;
    UserRepo db;
    EditText emailBox;
    EditText passwordBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new UserRepo(this);
        registerBtn = findViewById(R.id.registerBtn);
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);

    }

    public void registerBtnClick(View view){
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void resetBtnClick(View view){
        Intent resetIntent = new Intent(this, passwordReset.class);
        startActivity(resetIntent);
    }

    public void userBtnClick(View view){

        db.getAllUsers();
    }

    public void loginBtnClick(View view){
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();
        login(email, password);
    }

    public void login(String email, String password){
        if(db.loginCheck(email, password)){
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        }else {
            Toast.makeText(this, "No such user exists", Toast.LENGTH_LONG).show();
        }
    }
}
