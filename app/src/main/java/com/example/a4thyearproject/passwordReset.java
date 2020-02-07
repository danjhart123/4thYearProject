package com.example.a4thyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class passwordReset extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private EditText securityQuestionInput;
    private String emailInfo;
    private String passwordInfo;
    private String confirmPasswordInfo;
    private String securityQuestionInfo;
    private UserRepo db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        db = new UserRepo(this);
        emailInput = findViewById(R.id.resetEmailInput);
        passwordInput = findViewById(R.id.newPasswordInput);
        confirmPasswordInput = findViewById(R.id.confirmNewPasswordInput);
        securityQuestionInput = findViewById(R.id.resetSecurityQuestionInput);
    }

    public void resetPassword(View view){
        emailInfo = String.valueOf(emailInput.getText());;
        passwordInfo = String.valueOf(passwordInput.getText());
        confirmPasswordInfo = String.valueOf(confirmPasswordInput.getText());
        securityQuestionInfo = String.valueOf(securityQuestionInput.getText());

        if(db.resetCheck(emailInfo, securityQuestionInfo)){
            ArrayList<User> userList = db.loadUsers();
            for(User elem : userList){
                if(elem.getEmail().matches(emailInfo)){
                    elem.setPassword(passwordInfo);
                    db.updatePassword(elem);
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                }
            }
        } else{
            Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show();
        }

    }



}
