/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.car;

public class MediumCar extends Car {
    public MediumCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 80_000;
        this.capacity = 8;
    }
}