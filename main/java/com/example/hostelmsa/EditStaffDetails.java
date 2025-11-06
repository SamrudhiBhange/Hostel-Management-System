package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStaffDetails extends AppCompatActivity {

    Button bSave;
    EditText eName, eStaffId, eAddress, eContactNumber, eEmail, ePassword, eHostelNumber;
    String EMAIL_STRING = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseHelper dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff_details);

        bSave = findViewById(R.id.saveStaff);
        eName = findViewById(R.id.editStaffName);
        eStaffId = findViewById(R.id.editStaffId);
        eAddress = findViewById(R.id.editStaffAddress);
        eContactNumber = findViewById(R.id.editStaffContact);
        eEmail = findViewById(R.id.editStaffEmail);
        ePassword = findViewById(R.id.editStaffPassword);
        eHostelNumber = findViewById(R.id.editHostelAllocation);

        dbb = new DatabaseHelper(EditStaffDetails.this);

        // updating staff details

        Intent intent = getIntent();
        String getIntentStaffId = intent.getStringExtra("staffId");
        findRecordToUpdateData(getIntentStaffId);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    updateStaffDetails();
            }
        });
    }

    public void  updateStaffDetails(){
//
//        if(isPresent) {
            String staffId = eStaffId.getText().toString();
            String staffPassword = ePassword.getText().toString();
            String staffFullName = eName.getText().toString();
            String staffHostelAllocation = eHostelNumber.getText().toString();
            String staffContact = eContactNumber.getText().toString();
            String staffEmail = eEmail.getText().toString();
            String staffAddress = eAddress.getText().toString();

            boolean result = dbb.updateStaffData(staffId, staffPassword, staffFullName, staffAddress, staffHostelAllocation, staffContact, staffEmail);
            if (result) {
                Toast.makeText(EditStaffDetails.this, "Updated Staff Details Successfully.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(EditStaffDetails.this, "Updating Failed", Toast.LENGTH_SHORT).show();
//        }
    }


    // finding id of record from database to update
    public void findRecordToUpdateData(String id){
        boolean res = false;
        SQLiteDatabase db = dbb.getWritableDatabase();
        String query = "SELECT *FROM Staff where staffId = ?";
        Cursor cursor = db.rawQuery(query, new String[] {id});
        while(cursor.moveToNext()){
                String fetchStaffId = cursor.getString(0);
                String fetchStaffPassword = cursor.getString(1);
                String fetchStaffName = cursor.getString(2);
                String fetchStaffAddress = cursor.getString(3);
                String fetchStaffHostelAllocation = cursor.getString(4);
                String fetchStaffContact = cursor.getString(5);
                String fetchStaffEmail = cursor.getString(6);

                eStaffId.setText(fetchStaffId);
                ePassword.setText(fetchStaffPassword);
                eName.setText(fetchStaffName);
                eHostelNumber.setText(fetchStaffHostelAllocation);
                eContactNumber.setText(fetchStaffContact);
                eEmail.setText(fetchStaffEmail);
                eAddress.setText(fetchStaffAddress);
                cursor.close();
        }
    }
}