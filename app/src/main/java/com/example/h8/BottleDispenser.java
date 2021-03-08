package com.example.h8;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
public class BottleDispenser {
    private int bottles;
    // The array for the Bottle-objects
    private final ArrayList<Bottle> bottle_array;
    private float money;
    private Bottle last_bottle;

    //singleton:
    private static final BottleDispenser BD = new BottleDispenser();

    private BottleDispenser() {
        bottles = 8;
        money = 0;

        bottle_array = new ArrayList<>();

        bottle_array.add(new Bottle());
        bottle_array.add(new Bottle("Pepsi Max", 1.5, 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, 3.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", 2.5, 4.5));
        bottle_array.add(new Bottle("Coca-Cola", 0.5, 2.0));
        bottle_array.add(new Bottle("Coca-Cola", 1.5, 2.5));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, 1.95));
        bottle_array.add(new Bottle("Fanta Zero", 1, 3.5));
    }

    //singleton:
    public static BottleDispenser getInstance() {
        return BD;
    }

    public void addMoney(int barValue) {
        money = money + barValue;
    }

    public String buyBottle(int c) {
        String s ="";
        if (bottles == 0) {
            s = "None left!";
        }
        else {
            double price = bottle_array.get(c).getPrice();
            String name = bottle_array.get(c).getName();
            if (money < price) {
                s = "Add money first";
            }
            else {
                bottles -= 1;
                money = (float) (money - price);
                s = "KACHUNK! " + name + " came out of the dispenser!";
            }
        }
        last_bottle = bottle_array.get(c);
        return s;
    }

    public void returnMoney(TextView textView) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(dfs);
        String m = df.format(money);
        String text = "Klink klink. Money came out! You got " + m + "€ back\n";
        textView.setText(text);
        money = 0;
    }

    public void deleteBottle(int choice) {
        bottle_array.remove(choice);
    }

    public ArrayList<Bottle> getBottle_array() {
        return bottle_array;
    }

    public Bottle getLast_bottle() {
        return last_bottle;
    }
}
