package com.user;

import com.car.Car;
import com.payment.Order;
import java.util.HashMap;

public abstract class User {
    private static int counter;
    private String firstName;
    private String lastName;
    private int idNum;
    public int phoneNum;
    public String address;
    protected HashMap<Integer, Order> orders;

    boolean isOrdering;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = counter;
        this.orders = new HashMap<>();
        counter++;
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