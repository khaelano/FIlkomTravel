package com.car;


public class LargeCar extends Car {
    LargeCar(){
        super();
        setCapacity(16);
        this.rentFee = 120_000;
    }

    void carCapacity() {
        if (isIncludeDriver(true)) 
            capacity -= 1;
    }
}