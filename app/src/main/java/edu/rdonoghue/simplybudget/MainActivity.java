package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float availableCash;
    category cat1, cat2;
    TextView helloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cat1 = new category();
        cat2 = new category();
        helloWorld = findViewById(R.id.tvHelloWorld);
        helloWorld.setText(String.valueOf(cat1.id));
        helloWorld.setText(String.valueOf(cat2.id));

    }
}