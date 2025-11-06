package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterStaffInfoForEdit extends AppCompatActivity {

    Button bNext;
    EditText eSendStaffId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_staff_info_for_edit);

        bNext = findViewById(R.id.next);
        eSendStaffId = findViewById(R.id.editStaffId);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(), EditStaffDetails.class);
                nextIntent.putExtra("staffId", eSendStaffId.getText().toString());
                startActivity(nextIntent);
            }
        });
    }
}