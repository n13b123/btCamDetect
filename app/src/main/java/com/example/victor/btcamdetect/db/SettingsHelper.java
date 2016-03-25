package com.example.victor.btcamdetect.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victor.shkanov on 23.03.2016.
 */
public class SettingsHelper extends SQLiteOpenHelper{
    public SettingsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String DATABASE_NAME = "main.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "settings";
    public static final String ID_COLUMN = "_ID";
    public static final String NAME_COLUMN = "name";
    public static final String VALUE_COLUMN = "value";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE
                + " (" + ID_COLUMN + " integer primary key autoincrement,"
                + NAME_COLUMN + " text,"
                + VALUE_COLUMN + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
