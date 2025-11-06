package com.example.hostelmsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBStudentRegister extends SQLiteOpenHelper {

    public DBStudentRegister(@Nullable Context context) {
        super(context,"Student.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
     DB.execSQL("create table Student(stud_id text primary key,name text,address text,contact text,mail_id text,pass text,clg_name text,parent_contact text,jod text,mess_no text,room_no text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop table if exists Student");

    }

    public Boolean insert(String id, String name, String add, String contact, String mail, String pass, String clg, String p_contact, String jod, String mess_no,String room_no) {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("stud_id",id);
        contentValues.put("name",name);
        contentValues.put("address",add);
        contentValues.put("contact",contact);
        contentValues.put("mail_id",mail);
        contentValues.put("pass",pass);
        contentValues.put("clg_name",clg);
        contentValues.put("parent_contact",p_contact);
        contentValues.put("jod",jod);
        contentValues.put("mess_no",mess_no);
        //contentValues.put("room_no",room_no);
        long result=DB.insert("Student",null,contentValues);
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
        Cursor cursor = DB.rawQuery("select * from Student ",null);
        return cursor;
    }
    public Boolean update(String id, String name, String add, String contact, String mail, String pass, String clg, String p_contact, String jod, String mess_no,String room_no) {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("stud_id",id);
        contentValues.put("name",name);
        contentValues.put("address",add);
        contentValues.put("contact",contact);
        contentValues.put("mail_id",mail);
        contentValues.put("pass",pass);
        contentValues.put("clg_name",clg);
        contentValues.put("parent_contact",p_contact);
        contentValues.put("jod",jod);
        contentValues.put("mess_no",mess_no);
        //contentValues.put("room_no",room_no);
        Cursor cursor = DB.rawQuery("select * from Student where stud_id=?", new String[]{id});
        long res = DB.update("Student", contentValues, "stud_id=?", new String[]{id});
        if(res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public void delete() {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("delete from Student");
    }
    public Boolean delete(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Student where stud_id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long res = DB.delete("Student", "stud_id=?", new String[]{id});
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}
