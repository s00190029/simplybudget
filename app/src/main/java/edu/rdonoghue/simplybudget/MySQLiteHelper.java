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
    private static final int DATABASE_VERSION = 24;

    private static final String TABLE_CASH = "cash";
    private static final String COLUMN_MONEY ="money";

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


    private static final String DATABASE_CREATE_CASH =
            "CREATE TABLE "
                    + TABLE_CASH
                    + "("
                    + COLUMN_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_MONEY
                    + " float not null"
                    + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        this.db = db;
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE_CASH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CASH);
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

    public Category getOneCategory(int idIn) {
        db = getReadableDatabase();
        Category category = new Category();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CATEGORIES + " WHERE _id = " + String.valueOf(idIn), null);
        if (c.moveToFirst()) {
            do {
                category.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                category.setBalance(c.getFloat(c.getColumnIndex(COLUMN_BALANCE)));
            } while (c.moveToNext());
        }
        return category;
    }

    public void updateOneCategoryBalance(int idIn, float moneyIn) {
        // float must be viewable to SQL as a string first
        String idString = String.valueOf(idIn);
        String moneyString = String.valueOf(moneyIn);
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BALANCE, moneyIn);
        String whereClause = "_id="+idString;
        db.update(TABLE_CATEGORIES, cv, whereClause, null);
        }

    public void setInitCash(){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MONEY, 0);
        db.insert(TABLE_CASH, null, cv);
    }

    public void dbUpdateCash(float cashIn){
        // float must be viewable to SQL as a string first
        String cashString = String.valueOf(cashIn);
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MONEY, cashString);
        db.update(TABLE_CASH, cv, "_id=1", null);
    }

    public float getCash(){
        db = getReadableDatabase();
        float cashReturn = 0;
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CASH + " WHERE _id = " + "1", null);
        if (c.moveToFirst()) {
            do {
                cashReturn = c.getFloat(c.getColumnIndex(COLUMN_MONEY));
            } while (c.moveToNext());
        }
        return cashReturn;
    }

}