/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.car;

public class SmallCar extends Car {
    public SmallCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 120_000;
        this.capacity = 16;
    }
}