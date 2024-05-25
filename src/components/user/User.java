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
    private static int counter;
    private String firstName;
    private String lastName;
    private int idNum;
    public int phoneNum;
    public String address;
    
    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    protected HashMap<Integer, Order> orders;

    boolean isOrdering;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNum = counter;
        this.orders = new HashMap<>();
        counter++;
    }

    public abstract Order makeOrder(Car car, int quantity);

    public abstract boolean confirmPayment(int orderID);

    public int getIdNum() {
        return this.idNum;
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
}