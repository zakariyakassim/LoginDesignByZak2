package com.example.zakar.logindesignbyzak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "Database";
    private static final String TABLE = "Users";


    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "create table "+"users"+
                "( " +"ID"+" integer primary key autoincrement,"+ "username  text,password text); ";

        db.execSQL(DATABASE_CREATE);
     //  db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + "(" + ID + " integer primary key autoincrement not null, " + USERNAME + " VARCHAR, " + PASSWORD + " VARCHAR);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }


    public void insertEntry(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("username", userName);
        newValues.put("password", password);

        // Insert the row into your table
        db.insert("users", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        db.close();
    }

    public int deleteEntry(String UserName) {
        SQLiteDatabase db = this.getWritableDatabase();
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        db.close();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("users", null, " username=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            db.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        db.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
        db.close();
    }


    public ArrayList<User> getUsernames() {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String getUsername = cursor.getString(cursor
                        .getColumnIndex(USERNAME));
                String getPassword = cursor.getString(cursor
                        .getColumnIndex(PASSWORD));

                list.add(new User(getUsername, getPassword));
            }
        }

        return list;
    }


    public ArrayList<User> getPassword(String username) {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + USERNAME + "='" + username + "'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE.trim() + " WHERE TRIM(username) = '" + username.trim() + "'", null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String getUsername = cursor.getString(cursor
                        .getColumnIndex(USERNAME));
                String getPassword = cursor.getString(cursor
                        .getColumnIndex(PASSWORD));

                list.add(new User(getUsername, getPassword));
            }
        }

        return list;
    }

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);

        // Inserting Row
        db.insert(TABLE, null, values);
        db.close(); // Closing database connection
    }


}