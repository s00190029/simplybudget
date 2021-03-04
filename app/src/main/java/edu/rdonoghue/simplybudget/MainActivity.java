package edu.rdonoghue.simplybudget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MySQLiteHelper(this);
        dbHelper.fillStarterCats();
        dbHelper.setInitCash();
        catList = dbHelper.getAllCategories();


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
        tvCatCash2.setText(String.valueOf(dbHelper.getOneCategory(1).getBalance()));

        tvCatName3.setText(dbHelper.getOneCategory(3).getName());
        tvCatCash3.setText(String.valueOf(dbHelper.getOneCategory(1).getBalance()));

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

        // db test
        /*
        DatabaseHandler db = new DatabaseHandler(this);
        Log.i("Insert: ", "Inserting ..");
        db.addCategory(starter1);

         */
        /*
        // Reading all cats
        Log.i("Reading: ", "Reading all cats..");
        List<Category> cats = db.getAllCats();
        for (Category ct : cats) {
            String log =
                    "Id: " + ct.getId() +
                            " , Name: " + ct.getName() +
                            " , Balance: " + ct.getBalance();

            // Writing cats to log
            Log.i("Cat: ", log);
        }
        */
    //        Category tcat = db.getCat(2);
    //      Log.i("Reading:", "Cat name is " + tcat.getName());

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

        btnAvailableCash.setText("Cash: €" +String.valueOf(dbHelper.getCash()));
        if (availableCash < 0) {
            // Toast.makeText(this, "WARNING: Over-budgeted - Cash is negative", Toast.LENGTH_SHORT).show();
        }

    }

    // wallet type 0
    public void doSpendInvestCat(View view) {
        Intent investSpendCat = new Intent(view.getContext(), StartLumpSumActivity.class);
        //getIntent().putExtra("catID", starter1.id);
        investSpendCat.putExtra("walletType", 1);
        investSpendCat.putExtra("catID", 1);
        startActivity(investSpendCat);
    }


    public void DoLumSumInput(View view) {
        Intent LumpSum = new Intent(view.getContext(),StartLumpSumActivity.class);
        LumpSum.putExtra("walletType", 0);
        startActivity(LumpSum);
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