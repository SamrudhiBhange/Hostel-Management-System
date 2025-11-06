package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageStaff extends AppCompatActivity {

    Button bRegisterNewStaff, bViewStaffDetails, bEditStaffDetails, bMarkStaffAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_staff);

        bRegisterNewStaff = findViewById(R.id.registerNewStaff);
        bViewStaffDetails = findViewById(R.id.viewDetails);
        bEditStaffDetails = findViewById(R.id.editStaffDetails);
        bMarkStaffAttendance = findViewById(R.id.markAttendance);

        // Registering new staff
        bRegisterNewStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });

        // view Staff Details
        bViewStaffDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToViewDetails();
            }
        });

        // edit staff details
        bEditStaffDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditStaffDetails();
            }
        });

        // mark staff attendance
        bMarkStaffAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMarkAttendance();
            }
        });
    }

    // Registering new Staff
    public void goToRegister(){
        Intent staffRegisterIntent = new Intent(getApplicationContext(), RegisterNewStaff.class);
        startActivity(staffRegisterIntent);
    }

    // view Staff Details
    public void goToViewDetails(){
        Intent viewStaffDetailsIntent = new Intent(getApplicationContext(), ViewStaffDetails.class);
        startActivity(viewStaffDetailsIntent);
    }

    // edit staff details
    public void goToEditStaffDetails(){
        Intent editStaffDetailsIntent = new Intent(getApplicationContext(), EnterStaffInfoForEdit.class);
        startActivity(editStaffDetailsIntent);
    }

    // mark staff attendance
    public void goToMarkAttendance(){
        Intent markStaffAttendanceIntent = new Intent(getApplicationContext(), MarkStaffAttendance.class);
        startActivity(markStaffAttendanceIntent);
    }

}