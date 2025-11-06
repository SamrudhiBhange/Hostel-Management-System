package com.example.hostelmsa;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StaffStudView    extends AppCompatActivity {
    Button view, edit, reg,att;
    DBStudentRegister db;
    DBHelperStudentAttendance db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_stud_view);
        view = findViewById(R.id.staff_stud_view);
        edit = findViewById(R.id.staff_stud_edit);
        reg = findViewById(R.id.staff_stud_register);
        att=findViewById(R.id.getAttendance);
        db=new DBStudentRegister(this);
        db2=new DBHelperStudentAttendance(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.viewData();
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffStudView.this,"Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer s=new StringBuffer();
                while(res.moveToNext())
                {
                    s.append("Student ID: "+res.getString(0)+"\n");
                    s.append("Name: "+res.getString(1)+"\n");
                    s.append("Mess_no: "+res.getString(9)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(StaffStudView.this);
                builder.setCancelable(true);
                builder.setTitle("Student Details");
                builder.setMessage(s.toString());
                builder.show();

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StaffStudView.this,StaffStudEdit.class);
                startActivity(i);

            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StaffStudView.this,StudentRegistration.class);
                startActivity(i);
            }
        });
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db2.viewData();
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffStudView.this,"Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer s=new StringBuffer();
                while(res.moveToNext())
                {
                    s.append("Student ID: "+res.getString(0)+"\n");
                    s.append("Date: "+res.getString(1)+"\n");
                    s.append("Time: "+res.getString(2)+"\n");
                    s.append("Status: "+res.getString(3)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(StaffStudView.this);
                builder.setCancelable(true);
                builder.setTitle("Student Details");
                builder.setMessage(s.toString());
                builder.show();
            }
        });
    }
}
