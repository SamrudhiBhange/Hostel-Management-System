package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterNewStaff extends AppCompatActivity {

    Button registerStaff;
    EditText eName, eStaffId, eAddress, eContactNumber, eEmail, ePassword, eHostelNumber;
    String EMAIL_STRING = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseHelper dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_staff);

        registerStaff = findViewById(R.id.registerstaff);
        eName = findViewById(R.id.staffName);
        eStaffId = findViewById(R.id.staffId);
        eAddress = findViewById(R.id.staffAddress);
        eContactNumber = findViewById(R.id.staffContact);
        eEmail = findViewById(R.id.staffEmail);
        ePassword = findViewById(R.id.staffPassword);
        eHostelNumber = findViewById(R.id.hostelAllocation);

        dbb = new DatabaseHelper(RegisterNewStaff.this);

        // register staff
        registerStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validating EditTexts, Email and mobile number
                if(TextUtils.isEmpty(eName.getText().toString()) )
                    eName.setError("Please Enter name");
                if(TextUtils.isEmpty(eStaffId.getText().toString()) )
                    eStaffId.setError("Please Enter Correct Id");
                if(TextUtils.isEmpty(eHostelNumber.getText().toString()))
                    eHostelNumber.setError("Please enter hostel number to be assigned");
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
                else
                    registerNewStaff();
            }
        });

    }

    // register staff
    void registerNewStaff(){
        String staffId = eStaffId.getText().toString();
        String staffPassword = ePassword.getText().toString();
        String staffFullName = eName.getText().toString();
        String staffHostelAllocation = eHostelNumber.getText().toString();
        String staffContact = eContactNumber.getText().toString();
        String staffEmail = eEmail.getText().toString();
        String staffAddress = eAddress.getText().toString();

        Boolean checkStaff = dbb.checkStaffName(staffId);
        if(!checkStaff) {
            boolean result = dbb.registerStaff(staffId, staffPassword, staffFullName, staffAddress, staffHostelAllocation, staffContact, staffEmail);
            if (result) {
                Toast.makeText(RegisterNewStaff.this, "Staff Registered Successfully.", Toast.LENGTH_SHORT).show();
                Intent staffRegisterSuccessIntent = new Intent(getApplicationContext(), AdminManageStaff.class);
                startActivity(staffRegisterSuccessIntent);
            } else
                Toast.makeText(RegisterNewStaff.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(RegisterNewStaff.this, "User Already Exists! please sign in", Toast.LENGTH_SHORT).show();
        }
    }
}