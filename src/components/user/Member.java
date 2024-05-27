/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import components.payment.Order;
import components.vehicle.Vehicle;

public class Member extends User {
    private ArrayList<Order> orderHistory;
    private LocalDate joinDate;

    public Member(
        String userID,
        String firstName, 
        String lastName, 
        LocalDate joinDate, 
        long initialBalance
    ) {
        super(userID, firstName, lastName, initialBalance);
        this.joinDate = joinDate;

        this.orderHistory = new ArrayList<>();
    }

    public void printHistory() {
        // TODO: Implements print history
        System.out.println("Kode Pemesan: " + getUserID());
        System.out.println("Nama: " + getFullName());
        System.out.println("Saldo: " + getBalance());
        System.out.printf(
            "%4s| %11s | %5s | %5s | %8s | %-9s\n", 
            "No", "No. Pesanan", "Motor", "Mobil", "Subtotal", "PROMO"
        );
        System.out.println("====================================================");
        int counter = 1;
        for (Order order : orderHistory) {
            int carNumber = order.getCarNumber();
            int bikeNumber = order.getBikeNumber();

            System.out.printf(
                "%4d| %11d | %5d | %5d | %8d | %-9s\n", 
                counter, 
                order.getOrderID(), 
                bikeNumber, 
                carNumber, 
                order.calculateTotal() - order.getDiscountVal(), 
                order.getAppliedPromo().getPromoCode()
            );
            counter++;
        }
        System.out.println("====================================================");
    }

    public int getMembershipDuration() {
        long duration = ChronoUnit.DAYS.between(joinDate, LocalDate.now());
        return (int) duration;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
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
    public boolean confirmPayment() {
        boolean result =  this.activeOrder.checkOut();
        if (result) {
            this.orderHistory.add(activeOrder);
            this.activeOrder = null;
        }
        return result;
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
    public void printBill() {
        Order lastOrder = orderHistory.size() == 0 ? null : orderHistory.get(orderHistory.size()-1);
        if (this.activeOrder != null) {
            this.activeOrder.printDetails();
        } else if (lastOrder != null) {
            lastOrder.printDetails();
        }
    }
}