/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import java.time.LocalDate;
import java.util.ArrayList;

import components.car.Vehicle;
import components.payment.Order;

public abstract class User {
    private String userID;
    private String firstName, lastName;
    protected boolean isOrdering;
    protected long balance;
    protected ArrayList<Order> orderHistory;
    protected Order activeOrder;
    
    public User(String userID, String firstName, String lastName, long initialBalance) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = initialBalance;
        this.orderHistory = new ArrayList<Order>();
    }

    public abstract boolean makeOrder();

    public abstract int addToCart(Vehicle rentedVehicle, LocalDate startDate, int duration);

    public abstract int removeFromCart(Vehicle rentedVehicle, int duration);

    public abstract boolean confirmPayment();

    public abstract void printBill();

    public String getUserID() {
        return this.userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return lastName != null ? firstName + " " + lastName : firstName;
    }

    public Order[] getOrderHistory() {
        return (Order[]) orderHistory.toArray();
    }

    public void incrBalance(long nominal) {
        this.balance += nominal;
    }

    public void decrBalance(long nominal) {
        this.balance -= nominal;
    }

    public long getBalance() {
        return this.balance;
    }

    public Order getActiveOrder() {
        return this.activeOrder;
    }
}