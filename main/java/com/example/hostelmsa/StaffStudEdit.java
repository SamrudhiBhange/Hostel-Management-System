package com.example.hostelmsa;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StaffStudEdit extends AppCompatActivity {
    Button att,edit,remove;
    EditText id;
    DBStudentRegister db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_stud_edit);
        att = findViewById(R.id.att);
        edit=findViewById(R.id.edit);
        remove=findViewById(R.id.remove);
        id=findViewById(R.id.stud_id);
        db=new DBStudentRegister(this);


        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(id)) {
                    Toast.makeText(StaffStudEdit.this,"You must enter the ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(StaffStudEdit.this, StudAttendance.class);
                    i.putExtra("st",id.getText().toString());
                    startActivity(i);
                }



            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(id)) {
                    Toast.makeText(StaffStudEdit.this,"You must enter the ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i= new Intent(StaffStudEdit.this,EditStudDetails.class);
                    i.putExtra("st",id.getText().toString());
                    startActivity(i);
                }

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(id)) {
                    Toast.makeText(StaffStudEdit.this,"You must enter the ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.delete(id.getText().toString()))
                    {
                        Toast.makeText(StaffStudEdit.this,"Removed",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(StaffStudEdit.this,"Failed",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}
//    public void back(View view){
//       Intent i=new Intent(Page6_sse.this,Page7_sse2.class);
//       startActivity(i);
//    }
