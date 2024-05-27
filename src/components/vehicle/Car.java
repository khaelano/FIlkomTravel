/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.vehicle;

public class Car extends Vehicle {
    private CarSize capacity;

    public Car(
            String vehicleID,
            String vehicleName,
            String licenseNumber,
            long rentFee,
            CarSize carSize
        ) {
        super(vehicleID, vehicleName, licenseNumber, rentFee);
        this.capacity = carSize;
    }
}