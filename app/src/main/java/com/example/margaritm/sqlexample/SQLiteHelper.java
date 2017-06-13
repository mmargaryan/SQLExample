package com.example.margaritm.sqlexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Matrix;
import android.os.Message;
import android.util.Log;

/**
 * Created by margaritm on 6/12/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myTest.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "USER_TABLE";
    public static final String UID = "_id";
    public static final String USERNAME = "Name";
    public static final String PASSWORD = "Password";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                       " (" + UID + " INTEGER PRIMARY KEY,"
                            + USERNAME + " TEXT NOT NULL,"
                            + PASSWORD + " TEXT NOT NULL);";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + " IF EXISTS";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            Log.d("sql", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        } catch (SQLException e) {
            Log.d("sql", e.getMessage());
        }
    }
}
