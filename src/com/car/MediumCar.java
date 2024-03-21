package com.car;

public class MediumCar extends Car {
    public MediumCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 80_000;
        this.capacity = 8;
    }
}