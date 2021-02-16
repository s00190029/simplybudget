/* package edu.rdonoghue.simplybudget;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


 * Class has create / drop table and CRUD ops for table

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "simplybudget";
    private static final String TABLE_CAT = "categories";
    private static final String KEY_CAT_ID = "cat_id";
    private static final String KEY_CAT_NAME = "cat_name";
    //private static final String KEY_GAME_DATE = "game_date";
    private static final String KEY_BALANCE = "balance";


     * Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CAT + "(" +
                KEY_CAT_ID + " INTEGER PRIMARY KEY," +
                KEY_CAT_NAME + " TEXT NOT NULL," +
                KEY_BALANCE + " FLOAT NOT NULL" +
                ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);

        // Create tables again
        onCreate(db);
    }

    /*
     * CRUD Helper methods
    public void emptycategories() {
        // Drop older table if existed
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);

        // Create tables again
        onCreate(db);
    }

    // code to add the new cat
    void addCategory(Category cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NAME, cat.getName());
        values.put(KEY_BALANCE, cat.getBalance());

        // Inserting Row
        db.insert(TABLE_CAT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single category
    Category getCat(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CAT, new String[]{
                        KEY_CAT_ID,
                        KEY_CAT_NAME,
                        KEY_BALANCE},
                KEY_CAT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Category cat = new Category(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Float.parseFloat(cursor.getString(2)));
        // return cat
        return cat;
    }

    // code to get all categories in a list view
    public List<Category> getAllCats() {
        List<Category> catList = new ArrayList<Category>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category cat = new Category();
                cat.setId(Integer.parseInt(cursor.getString(0)));
                cat.setName(cursor.getString(2));
                cat.setBalance(cursor.getFloat(3));
                // Adding hi score to list
                catList.add(cat);
            } while (cursor.moveToNext());
        }

        // return hiScore list
        return catList;
    }

    // code to update the single cat balance
    public int updateHiScore(Category cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NAME, cat.getName());
        values.put(KEY_BALANCE, cat.getBalance());

        // updating row
        return db.update(TABLE_CAT, values, KEY_CAT_ID + " = ?",
                new String[]{String.valueOf(cat.getId())});
    }

    // Deleting single category
    public void deleteCat(Category cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAT, KEY_CAT_ID + " = ?",
                new String[]{String.valueOf(cat.getId())});
        db.close();
    }

    /*
    // Getting top 5 scores
    public List<HiScore> getTopFiveScores() {
        List<HiScore> hiScoreList = new ArrayList<HiScore>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAT +
                " ORDER BY SCORE DESC " +
                " LIMIT 5";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HiScore hiScore = new HiScore();
                hiScore.setScore_id(Integer.parseInt(cursor.getString(0)));
                hiScore.setGame_date(cursor.getString(1));
                hiScore.setPlayer_name(cursor.getString(2));
                hiScore.setScore(cursor.getInt(3));
                // Adding hi score to list
                hiScoreList.add(hiScore);
            } while (cursor.moveToNext());
        }

        // return hi score list
        return hiScoreList;
    }

}
    */