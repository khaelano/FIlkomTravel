package com.car;

public class Car {
    protected int rentFee;
    protected String brand;
    protected String model;
    protected String color;
    protected String licensePlateNum;
    protected int capacity;
    protected boolean includeDriver;
    protected int carUniqueCode;

    public int getRentFee() {
        return this.rentFee;
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isIncludeDriver(boolean includeDriver) {
        this.includeDriver = includeDriver;
        return this.includeDriver;
    }

    public void setCarUniqueCode(int carUniqueCode){
        this.carUniqueCode = carUniqueCode;
    }

    public int getCarUniqueCode(){
        return this.carUniqueCode;
    }
}