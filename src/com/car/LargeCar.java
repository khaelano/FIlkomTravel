package com.car;

public class LargeCar extends Car {
    public final int FEE = 40_000;
    public final int CAPACITY = 5;

    public LargeCar(String licensePlate) {
        super(licensePlate);
    }
}