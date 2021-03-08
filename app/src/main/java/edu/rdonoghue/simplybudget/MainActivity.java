package edu.rdonoghue.simplybudget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Button btnAvailableCash;
    public static float availableCash;
    public static TextView tvCatCash1, tvCatCash2, tvCatName1, tvCatName2, tvCatName3, tvCatCash3;
    public Button catButton;
    public static Category starter1, starter2, starter3;
    private List<Category> catList;
    private static MySQLiteHelper dbHelper;
    public static ProgressBar ProTrackerCat1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MySQLiteHelper(this);
        dbHelper.fillStarterCats();
        dbHelper.setInitCash();
        catList = dbHelper.getAllCategories();

        //progress bar
        ProTrackerCat1 = findViewById(R.id.proBarCat);
        ProTrackerCat1.setProgress(0);
        ProTrackerCat1.setMax(100);


        btnAvailableCash = findViewById(R.id.btnCashDisplay);
        btnAvailableCash.setText("Cash: €" + String.valueOf(dbHelper.getCash()));

        // Starter categories linking to visual elements
        tvCatName1 = findViewById(R.id.tvCatName);
        tvCatCash1 = findViewById(R.id.tvCatCash);

        tvCatName2 = findViewById(R.id.tvCatName2);
        tvCatCash2 = findViewById(R.id.tvCatCash2);

        tvCatName3 = findViewById(R.id.tvCatName3);
        tvCatCash3 = findViewById(R.id.tvCatCash3);

        // directly set the starter category visual data to the content of database with hardcoded IDs
        tvCatName1.setText(dbHelper.getOneCategory(1).getName());
        tvCatCash1.setText(String.valueOf(dbHelper.getOneCategory(1).getBalance()));

        tvCatName2.setText(dbHelper.getOneCategory(2).getName());
        tvCatCash2.setText(String.valueOf(dbHelper.getOneCategory(2).getBalance()));

        tvCatName3.setText(dbHelper.getOneCategory(3).getName());
        tvCatCash3.setText(String.valueOf(dbHelper.getOneCategory(3).getBalance()));

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
        dbHelper.dbUpdateCash(amtIn);
        btnAvailableCash.setText("Cash: €" + String.valueOf(dbHelper.getCash()));
    }

    public static void updateCash(float amtIn, boolean plusOrMinus){
        availableCash = dbHelper.getCash();
        if (plusOrMinus == true){
            availableCash += amtIn;
        }
        else {
            availableCash -= amtIn;
        }
        dbHelper.dbUpdateCash(availableCash);
        updateVisuals();
        if (availableCash < 0) {
            // Toast.makeText(this, "WARNING: Over-budgeted - Cash is negative", Toast.LENGTH_SHORT).show();
        }
    }

    public void doSpendInvestCat(View view) {
        Intent investSpendCat = new Intent(view.getContext(), StartLumpSumActivity.class);
        investSpendCat.putExtra("walletType", 1);
        investSpendCat.putExtra("catID", 1);
        startActivity(investSpendCat);
    }

    public void doSpendInvestCat2(View view) {
        Intent investSpendCat = new Intent(view.getContext(), StartLumpSumActivity.class);
        investSpendCat.putExtra("walletType", 1);
        investSpendCat.putExtra("catID", 2);
        startActivity(investSpendCat);
    }

    public void doSpendInvestCat3(View view) {
        Intent investSpendCat = new Intent(view.getContext(), StartLumpSumActivity.class);
        investSpendCat.putExtra("walletType", 1);
        investSpendCat.putExtra("catID", 3);
        startActivity(investSpendCat);
    }

    public void DoLumSumInput(View view) {
        Intent LumpSum = new Intent(view.getContext(),StartLumpSumActivity.class);
        LumpSum.putExtra("walletType", 0);
        startActivity(LumpSum);
    }

    public static void updateVisuals(){
        btnAvailableCash.setText("Cash: €" +String.valueOf(dbHelper.getCash()));
        tvCatCash1.setText(String.valueOf(dbHelper.getOneCategory(1).getBalance()));
        tvCatCash2.setText(String.valueOf(dbHelper.getOneCategory(2).getBalance()));
        tvCatCash3.setText(String.valueOf(dbHelper.getOneCategory(3).getBalance()));
    }

    /*public void DoTutorial(View view) {
        Intent tutorial= new Intent(view.getContext(),activityTutorialVideo.class);
        startActivity(tutorial);
    }*/

    //for the creation of actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_icon_menu,menu);
        return true;
    }

    //for when the help button is pressed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.HelpIcon)
        {
            Intent tutorial= new Intent(this,activityTutorialVideo.class);
            startActivity(tutorial);
        }
        if(item.getItemId() == R.id.SQLTesting)
        {
            Intent sql= new Intent(this,SQLTestingActivity.class);
            startActivity(sql);
        }
        return true;
    }

}