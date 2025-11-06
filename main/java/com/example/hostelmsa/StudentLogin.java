package com.example.hostelmsa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class StudentLogin extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +         // no white spaces
                    ".{4,}" +             // at least 4 characters
                    "$");
    EditText userNameEdt, passwordEdt;
    Button loginBtn, signupBtn;
    DBStudentRegister db;



    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        // initializing our edit text  and buttons.
        userNameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signUp);
        db=new DBStudentRegister(this);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                isAllFieldsChecked = CheckAll();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {

                    Cursor res=db.viewData();
                    if(res.getCount()==0)
                    {
                        Toast.makeText(StudentLogin.this,"Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer s=new StringBuffer();
                    while(res.moveToNext())
                    {
                        s.append("Student ID: "+res.getString(0)+"\n");
                        s.append("Name: "+res.getString(1)+"\n");
                        s.append("Address: "+res.getString(2)+"\n");
                        s.append("Contact Number: "+res.getString(3)+"\n");
                        s.append("College Name: "+res.getString(4)+"\n");
                        s.append("Email ID: "+res.getString(5)+"\n");
                        s.append("Password: "+res.getString(6)+"\n");
                        s.append("Parents Contact Number: "+res.getString(7)+"\n");
                        s.append("Joining Date: "+res.getString(8)+"\n");
                        s.append("Mess Number: "+res.getString(9)+"\n\n\n");
                        // s.append("Hostel Number: "+res.getString(10)+"\n\n\n");

                    }

                    final String Col_1 = "ID";
                    final String Col_2 = "Name";
                    final String Col_3 = "Address";
                    final String Col_4 = "Contact Number";
                    final String Col_5 = "College Name";
                    final String Col_6 = "Email ID";
                    final String Col_7 = "Password";
                    final String Col_8 = "Parents Contact Info";
                    final String Col_9 = "Joining Date";
                    final String Col_10 = "Mess Number";
                    // final String Col_11 = "Hostel Number";



                    AlertDialog.Builder builder = new AlertDialog.Builder(StudentLogin.this);
                    builder.setCancelable(true);
                    builder.setTitle("Student Details");
                    builder.setMessage(s.toString());
                    builder.show();


                }
            }
        });


    }


    private boolean CheckAllFields() {

        String emailInput = userNameEdt.getText().toString().trim();


        if (emailInput.isEmpty()) {
            userNameEdt.setError("Field can not be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            userNameEdt.setError("Please enter a valid email address");
            return false;
        } else {
            userNameEdt.setError(null);
            return true;
        }
    }

    private boolean CheckAll() {
        String passwordInput = passwordEdt.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            passwordEdt.setError("Field can not be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordEdt.setError("Password is too weak");
            return false;
        } else {
            passwordEdt.setError(null);
            return true;
        }
    }
}