package com.car;

public class SmallCar extends Car {
    public final int FEE = 120_000;
    public final int CAPACITY = 16;

    public SmallCar(String licensePlate) {
        super(licensePlate);
    }
}