package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExpandedCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_category);
    }

    public void DoBackCat(View view) {
        finish();
    }

    public void DoEnterMoneyAct(View view) {
        Intent EnterMoneyAct = new Intent(view.getContext(),EnterMoneyActivity.class);
        startActivity(EnterMoneyAct);
    }
}