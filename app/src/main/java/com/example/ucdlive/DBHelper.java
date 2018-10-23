package com.example.ucdlive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UCDLive.db";
    public static final String TABLE_NAME = "user";
    public static final String USER_USERNAME = "username";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (username integer primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(int username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        db.insert("user", null, contentValues);
        return true;
    }

    /**
     * check if there is a user on the db
     * @return 1 if there is a user on the db, 0 if not
     * */
    public boolean existsUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user", null );
        return res.getCount() == 1;
    }

}
