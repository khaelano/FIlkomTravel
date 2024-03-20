package com.car;

public class Car {
    public String brand;
    public String model;
    public String color;
    protected String licensePlate;
    protected boolean includeDriver;

    public final int FEE = 0;
    public final int CAPACITY = 0;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isDriverIncluded(boolean includeDriver) {
        this.includeDriver = includeDriver;
        return this.includeDriver;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }
}