/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.car;

public class LargeCar extends Car {
    public LargeCar(String licensePlate) {
        super(licensePlate);
        this.rentalFee = 40_000;
        this.capacity = 5;
    }
}