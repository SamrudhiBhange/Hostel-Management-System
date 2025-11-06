package com.example.hostelmsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewStaffDetails extends AppCompatActivity {

    RecyclerView rViewStaffData;
    ArrayList<StaffModule> staffDetailsHolder = new ArrayList<>();
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff_details);

        rViewStaffData = findViewById(R.id.viewStaffData);
        rViewStaffData.setLayoutManager(new LinearLayoutManager(this));

        cursor = new DatabaseHelper(this).viewStaffDetails();
        while(cursor.moveToNext()){
            StaffModule staffModule = new StaffModule(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            staffDetailsHolder.add(staffModule);
        }
        MyAdapter adapter = new MyAdapter(staffDetailsHolder);
        rViewStaffData.setAdapter(adapter);
    }
}