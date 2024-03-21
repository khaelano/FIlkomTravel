package com.car;

public class LargeCar extends Car {
    public LargeCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 40_000;
        this.capacity = 5;
    }
}