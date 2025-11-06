package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button bSignUp, bSignIn;
    EditText eUsername, ePassword;

    DatabaseHelper dbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        bSignUp = findViewById(R.id.signUp);
        bSignIn = findViewById(R.id.signIn);
        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);

        dbb = new DatabaseHelper(this);

        // Registering Admin
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(getApplicationContext(), AdminSignUp.class);
                startActivity(signUpIntent);
            }
        });

        // login as Admin
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eUsername.getText().toString();
                String password = ePassword.getText().toString();

                if (TextUtils.isEmpty(username))
                    eUsername.setError("Please Enter Id");
                if (TextUtils.isEmpty(password))
                    ePassword.setError("please Enter Password");
                if (ePassword.length() < 8)
                    ePassword.setError("Use more than 8 characters");
                else {
                    login();
                }
            }
        });
    }

    //
    public void login(){
        boolean var = dbb.checkUserNamePassword(eUsername.getText().toString(), ePassword.getText().toString());
        if(var) {
            Toast.makeText(AdminLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(getApplicationContext(), AdminManageStaff.class);
            startActivity(loginIntent);
        }
        else
            Toast.makeText(AdminLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}