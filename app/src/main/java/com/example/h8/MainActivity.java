package com.example.h8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button addMon;
    Button returnMon;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        addMon = (Button) findViewById(R.id.addMon);
        returnMon = (Button) findViewById(R.id.returnMon);
        buy = (Button) findViewById(R.id.buy);

        BottleDispenser BD = BottleDispenser.getInstance();

        addMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.addMoney(textView);
            }
        });

        returnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.returnMoney(textView);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD.listBottles(textView);
                BD.buyBottle(1, textView);
                BD.listBottles(textView);
            }
        });


    }
}