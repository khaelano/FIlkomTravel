/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.car;

public abstract class Vehicle {
    private String vehicleID;
    private String vehicleName;
    private String licenseNumber;
    private long rentFee;

    public Vehicle(String vehicleID, String vehicleName, String licenseNumber, long rentFee) {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.licenseNumber = licenseNumber;
        this.rentFee = rentFee;
    }

    public String getVehicleID() {
        return vehicleID;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public long getRentFee() {
        return rentFee;
    }
}
