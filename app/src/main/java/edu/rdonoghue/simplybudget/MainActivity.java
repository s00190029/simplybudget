package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity 
    public static Button btnAvailableCash;
    public static float availableCash = 0;
    TextView tvCatCash1, tvCatCash2, tvCatName1, tvCatName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAvailableCash = findViewById(R.id.btnCashDisplay);
        btnAvailableCash.setText("Cash: €" + String.valueOf(availableCash));

        // Starter categories
        tvCatName1 = findViewById(R.id.tvCatName);
        tvCatCash1 = findViewById(R.id.tvCatCash);
        tvCatName2 = findViewById(R.id.tvCatName2);
        tvCatCash2 = findViewById(R.id.tvCatCash2);
        category starter1, starter2;
        starter1 = new category("Groceries", 100.0f);
        starter2 = new category("Bills");
        tvCatName1.setText(starter1.name);
        tvCatCash1.setText(String.valueOf(starter1.balance));
        tvCatName2.setText(starter2.name);
        tvCatCash2.setText(String.valueOf(starter2.balance));
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