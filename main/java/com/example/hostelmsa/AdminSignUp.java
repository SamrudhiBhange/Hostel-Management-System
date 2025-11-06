package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminSignUp extends AppCompatActivity {

    Button bSignUp;
    EditText eName, eAddress, eContactNumber, eEmail, ePassword, eAdminId;
    String EMAIL_STRING = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private DatabaseHelper dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        bSignUp = findViewById(R.id.registerSignUp);
        eName = findViewById(R.id.adminName);
        eAddress = findViewById(R.id.adminAddress);
        eContactNumber = findViewById(R.id.adminContact);
        eEmail = findViewById(R.id.adminEmail);
        ePassword = findViewById(R.id.adminPassword);
        eAdminId = findViewById(R.id.adminId);

        dbb = new DatabaseHelper(AdminSignUp.this);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validating EditTexts, Email and mobile number
                if(TextUtils.isEmpty(eName.getText().toString()) )
                    eName.setError("Please Enter name");
                if(TextUtils.isEmpty(eAdminId.getText().toString()) )
                    eName.setError("Please Enter Correct Id");
                if(TextUtils.isEmpty(eAddress.getText().toString()))
                    eAddress.setError("Please Enter address");
                if(TextUtils.isEmpty(eContactNumber.getText().toString()))
                    eContactNumber.setError("Please Enter Contact number");
                if(!android.util.Patterns.PHONE.matcher(eContactNumber.getText()).matches())
                    eContactNumber.setError("Please Enter right Contact Number");
                if(TextUtils.isEmpty(eEmail.getText().toString()))
                    eEmail.setError("Please Enter Email");
                if(!eEmail.getText().toString().matches(EMAIL_STRING))
                    eEmail.setError("Please Enter right Email Address");
                if(TextUtils.isEmpty(ePassword.getText().toString()))
                    ePassword.setError("please Enter Password");
                else{
                    String adminId = eAdminId.getText().toString();
                    String adminPassword = ePassword.getText().toString();
                    String adminFullName = eName.getText().toString();
                    String adminContact = eContactNumber.getText().toString();
                    String adminEmail = eEmail.getText().toString();
                    String adminAddress = eAddress.getText().toString();

                    Boolean checkAdmin = dbb.checkAdminName(adminId);
                    if(!checkAdmin) {
                        boolean result = dbb.registerAdmin(adminId, adminPassword, adminFullName, adminAddress, adminContact, adminEmail);
                        if (result) {
                            Toast.makeText(AdminSignUp.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();
                            Intent signUpSuccessIntent = new Intent(getApplicationContext(),AdminLogin.class);
                            startActivity(signUpSuccessIntent);
                        } else
                            Toast.makeText(AdminSignUp.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AdminSignUp.this, "User Already Exists! please sign in", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}