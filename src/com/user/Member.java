package com.user;

import java.time.LocalDate;
import java.util.ArrayList;

import com.car.Car;
import com.payment.Order;

public class Member extends User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Order> orderHistory;
    private LocalDate joinDate;

    public Member(String firstName, String lastName) {
        super(firstName, lastName);
        this.loggedIn = true;

        this.orderHistory = new ArrayList<>();
    }

    public boolean register(String username, String password) {
        if (this.loggedIn != true) return false;

        this.username = username;
        this.password = password;
        return true;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean login(String username, String password) {
        if (!(username.equals(this.username) && password.equals(this.password))) return false;

        System.out.println("Logged In!");
        this.loggedIn = true;
        return true;
    }

    public boolean logout() {
        if (this.loggedIn == true) return false;

        this.loggedIn = false;
        return true;
    }

    @Override
    public boolean makeOrder(Car car) {
        
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