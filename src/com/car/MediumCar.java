package com.car;

public class MediumCar extends Car {
    public final int FEE = 80_000;
    public final int CAPACITY = 8;

    public MediumCar(String licensePlate) {
        super(licensePlate);
    }
}