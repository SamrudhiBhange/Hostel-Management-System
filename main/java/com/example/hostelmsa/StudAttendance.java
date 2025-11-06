package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;

public class StudAttendance extends AppCompatActivity {
    Spinner s;
    EditText ed1;
    EditText ed2;
    EditText ed3;

    Button b1;
    DBHelperStudentAttendance db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_attendance);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:MM:SS");
        Date date = new Date();
        ed1=findViewById(R.id.date);
        ed2=findViewById(R.id.time);
        ed3=findViewById(R.id.student_id);
        ed1.setText(formatter.format(date));
        ed2.setText(formatter2.format(date));
        s=findViewById(R.id.att_status);

        b1=findViewById(R.id.submit);
        db=new DBHelperStudentAttendance(this);
        Intent bundle=getIntent();
        String id=bundle.getStringExtra("st");
        ed3.setText(id);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spin= (String) s.getSelectedItem();
                if(db.viewData(id).getCount()==0)
                {
                    if(db.insert(ed3.getText().toString(), ed1.getText().toString(),ed2.getText().toString(),spin))
                    {
                        Toast.makeText(getApplicationContext(), "Attendance Marked", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Already Marked", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}