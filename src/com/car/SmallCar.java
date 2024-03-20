package com.car;

public class SmallCar extends Car {
    SmallCar(){
        super();
        setCapacity(5);
        this.rentFee = 40_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true))
            capacity -= 1;
    }

}