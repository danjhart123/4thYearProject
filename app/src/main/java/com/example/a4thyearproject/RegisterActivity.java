package com.example.a4thyearproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth userAuth;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private EditText securityQuestionInput;
    private String email;
    private String password;
    private String confirmPassword;
    private String securityQuestion;
    private RadioGroup registerRadioGroup;
    private RadioButton adminRadio;
    private RadioButton memberRadio;
    private UserRepo db;
    private int userAccountChoice;


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
        setContentView(R.layout.register_activity);

        registerRadioGroup = findViewById(R.id.registerRadioGroup);
        adminRadio = findViewById(R.id.adminChoice);
        memberRadio = findViewById(R.id.memberChoice);
        db = new UserRepo(this);
        emailInput = findViewById(R.id.registerEmail);
        passwordInput = findViewById(R.id.registerPassword);
        securityQuestionInput = findViewById(R.id.securityQuestionInput);
        confirmPasswordInput = findViewById(R.id.reenterPassword);
        userAuth = FirebaseAuth.getInstance();

    }

    public void registerUser(View view){
        email = String.valueOf(emailInput.getText());
        password = String.valueOf(passwordInput.getText());
        confirmPassword = String.valueOf(confirmPasswordInput.getText());
        securityQuestion = String.valueOf(securityQuestionInput.getText());

        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        if(password.matches(confirmPassword)) {
            userAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Success",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        } else{
            Toast.makeText(RegisterActivity.this, "Passwords do not match",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == adminRadio) {
            userAccountChoice = 1;
        }
        if (view == memberRadio) {
            userAccountChoice = 0;
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
