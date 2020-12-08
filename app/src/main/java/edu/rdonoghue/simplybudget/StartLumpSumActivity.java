package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartLumpSumActivity extends AppCompatActivity {
EditText eTcashValue;
public Float userInput;
public int walletType; // 0 is cash. 1 is category (Groceries for now)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lump_sum);
        eTcashValue =findViewById(R.id.etLumpSumInput);
        walletType = getIntent().getIntExtra("walletType", 0);
    }

    public void DoBack(View view) {
        finish();
    }

    public void doInvest(View view) {
        if (walletType == 0){
            userInput = Float.valueOf(eTcashValue.getText().toString());
            MainActivity.updateCash(userInput, true);
        }
        else if (walletType == 1){
            userInput = Float.valueOf(eTcashValue.getText().toString());
            MainActivity.starter1.updateBalance(userInput, true);
            MainActivity.tvCatCash1.setText(String.valueOf(MainActivity.starter1.balance));
            MainActivity.updateCash(userInput, false);
        }
        finish();
    }

    public void doSpend(View view) {
        walletType = getIntent().getIntExtra("walletType", 0);
        float userInput = Float.valueOf(eTcashValue.getText().toString());
        if (walletType == 1) {
            MainActivity.starter1.updateBalance(Float.valueOf(eTcashValue.getText().toString()), false);
            MainActivity.tvCatCash1.setText(String.valueOf(MainActivity.starter1.balance));
            //MainActivity.updateCash(userInput, false);
        }
        else if (walletType == 0) {
            MainActivity.updateCash(userInput, false);
        }
        finish();
    }
}