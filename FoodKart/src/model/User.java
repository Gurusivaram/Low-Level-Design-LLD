package model;

import interfaces.ILocation;

public class User {
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String location;

    public User(int id, String name, String gender, String phone, String pincode) {
        this.id = String.valueOf(id);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.location = pincode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
