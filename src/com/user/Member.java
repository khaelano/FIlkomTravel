package com.user;

import java.util.ArrayList;

import com.car.Car;
import com.payment.Order;

public class Member extends User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Order> orderHistory;

    public Member(String name, String idNum, String username, String password) {
        super(name, idNum);

        this.username = username;
        this.password = password;

        this.discount = 0.1;
        this.loggedIn = true;

        this.orderHistory = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public void login(String username, String password) {
        if (!(username.equals(this.username) && password.equals(this.password))) {
            System.out.println("Login Failed!");
            throw new RuntimeException("Fatal: Incorrect Credentials!");
        }

        System.out.println("Logged In!");
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    @Override
    public Order order(Car car) {
        if (!loggedIn)
            throw new SecurityException("You must log in to do this operation");

        Order order = new Order(this, car);

        orderHistory.add(order);
        return order;
    }

    public void printHistory() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- Order History -------------------------------------");
        System.out.printf("| %-16s | %-16s | %-16s | %-8s | %-13s |\n", "Invoice Date", "Start Date", "End Date",
                "Duration", "Net. Charges");

        for (Order order : orderHistory) {
            System.out.printf("| %-16s | %-16s | %-16s | %-5s hr | Rp. %-9s |\n",
                    order.getInvoiceDate(),
                    order.getRentStartDate(),
                    order.getRentEndDate(),
                    order.calculateDuration(),
                    order.calculateTotalCharges());
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }
}