package com.example.a4thyearproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    FirebaseAuth userAuth;
    Button registerBtn;
    UserRepo db;
    EditText emailInput;
    EditText passwordInput;



    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = userAuth.getCurrentUser();
        if(currentUser != null) {
            System.out.println(currentUser.toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new UserRepo(this);
        registerBtn = findViewById(R.id.registerBtn);
        emailInput = findViewById(R.id.emailBox);
        passwordInput = findViewById(R.id.passwordBox);
        userAuth = FirebaseAuth.getInstance();
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
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        login(email, password);
    }

    public void login(String email, String password){
        userAuth.signOut();
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        userAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Success",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
