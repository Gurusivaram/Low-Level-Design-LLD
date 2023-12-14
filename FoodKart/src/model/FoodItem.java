package model;

import interfaces.IItem;

public class FoodItem implements IItem {
    private String name;
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public FoodItem(String foodItem, int price) {
        this.name = foodItem;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
