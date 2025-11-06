package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StaffPage2 extends AppCompatActivity {
    Button staffView,studentView;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page2);
        staffView=findViewById(R.id.staff_view);
        studentView=findViewById(R.id.staff_student);
        db=new DatabaseHelper(this);
        staffView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.viewData();
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffPage2.this,"Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer s=new StringBuffer();
                while(res.moveToNext())
                {
                    s.append("Staff ID: "+res.getString(0)+"\n");
                    s.append("Name: "+res.getString(2)+"\n");
                    s.append("Contact no: "+res.getString(4)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(StaffPage2.this);
                builder.setCancelable(true);
                builder.setTitle("Student Details");
                builder.setMessage(s.toString());
                builder.show();

            }
        });
        studentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2=new Intent(StaffPage2.this,StaffStudView.class);
                startActivity(int2);
            }
        });
    }
}