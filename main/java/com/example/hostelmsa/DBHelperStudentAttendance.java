package com.example.hostelmsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperStudentAttendance extends SQLiteOpenHelper {
    public DBHelperStudentAttendance(@Nullable Context context) {
        super(context,"StudentAtt.db", null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table StudentAtt(ID text,date text,time text,status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists StudentAtt");
    }
    public Boolean insert(String id,String date,String time,String status)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("status",status);
        long result=DB.insert("StudentAtt",null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor viewData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from StudentAtt ",null);
        return cursor;
    }
    public Cursor viewData(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from StudentAtt where ID=?",new String[]{id});
        return cursor;
    }

}
