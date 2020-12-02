package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartLumpSumActivity extends AppCompatActivity {
EditText eTcashValue;
public Float cashValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lump_sum);
        eTcashValue =findViewById(R.id.etLumpSumInput);

    }

    public void DoBack(View view) {
        finish();
    }

    public void doSave(View view) {
        //float floatValueOf;
        cashValue = Float.valueOf(eTcashValue.getText().toString());
        MainActivity.setCash(cashValue);
        Intent mainAct = new Intent(view.getContext(),MainActivity.class);
        startActivity(mainAct);
    }
}