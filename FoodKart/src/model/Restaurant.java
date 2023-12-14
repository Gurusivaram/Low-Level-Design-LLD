package model;

import interfaces.IItem;
import interfaces.IFoodProvider;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Restaurant implements IFoodProvider {
    private String name;
    private Set<String> locationSet;
    private FoodItem item;
    private int quantity;

    public Restaurant(String name, Set<String> location, FoodItem foodItem, int quantity) {
        this.name = name;
        this.item = foodItem;
        this.locationSet = location;
        this.quantity = quantity;
    }

    public Set<String> getLocationSet() {
        return locationSet;
    }

    public String getLocations() {
        return String.join(", ", this.locationSet);
    }


    public void updateLocation(Set<String> locations) {
        this.locationSet = locations;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public String getFoodName() {
        return this.item.getName();
    }

    public int getPerItemPrice() {
        return this.item.getPrice();
    }
}
