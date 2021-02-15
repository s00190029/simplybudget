package edu.rdonoghue.simplybudget;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_BALANCE };

    public CategoryDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /*
    public Category createCategory(String category) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, category);
        long insertId = database.insert(MySQLiteHelper.TABLE_CATEGORIES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORIES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;
    }
    */

    /*
    public void deleteCategory(Category category) {
        long id = category.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_CATEGORIES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }
     */

    /*
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return categories;
    }
*/

    /*
    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getLong(0));
        category.setName(cursor.getString(1));
        category.setBalance(cursor.getFloat(2));
        return category;
    }
     */
} 