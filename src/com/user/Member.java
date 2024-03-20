package com.user;

import java.util.ArrayList;

import com.Order;
import com.car.Car;

public class Member extends User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Order> orderHistory;

    public Member(String name, String identityNum) {
        super(name, identityNum);
        this.identityNum = "111" + Integer.toString(userCounter);
        this.discount = 0.1;
        this.loggedIn = true;

        this.orderHistory = new ArrayList<>();
    }

    public void setCredentials(String username, String password) {
        if (!loggedIn)
            return;

        this.username = username;
        this.password = password;
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
        Order order = new Order(this, car);

        orderHistory.add(order);
        return order;
    }

    public void printHistory() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- Order History -------------------------------------");
        System.out.printf("| %-16s | %-16s | %-16s | %-8s | %-13s |\n", "Invoice Date", "Start Date", "End Date", "Duration", "Net. Charges");

        for (Order order : orderHistory) {
            System.out.printf("| %-16s | %-16s | %-16s | %-5s hr | Rp. %-9s |\n", 
                order.getInvoiceDate(),
                order.getRentStartDate(), 
                order.getRentEndDate(), 
                order.calculateDuration(),
                order.calculateTotalCharges()
            );
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }
}