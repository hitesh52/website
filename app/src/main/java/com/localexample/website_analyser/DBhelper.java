package com.localexample.website_analyser;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

   // public static String query="create table login16 (user_id integer primary key autoincrement, username text, password text)";
    public static String column1="username";
    public static String column2="password";
    static int db_version=1;
    public static String db_name="my_db";
    public static String db_table="login  ";
        //database name = my_db
        //database version = 1;
    public DBhelper(Context context) {
        super(context, db_name, null, db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table login " +
                        "( username text , password text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
         //method for saving records in database
    public void saverecord(String s1 , String s2)
    {

        SQLiteDatabase obj = getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(column1,s1 );
        cv.put(column2,s2 );
        obj.insert(db_table ,null, cv);
        obj.close();


    }


}


