package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class SQLTestingActivity extends AppCompatActivity {
    public TextView tvCat;
    private List<Category> catList;
    public Category starter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_l_testing);
        TextView Text = (TextView)findViewById(R.id.tvCat);

        //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        catList = dbHelper.getAllCategories();
        //starter = dbHelper.getOneCategory(1);
        Text.setText(dbHelper.getOneCategory(1).toString());

        changeText();
        //catList.add(starter);
        Log.i("SQL","THIS IS THE 1ST CATEGORY!!! : " + catList.get(0).toString());

        dbHelper.dbUpdateCash(100);

       // catList = dbHelper.getAllCategories();
       // dbHelper.testFILL();
       // dbHelper.fillStarterCats();
       // starter = new Category("Groceries", 100.0f);
        //dbHelper.addCategory(starter);

    }

    private void changeText(){

    }
}