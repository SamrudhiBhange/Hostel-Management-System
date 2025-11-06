package com.example.hostelmsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // creating a constant variables for admin database.
    public static final String DBNAME = "dbhostel";
    private static final int DB_VERSION = 2;
    public static final String ADMIN_TABLE_NAME= "Admin";
    public static final String ADMIN_ID= "adminId";
    public static final String ADMIN_PASSWORD = "adminPassword";
    public static final String ADMIN_FULL_NAME = "adminFullName";
    public static final String ADMIN_ADDRESS = "adminAddress";
    public static final String ADMIN_CONTACT_NUMBER = "adminContactNumber";
    public static final String ADMIN_EMAIL = "adminEmail";

    // creating variables for staff database
    public static final String STAFF_TABLE_NAME= "Staff";
    public static final String STAFF_ID= "staffId";
    public static final String STAFF_PASSWORD = "staffPassword";
    public static final String STAFF_FULL_NAME = "staffFullName";
    public static final String STAFF_ADDRESS = "staffAddress";
    public static final String STAFF_HOSTEL_ALLOCATION = "staffHostelAllocation";
    public static final String STAFF_CONTACT_NUMBER = "staffContactNumber";
    public static final String STAFF_EMAIL = "staffEmail";


    // creating a constructor for our database handler.
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String adminTable = "CREATE TABLE " + ADMIN_TABLE_NAME + "( "
                + ADMIN_ID + " TEXT PRIMARY KEY,"
                + ADMIN_PASSWORD + " TEXT,"
                + ADMIN_FULL_NAME + " TEXT,"
                + ADMIN_ADDRESS + " TEXT, "
                + ADMIN_CONTACT_NUMBER + " TEXT, "
                + ADMIN_EMAIL + " TEXT "
                +")";

        String staffTable = "CREATE TABLE "+ STAFF_TABLE_NAME +"( "
                + STAFF_ID + " TEXT PRIMARY KEY, "
                + STAFF_PASSWORD + " TEXT, "
                + STAFF_FULL_NAME + " TEXT, "
                + STAFF_ADDRESS + " TEXT, "
                + STAFF_HOSTEL_ALLOCATION + " TEXT, "
                + STAFF_CONTACT_NUMBER + " TEXT, "
                + STAFF_EMAIL + " TEXT "
                + ")";

        db.execSQL(adminTable);
        db.execSQL(staffTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STAFF_TABLE_NAME);
        onCreate(db);
    }


    // inserting admin data into database
    public boolean registerAdmin(String adminId, String adminPassword, String adminFullName, String adminAddress, String adminContactNumber, String adminEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ADMIN_ID, adminId);
        values.put(ADMIN_PASSWORD, adminPassword);
        values.put(ADMIN_FULL_NAME, adminFullName);
        values.put(ADMIN_ADDRESS, adminAddress);
        values.put(ADMIN_CONTACT_NUMBER, adminContactNumber);
        values.put(ADMIN_EMAIL, adminEmail);

        long isInserted = db.insert(ADMIN_TABLE_NAME, null, values);
        db.close();
        if(isInserted == -1)
            return false;
        else
            return true;
    }

    // inserting staff data into database
    public boolean registerStaff(String staffId, String staffPassword, String staffFullName, String staffAddress, String staffHostelAllocation, String staffContactNumber, String staffEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_ID, staffId);
        values.put(STAFF_PASSWORD, staffPassword);
        values.put(STAFF_FULL_NAME, staffFullName);
        values.put(STAFF_ADDRESS, staffAddress);
        values.put(STAFF_HOSTEL_ALLOCATION, staffHostelAllocation);
        values.put(STAFF_CONTACT_NUMBER, staffContactNumber);
        values.put(STAFF_EMAIL, staffEmail);

        long isInserted = db.insert(STAFF_TABLE_NAME, null, values);
        db.close();
        if(isInserted == -1)
            return false;
        else
            return true;
    }

    // edit staff data
    public boolean updateStaffData(String staffId, String staffPassword, String staffFullName, String staffAddress, String staffHostelAllocation, String staffContactNumber, String staffEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_PASSWORD, staffPassword);
        values.put(STAFF_FULL_NAME, staffFullName);
        values.put(STAFF_ADDRESS, staffAddress);
        values.put(STAFF_HOSTEL_ALLOCATION, staffHostelAllocation);
        values.put(STAFF_CONTACT_NUMBER, staffContactNumber);
        values.put(STAFF_EMAIL, staffEmail);
        return db.update(STAFF_TABLE_NAME, values, STAFF_ID + " = ?", new String[]{staffId}) > 0;
    }

    // check if admin id exist  or not
    public Boolean checkAdminName(String adminId){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select *from  Admin where adminId = ?", new String[] {adminId});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // viewing staff details
    public Cursor viewStaffDetails(){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        String query = "select *from " + STAFF_TABLE_NAME + " order by " + STAFF_ID + " desc";
        Cursor cursor = MyDb.rawQuery(query, null);
        return  cursor;
    }

    // check if staff id exist  or not
    public Boolean checkStaffName(String staffId){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select *from  Staff where staffId = ?", new String[] {staffId});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // check login details
    public boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor= MyDb.rawQuery("select *from Admin where adminId = ? and adminPassword = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkUserNamePassword2(String username, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor= MyDb.rawQuery("select *from Staff where staffID = ? and staffPassword = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor viewData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Staff ",null);
        return cursor;
    }
}