package com.example.hostelmsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class StaffLogin extends AppCompatActivity {

    Button login;
    TextInputLayout pass,uname;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_staff_login);

        login = findViewById(R.id.login1);
        pass = findViewById(R.id.password);
        uname = findViewById(R.id.username);
        db=new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //db.delete();
                String username = uname.toString();
                String password = pass.toString();

                if (TextUtils.isEmpty(username))
                    uname.setError("Please Enter Id");
                if (TextUtils.isEmpty(password))
                    pass.setError("please Enter Password");
                if (pass.toString().length() < 8)
                    pass.setError("Use more than 8 characters");
                else {
                    login();
                }

            }
        });
    }
    public void login(){
        boolean var = db.checkUserNamePassword2(uname.getEditText().getText().toString(), pass.getEditText().getText().toString());
        if(var) {
            Toast.makeText(StaffLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(getApplicationContext(), StaffPage2.class);
            startActivity(loginIntent);
        }
        else
            Toast.makeText(StaffLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}