package com.user;

import com.Order;
import com.car.Car;

public class User {
    protected static int userCounter;
    protected String UID;
    protected String name;
    protected String identityNum;
    protected double discount;
    String phoneNum;
    String address;

    public User(String name, String identityNum) {
        this.name = name;
        this.identityNum = identityNum;
        this.UID = "101" + Integer.toString(userCounter);
        this.discount = 0;
        userCounter++;
    }

    public Order order(Car car) {
        Order order = new Order(this, car);

        return order;
    }

    public String getUID() {
        return this.UID;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentityNum() {
        return this.identityNum;
    }
}