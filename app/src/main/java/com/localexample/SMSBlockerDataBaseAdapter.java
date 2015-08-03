package com.localexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 * Created by Guddu on 7/30/2015.
 */
public class SMSBlockerDataBaseAdapter
{
    // Name of the database
    static final String DATABASE_NAME = "SMSBLOCKERDATABASE.db";

    // database version  if creating first time it should be 1
    static final int DATABASE_VERSION = 1;

    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table BLOCKEDSMSTABLE " +
            "( " +"ID integer primary key autoincrement,WEBSITE_NAME text, WEBSITE_MODULE text, WEBSITE_KEY text, TIME integer ); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public SMSBlockerDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Open the Database
    public SMSBlockerDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }


    // Close the Database
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // to Insert A record in Table
    public void insertEntry(String website_name,String website_module,String website_key, int time)
    {
        // TODO: Create a new ContentValues to represent the row
        // and insert it into the database.
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("WEBSITE_NAME", website_name);
        newValues.put("WEBSITE_MODULE",website_module);
        newValues.put("WEBSITE_KEY", website_key);
        newValues.put("TIME",time);
        // Insert the row into your table
        db.insert("BLOCKEDSMSTABLE", null, newValues);
    }
    public int deleteEntry(String ID)
    {


        String where="ID=?";
        int numberOFEntriesDeleted= db.delete("BLOCKEDSMSTABLE", where, new String[]{ID}) ;

        return numberOFEntriesDeleted;

    }

    public void deleteOlderEntries()
    {
        String olderTime=String.valueOf(new GregorianCalendar().getTimeInMillis()-7*24*60*60*1000);
        String where="TIME < ?";
        int numberOFEntriesDeleted= db.delete("BLOCKEDSMSTABLE", where, new String[]{olderTime}) ;
        Toast.makeText(context, "Number Of Entries Deleted " + numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
    }
    public Cursor getAllEntries ()
    {

        return db.query("BLOCKEDSMSTABLE", null,null, null, null, null, "TIME DESC");
    }

    public String getSinlgeEntry(String WEBSITE ,String MODULE)
    {

        Cursor cursor=db.query("BLOCKEDSMSTABLE", null, " WEBSITE_NAME=? AND WEBSITE_MODULE=?", new String[]{WEBSITE , MODULE },null, null, null);
        if(cursor.getCount()==0)
        {

            return null;
        }
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex("ID"));
        String website_name= cursor.getString(cursor.getColumnIndex("WEBSITE_NAME"));
        String module = cursor.getString(cursor.getColumnIndex("WEBSITE_MODULE"));
        String key = cursor.getString(cursor.getColumnIndex("WEBSITE_KEY"));
        Long time = Long.parseLong(cursor.getString(cursor.getColumnIndex("TIME")));

        //Log.i("getSingle Entry ID: "+"PhoneNumber "+task.senderName+"  "+task.message,ID);
        cursor.close();
        return module;
    }
    public String getSinlgeEntry2(String WEBSITE ,String MODULE)
    {

        Cursor cursor=db.query("BLOCKEDSMSTABLE", null, " WEBSITE_NAME=? AND WEBSITE_MODULE=? ", new String[]{WEBSITE ,MODULE}, null, null, null);

        if(cursor.getCount()==0)
        {

            return null;
        }
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex("ID"));
        String website_name= cursor.getString(cursor.getColumnIndex("WEBSITE_NAME"));
        String module = cursor.getString(cursor.getColumnIndex("WEBSITE_MODULE"));
        String key = cursor.getString(cursor.getColumnIndex("WEBSITE_KEY"));
        Long time = Long.parseLong(cursor.getString(cursor.getColumnIndex("TIME")));

        //Log.i("getSingle Entry ID: "+"PhoneNumber "+task.senderName+"  "+task.message,ID);
        cursor.close();
        return key;
    }
}

