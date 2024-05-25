/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.car;

public class Car {
    public String brand;
    public String model;
    public String color;
    public boolean includeDriver;
    protected String licensePlate;
    protected long rentalFee;
    protected int capacity;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public long getRentalFee() {
        return rentalFee;
    }

    public int getCapacity() {
        return capacity;
    }
}