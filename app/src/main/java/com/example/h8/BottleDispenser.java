package com.example.h8;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
//muutetaan array arraylistiks että voidaan poistaa pulloja helpommin
public class BottleDispenser {
    private int bottles;
    // The array for the Bottle-objects
    private ArrayList<Bottle> bottle_array;
    private float money;
    private String name,m;
    private double price;

    //singleton:
    private static BottleDispenser BD = new BottleDispenser();

    private BottleDispenser() {
        bottles = 5;
        money = 0;

        bottle_array = new ArrayList<Bottle>();

        bottle_array.add(new Bottle());
        bottle_array.add(new Bottle("Pepsi Max", 1.5, 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, 2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", 1.5, 2.5));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, 1.95));
    }

    //singleton:
    public static BottleDispenser getInstance() {
        return BD;
    }

    public void addMoney(int barValue) {
        money = money + barValue;
    }

    public void buyBottle(int c, TextView textView) {
        price = bottle_array.get(c -1).getPrice();
        name = bottle_array.get(c -1).getName();
        if (bottles == 0) {
            System.out.println("No bottles left");
        }
        else if (money < price) {
            System.out.println("Add money first!");
        }
        else {
            bottles -= 1;
            money = (float) (money - price);

            System.out.println("KACHUNK! " + name + " came out of the dispenser!");
        }
    }

    public void returnMoney(TextView textView) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(dfs);
        m = df.format(money);
        String text = "Klink klink. Money came out! You got " + m + "€ back\n";
        textView.setText(text);
        money = 0;
    }

    public void listBottles(TextView textView) {
        String n;
        double s,p;
        for(int i = 0; i < bottle_array.size();i++) {
            p = bottle_array.get(i).getPrice();
            n = bottle_array.get(i).getName();
            s = bottle_array.get(i).getSize();
            System.out.println(i+1 + ". Name: " + n + "\n	Size: " + s + "	Price: " + p);
        }
    }
    public void deleteBottle(int choice) {
        bottle_array.remove(choice-1);
    }
}
