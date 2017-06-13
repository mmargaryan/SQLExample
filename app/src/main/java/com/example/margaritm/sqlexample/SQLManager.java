package com.example.margaritm.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AutoCompleteTextView;

/**
 * Created by margaritm on 6/12/2017.
 */

public class SQLManager {
    private SQLiteHelper SqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SQLManager(Context context) {
        SqlHelper = new SQLiteHelper(context);
    }

    public void open() {
        sqLiteDatabase = SqlHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public long insertData(String username, String password) {
        open();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.USERNAME, username);
        values.put(SQLiteHelper.PASSWORD, password);
        long id = sqLiteDatabase.insert(SQLiteHelper.TABLE_NAME, null, values);
        close();
        return id;
    }

    public String getAllData() {
        open();
        String[] columns = {SqlHelper.UID, SqlHelper.USERNAME, SqlHelper.PASSWORD};
        Cursor cursor = sqLiteDatabase.query(SQLiteHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            buffer.append("id: " + id + " \n" + "username: " + username + " \n" + "passowrd: " + password + "\n\n");
        }

        close();
        return buffer.toString();
    }

    public String getData(String name) {
        open();
        String[] columns = {SqlHelper.UID, SqlHelper.USERNAME, SqlHelper.PASSWORD};
        Cursor cursor = sqLiteDatabase.query(SQLiteHelper.TABLE_NAME, columns, SqlHelper.USERNAME + " = '" + name + "'", null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String password = cursor.getString(2);
            buffer.append("username: " + name + " \n" + "passowrd: " + password + "\n\n");
        }

        close();
        return buffer.toString();
    }

    public String getUserData(String name, String password) {
        open();
        String[] columns = {SqlHelper.UID, SqlHelper.USERNAME, SqlHelper.PASSWORD};
        String[] selectionArgs = {name, password};

        Cursor cursor = sqLiteDatabase.query(SQLiteHelper.TABLE_NAME, columns, SqlHelper.USERNAME + " =? AND " + SqlHelper.PASSWORD + " =?", selectionArgs, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index0 = cursor.getColumnIndex(SQLiteHelper.UID);
            int index1 = cursor.getColumnIndex(SQLiteHelper.USERNAME);
            int index2 = cursor.getColumnIndex(SQLiteHelper.PASSWORD);
            String user = cursor.getString(index1);
            String pass = cursor.getString(index2); 
            buffer.append("username: " + user + "   " + "passowrd: " + pass + "   \n");
        }

        close();
        return buffer.toString();
    }

    public void updateName(String oldName, String newName) {
        open();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.USERNAME, newName);
        String[] whereArgs = {oldName};
        sqLiteDatabase.update(SQLiteHelper.TABLE_NAME, values, SQLiteHelper.USERNAME + " =? ", whereArgs);
        close();
    }

    public int  delete(String name) {
        open();
        String[] whereArgs = {name};
       int count =  sqLiteDatabase.delete(SQLiteHelper.TABLE_NAME, SQLiteHelper.USERNAME + "=?", whereArgs);
        close();

        return  count;
    }

}
