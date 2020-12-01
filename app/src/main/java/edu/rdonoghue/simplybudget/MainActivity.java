package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static float availableCash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void setCash(float amtIn){
        availableCash = amtIn;
    }

    public static void updateCash(float amtIn, boolean plusOrMinus){
        if (plusOrMinus == true){
            availableCash += amtIn;
        }
        else {
            availableCash -= amtIn;
        }
    }

    public void doExpandedCat(View view) {
        Intent ExpandedCat = new Intent(view.getContext(),ExpandedCategoryActivity.class);
        startActivity(ExpandedCat);

    }

    public void DoLumSumInput(View view) {
        Intent LumpSum = new Intent(view.getContext(),StartLumpSumActivity.class);
        startActivity(LumpSum);
    }
}