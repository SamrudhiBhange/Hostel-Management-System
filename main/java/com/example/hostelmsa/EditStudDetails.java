package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudDetails extends AppCompatActivity {

    Button bRegisterStudent;
    EditText student_id;
    EditText student_name;
    EditText student_address;
    EditText contact_no;
    EditText emailID;
    EditText college_name;
    EditText parents_contact;
    EditText date_of_joining;
    EditText Mess_no;
    EditText pass;
    EditText Room_no;
    DBStudentRegister db;
    EditText ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stud_details);

        bRegisterStudent = findViewById(R.id.register_Student);
        student_id = findViewById(R.id.studentId);
        student_name = findViewById(R.id.studentName);
        student_address = findViewById(R.id.studentAddress);
        contact_no = findViewById(R.id.studentContactNo);
        college_name = findViewById(R.id.studentCollegeName);
        emailID = findViewById(R.id.studentEmail);
        date_of_joining = findViewById(R.id.studentJoiningDate);
        Mess_no = findViewById(R.id.studentMessNumber);
        parents_contact = findViewById(R.id.studentParentsContactInfo);
        pass=findViewById(R.id.studentPass);
        Room_no=findViewById(R.id.studentRoomNumber);
        db=new DBStudentRegister(this);
        Intent bundle=getIntent();
        String id=bundle.getStringExtra("st");

        bRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=student_name.getText().toString();
                String add=student_address.getText().toString();
                String phno=contact_no.getText().toString();
                String clg=college_name.getText().toString();
                String email=emailID.getText().toString();
                String pawd=pass.getText().toString();
                String jod=date_of_joining.getText().toString();
                String mess=Mess_no.getText().toString();
                String parent=parents_contact.getText().toString();
                String room=Room_no.getText().toString();
                Bundle ex=getIntent().getExtras();
                if(db.update(id,nm,add,phno,clg,email,pawd,parent,jod,mess,room))
                {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(student_id)) {
            Toast t = Toast.makeText(this, "You must enter ID to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(pass)) {
            Toast t = Toast.makeText(this, "You must enter password to register!", Toast.LENGTH_SHORT);

            t.show();
        }
        if (isEmpty(student_name)) {
            student_name.setError("Full name is required!");
        }

        if (isEmail(emailID) == false) {
            emailID.setError("Enter valid email!");
        }

        if (isEmpty(parents_contact)) {
            parents_contact.setError("Enter your parents contact number");
        }

        if (isEmpty(contact_no)) {
            contact_no.setError("Enter your contact number");
        }

        if (isEmpty(student_address)) {
            student_address.setError("Enter your permanent address");
        }

        if (isEmpty(date_of_joining)) {
            date_of_joining.setError("Enter valid date of joining");
        }

        if (isEmpty(Mess_no)) {
            Mess_no.setError("Please enter valid Mess number");
        }

        if (isEmpty(college_name)) {
            college_name.setError("Enter College name");
        }

    }


}

