package com.car;

public class Car {
    public String brand;
    public String model;
    public String color;
    public boolean includeDriver;
    protected String licensePlate;
    protected int rentalFee;
    protected int capacity;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public int getRentalFee() {
        return rentalFee;
    }

    public int getCapacity() {
        return capacity;
    }
}