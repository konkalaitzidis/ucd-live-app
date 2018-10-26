package com.example.ucdlive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UCDLive.db";
    public static final String TABLE_NAME = "user";
    public static final String USER_USERNAME = "username";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (ID integer primary key , name varchar, surname varchar, birthday varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", u.getID());
        contentValues.put("name", u.getName());
        contentValues.put("surname", u.getSurname());
        contentValues.put("birthday", u.getBirthday().toString());
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

    /**
     * @return null if there isn't a user, else return the username of current user
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public User getCurrentUser(){
        if(existsUser()){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery( "select * from user", null );
            if (res.moveToFirst()){
                int IDuser = res.getInt(res.getColumnIndex("ID"));
                String name = res.getString(res.getColumnIndex("name"));
                String surname = res.getString(res.getColumnIndex("surname"));
                LocalDate birthday = LocalDate.parse(res.getString(res.getColumnIndex("surname")));
                return new User(IDuser, name, surname, birthday);
            }
        }
        return null;
    }

}
