package edu.rdonoghue.simplybudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CATEGORIES = "categories"; // table name
    public static final String COLUMN_ID = "_id"; // primary key
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BALANCE = "balance";
    private static final String DATABASE_NAME = "categories.db";
    private static final int DATABASE_VERSION = 16;
    private SQLiteDatabase db;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE "
            + TABLE_CATEGORIES
            + "("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME
            + " text not null,"
            + COLUMN_BALANCE
            + " float);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        this.db = db;
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

    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());
        values.put(COLUMN_BALANCE, category.getBalance());

        // Inserting row
        db.insert(TABLE_CATEGORIES, null, values);
        //db.close(); // Closing database connection
    }

    public void fillStarterCats(){
        db = this.getWritableDatabase();
        Category c1 = new Category("Groceries", 50f);
        addStarterCats(c1);
        Category c2 = new Category("Bills", 100f);
        addStarterCats(c2);
        Category c3 = new Category("Leisure", 25.50f);
        addStarterCats(c3);
    }

    public void addStarterCats(Category category){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, category.getName());
        cv.put(COLUMN_BALANCE, category.getBalance());
        db.insert(TABLE_CATEGORIES, null, cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " +  TABLE_CATEGORIES, null);
        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                category.setBalance(c.getFloat(c.getColumnIndex(COLUMN_BALANCE)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

}