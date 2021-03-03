package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SQLTestingActivity extends AppCompatActivity {
    private TextView tvCat;
    private List<Category> catList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_l_testing);

        //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        catList = dbHelper.getAllCategories();


    }
}