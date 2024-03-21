package com.car;

public class Car {
    public String brand;
    public String model;
    public String color;
    public boolean includeDriver;
    protected String licensePlate;

    public final int FEE = 0;
    public final int CAPACITY = 0;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }
}