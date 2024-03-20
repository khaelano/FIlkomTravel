package com.car;

public class MediumCar extends Car {
    MediumCar(){
        super();
        setCapacity(8);
        this.rentFee = 80_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true))
            capacity -= 1;
    }
}