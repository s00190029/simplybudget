package edu.rdonoghue.simplybudget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CATEGORIES = "categories"; // table name
    public static final String COLUMN_ID = "_id"; // primary key
    public static final String COLUMN_NAME = "name"; // text column
    public static final String COLUMN_BALANCE = "balance"; // text column

    private static final String DATABASE_NAME = "categories.db"; // name of db
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CATEGORIES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null," + COLUMN_BALANCE + " float);";

    /*
    create table categories (
        id integer primary key autoincrement,
        name text not null,
        balance float
    );

    OR

    create table categories (id integer primary key autoincrement, name text not null, balance float);  */

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }
}