package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bAdmin, bStaff, bStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAdmin = findViewById(R.id.admin);
        bStaff = findViewById(R.id.staff);
        bStudent = findViewById(R.id.student);

        // logging to admin module
        bAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(adminIntent);
            }
        });

        // logging to staff module
        bStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staffIntent = new Intent(getApplicationContext(),StaffLogin.class);
                startActivity(staffIntent);
            }
        });
        bStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentIntent=new Intent(getApplicationContext(),StudentLogin.class);
                startActivity(studentIntent);
            }
        });
    }


}