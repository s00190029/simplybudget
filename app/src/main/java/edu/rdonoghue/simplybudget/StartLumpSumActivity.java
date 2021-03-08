package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartLumpSumActivity extends AppCompatActivity {
EditText eTcashValue;
public Float userInput;

public int walletType; // 0 is cash. 1 is category (Groceries for now)
    public int catID;
    private static MySQLiteHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lump_sum);
        eTcashValue =findViewById(R.id.etLumpSumInput);
        walletType = getIntent().getIntExtra("walletType", 0);
        catID = getIntent().getIntExtra("catID",0);

        dbHelper = new MySQLiteHelper(this);

        //for back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void DoBack(View view) {
        finish();
    }

    public void doInvest(View view) {
        if (walletType == 0){ //cash
            userInput = Float.valueOf(eTcashValue.getText().toString());
            MainActivity.updateCash(userInput, true);
        }
        else if (walletType == 1){ //cat
            float tempCatBalance = dbHelper.getOneCategory(catID).balance;
            userInput = Float.valueOf(eTcashValue.getText().toString());
            tempCatBalance += userInput;
            dbHelper.updateOneCategoryBalance(catID, tempCatBalance);
            MainActivity.tvCatCash1.setText(String.valueOf(dbHelper.getOneCategory(catID).getBalance()));
            MainActivity.updateCash(userInput, false);
        }
        MainActivity.updateVisuals();
        finish();
    }

    public void doSpend(View view) {

        walletType = getIntent().getIntExtra("walletType", 0);
        float userInput = Float.valueOf(eTcashValue.getText().toString());
        if (walletType == 1) { //cat
            float tempCatBalance = dbHelper.getOneCategory(catID).balance;
            tempCatBalance -= userInput;
           // MainActivity.starter1.updateBalance(Float.valueOf(eTcashValue.getText().toString()), false);
            dbHelper.updateOneCategoryBalance(catID, tempCatBalance);
            MainActivity.updateVisuals();
        }
        else if (walletType == 0) { //cash
            MainActivity.updateCash(userInput, false);
        }
        MainActivity.updateVisuals();
        finish();
    }
}