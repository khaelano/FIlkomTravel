package com.user;

import com.Order;
import com.car.Car;
import java.util.HashMap;

public abstract class User {
    private String firstName;
    private String lastName;
    private int idNum;
    public int phoneNum;
    public String address;
    protected HashMap<Integer, Order> orders;

    boolean isOrdering;

    public User(String firstName, String lastName, int idNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = idNum;
        this.orders = new HashMap<>();
    }

    public abstract boolean makeOrder(Car car);

    public abstract boolean confirmPayment(int orderID);

    public int getIdNum() {
        return this.idNum;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return lastName != null ? firstName + " " + lastName : firstName;
    }
}