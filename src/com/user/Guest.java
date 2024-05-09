package com.user;

import com.payment.Order;
import com.car.Car;

public class Guest extends User {
    public Guest(String firstName, String lastName, int idNum) {
        super(firstName, lastName);
    }

    public boolean makeOrder(Car rentedCar, int quantity) {
        Order order = new Order(rentedCar, quantity, this);
        this.orders.put(order.getOrderID(), order);

        return true;
    }

    public boolean confirmPayment(int orderID) {
        Order order = this.orders.get(orderID);
        
        if (order == null) return false;
        order.pay();
        orders.remove(orderID);

        return true;
    }
}