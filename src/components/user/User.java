/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import java.util.HashMap;

import components.car.Car;
import components.payment.Order;

public abstract class User {
    private String userID;
    private String firstName, lastName;
    protected boolean isOrdering;
    protected long balance;
    protected HashMap<Integer, Order> orders;
    
    public User(String userID, String firstName, String lastName, long initialBalance) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = initialBalance;
        this.orders = new HashMap<>();
    }

    public abstract Order makeOrder(Car car, int quantity);

    public abstract boolean confirmPayment(int orderID);

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

    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

}