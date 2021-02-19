package com.example.h8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button addMon;
    Button returnMon;
    Button buy;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        addMon = (Button) findViewById(R.id.addMon);
        returnMon = (Button) findViewById(R.id.returnMon);
        buy = (Button) findViewById(R.id.buy);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        BottleDispenser BD = BottleDispenser.getInstance();

        addMon.setOnClickListener(v -> {
            int  barValue= seekBar.getProgress();
            BD.addMoney(barValue);
            resetSeekBar(v);
            textView.setText("Klink! Added more money!");
        });

        returnMon.setOnClickListener(v -> BD.returnMoney(textView));

        buy.setOnClickListener(v -> {
            BD.listBottles(textView);
            BD.buyBottle(1, textView);
            BD.listBottles(textView);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekBarValue= seekBar.getProgress();
                String prog = "Press Add Money to add amount of: " + seekBarValue;
                textView.setText(prog);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void resetSeekBar(View v) {
        seekBar.setProgress(0);
    }
}