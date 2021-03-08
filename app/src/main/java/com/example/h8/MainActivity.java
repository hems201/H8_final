package com.example.h8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button addMon;
    Button returnMon;
    Button buy;
    SeekBar seekBar;
    Spinner spinner;
    int index;
    Button receipt;
    Object btl;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        addMon = (Button) findViewById(R.id.addMon);
        returnMon = (Button) findViewById(R.id.returnMon);
        buy = (Button) findViewById(R.id.buy);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinner);
        receipt = (Button) findViewById(R.id.receipt);

        BottleDispenser BD = BottleDispenser.getInstance();

        addMon.setOnClickListener(v -> {
            int  barValue= seekBar.getProgress();
            BD.addMoney(barValue);
            resetSeekBar(v);
            textView.setText("Klink! Added more money!");
        });

        returnMon.setOnClickListener(v -> BD.returnMoney(textView));

        buy.setOnClickListener(v -> {
            String s = BD.buyBottle(index);
            BD.deleteBottle(index);
            textView.setText(s);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekBarValue= seekBar.getProgress();
                String prog = "Press Add Money to add amount of: " + seekBarValue + " €";
                textView.setText(prog);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //spinnerin alustus
        List<Bottle> bottle_array = BD.getBottle_array();
        ArrayAdapter<Bottle> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bottle_array);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btl = spinner.getSelectedItem();
                index = spinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //receipt button
        receipt.setOnClickListener(v -> {
            Bottle btl = BD.getLast_bottle();
            printReceipt(btl);
        });

    }
    public void resetSeekBar(View v) {
        seekBar.setProgress(0);
    }

    public void printReceipt(Bottle btl) {

        String name = btl.getName();
        String size = String.valueOf(btl.getSize());
        String price = String.valueOf(btl.getPrice());

        try {
            String filename = "Receipt.txt";
            OutputStreamWriter osw = new OutputStreamWriter(getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE));
            String s = "You Purchased a " + name + ".\nSize " + size + " l\nPrice: " + price+ " €";
            System.out.print(s);
            osw.write("Receipt\n");
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}