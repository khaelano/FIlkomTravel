package components.car;

public class SmallCar extends Car {
    public SmallCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 120_000;
        this.capacity = 16;
    }
}