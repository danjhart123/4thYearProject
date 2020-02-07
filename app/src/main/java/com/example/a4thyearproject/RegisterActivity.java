package com.example.a4thyearproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private EditText securityQuestionInput;
    private String emailInfo;
    private String passwordInfo;
    private String confirmPasswordInfo;
    private String securityQuestionInfo;
    private RadioGroup registerRadioGroup;
    private RadioButton adminRadio;
    private RadioButton memberRadio;
    private UserRepo db;
    private int userAccountChoice;



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
    }

    public void registerUser(View view){
        emailInfo = String.valueOf(emailInput.getText());
        passwordInfo = String.valueOf(passwordInput.getText());
        confirmPasswordInfo = String.valueOf(confirmPasswordInput.getText());
        securityQuestionInfo = String.valueOf(securityQuestionInput.getText());

        if((validateEmail(emailInfo)) && (!checkDuplicateAccount(emailInfo)) && (passwordInfo.equals(confirmPasswordInfo))) {
            User newUser = new User(emailInfo, passwordInfo, securityQuestionInfo, userAccountChoice);
            db.addUser(newUser);
            Toast.makeText(this, newUser.toString(), Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
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
