package com.example.h8;


public class Bottle {
    private String name;
    private double size;    //Probably should've been type String
    private double price;

    public Bottle(){
        name = "Pepsi Max";
        size = 0.5;
        price = 1.80;
    }

    public Bottle(String Name, double Size, double Price){
        name = Name;
        size = Size;
        price = Price;
    }

    public String getName(){
        return name;
    }

    public double getSize(){
        return size;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        String s = name + " " + String.valueOf(size) + "l "+ String.valueOf(price) + "€";
        return s;
    }
}
