package edu.rdonoghue.simplybudget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ExpandedCategoryActivity extends AppCompatActivity {

    public int catID;
    public EditText nameField, moneyInput;
    private static MySQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_category);
        dbHelper = new MySQLiteHelper(this);

        //for back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        catID = getIntent().getIntExtra("catID", 0);
        nameField = findViewById(R.id.etCatName);
        nameField.setText(dbHelper.getOneCategory(catID).name);
        moneyInput = findViewById(R.id.etCatCash);
        //nameField = findViewById(R.id.)


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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.expandedcat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.AddIcon)
        {
            Intent Act= new Intent(this,StartLumpSumActivity.class);
            Act.putExtra("catID", catID);
            startActivity(Act);
        }
        else
        {
            onBackPressed();
            return  true;
        }

        return true;
    }

    public void expandSave(View view) {
        if (nameField != null){
            dbHelper.updateOneCategoryName(catID, nameField.getText().toString());
        }
        if (moneyInput != null){
            float tempMoney = Float.parseFloat(moneyInput.getText().toString());
            dbHelper.updateOneCategoryBalance(catID, tempMoney);
        }

        MainActivity.updateVisuals();
        finish();
    }
}