/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import java.time.LocalDate;

import components.car.Vehicle;
import components.payment.Order;

public class Guest extends User {
    private Order lastOrder;

    public Guest(String userID, long initialBalance) {
        super(userID, "NON_MEMBER", null, initialBalance);
    }

    @Override
    public boolean makeOrder() {
        if (this.activeOrder == null) {
            this.activeOrder = new Order(this);
            return true;
        }

        return false;
    }

    @Override
    public int addToCart(Vehicle rentedVehicle, LocalDate startDate, int duration) {
        // Check if the vehicle is already in the cart
        if (activeOrder.contains(rentedVehicle.getVehicleID())) {
            activeOrder.incrVehicle(rentedVehicle.getVehicleID(), duration);
            return activeOrder.getDuration(rentedVehicle.getVehicleID());
        }

        activeOrder.addVehicle(rentedVehicle, startDate, duration);
        return duration;
    }

    @Override
    public int removeFromCart(Vehicle rentedVehicle, int duration) {
        // Check if the vehicle is already in the cart
        if (activeOrder.contains(rentedVehicle.getVehicleID())) {
            activeOrder.decrVehicle(rentedVehicle.getVehicleID(), duration);

            if (activeOrder.getDuration(rentedVehicle.getVehicleID()) <= 0) {
                activeOrder.removeVehicle(rentedVehicle);
                return 0; // Will return 0 if the vehicle is removed;
            } else {
                return activeOrder.getDuration(rentedVehicle.getVehicleID()); // Will return the new vehicle duration
            }
        }

        else return -1; // Will return -1 if the vehicle is not found
    }

    @Override
    public boolean confirmPayment() {
        // TODO Auto-generated method stub
        boolean result = activeOrder.checkOut();
        if (result) {
            this.lastOrder = this.activeOrder;
            this.activeOrder = null;
        }

        return result;
    }

    @Override
    public void printBill() {
        if (this.activeOrder != null) {
            this.activeOrder.printDetails();
        } else if (this.lastOrder != null) {
            this.lastOrder.printDetails();
        }
    }
}