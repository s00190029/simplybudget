package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static Button btnAvailableCash;
    public static float availableCash;
    public static TextView tvCatCash1, tvCatCash2, tvCatName1, tvCatName2;
    public Button catButton;
    public static category starter1, starter2;

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
        starter1 = new category("Food", 100.0f);
        starter2 = new category("Bills");
        tvCatName1.setText(starter1.name);
        tvCatCash1.setText(String.valueOf(starter1.balance));
        tvCatName2.setText(starter2.name);
        tvCatCash2.setText(String.valueOf(starter2.balance));

        /*  Button listeners
        *   Category long press
        */
        catButton = findViewById(R.id.btnCat);
        catButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent expandedCatView = new Intent(view.getContext(), ExpandedCategoryActivity.class);
                startActivity(expandedCatView);
                return true;
            }
        });
    }

    public static void setCash(float amtIn){
        availableCash = amtIn;
        btnAvailableCash.setText("Cash: €" + String.valueOf(amtIn));
    }

    public static void updateCash(float amtIn, boolean plusOrMinus){
        if (plusOrMinus == true){
            availableCash += amtIn;
        }
        else {
            availableCash -= amtIn;
        }
        btnAvailableCash.setText("Cash: €" +String.valueOf(availableCash));
        if (availableCash < 0) {
//            Toast.makeText(this, "WARNING: Over-budgeted - Cash is negative", Toast.LENGTH_SHORT).show();
        }
    }

    public void doSpendInvestCat(View view) {
        Intent investSpendCat1 = new Intent(view.getContext(), StartLumpSumActivity.class);
        //getIntent().putExtra("catID", starter1.id);
        investSpendCat1.putExtra("walletType", 1);
        startActivity(investSpendCat1);
    }

    public void DoLumSumInput(View view) {
        Intent LumpSum = new Intent(view.getContext(),StartLumpSumActivity.class);
        LumpSum.putExtra("walletType", 0);
        startActivity(LumpSum);
    }
}