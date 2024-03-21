package com.user;

import com.Order;
import com.car.Car;

public class User {
    public String name;
    public String phoneNum;
    public String address;
    protected String idNum;
    protected double discount;

    public User(String name, String idNum) {
        this.name = name;
        this.idNum = idNum;
        this.discount = 0;
    }

    public Order order(Car car) {
        Order order = new Order(this, car);

        return order;
    }

    public String getIdNum() {
        return this.idNum;
    }

    public double getDiscount() {
        return this.discount;
    }
}