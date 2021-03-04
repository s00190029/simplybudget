package edu.rdonoghue.simplybudget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ExpandedCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_category);

        //for back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //public void DoBackCat(View view) {
    //    finish();
    //}

    //public void DoEnterMoneyAct(View view) {
     //   Intent EnterMoneyAct = new Intent(view.getContext(), StartLumpSumActivity.class);
     //   startActivity(EnterMoneyAct);
    //}

    //for the creation of actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.expandedcat_menu,menu);
        return true;
    }

    //for when the help button is pressed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.AddIcon)
        {
            Intent Act= new Intent(this,StartLumpSumActivity.class);
            startActivity(Act);
        }

        return true;
    }
}