package com.user;

import com.Order;
import com.car.Car;

public class Guest extends User {
    public Guest(String firstName, String lastName, int idNum) {
        super(firstName, lastName, idNum);
    }

    public boolean makeOrder(Car rentedCar) {
        Order order = new Order(rentedCar);
        this.orders.put(order.getId, order);

        return true;
    }

    public boolean confirmPayment(int orderID) {
        Order order = this.orders.get(orderID);
        
        if (order == null) return false;


        return true;
    }
}