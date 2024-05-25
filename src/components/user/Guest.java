/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import components.car.Car;
import components.payment.Order;

public class Guest extends User {
    public Guest(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Order makeOrder(Car rentedCar, int quantity) {
        Order order = new Order(rentedCar, quantity, this);
        this.orders.put(order.getOrderID(), order);

        return order;
    }

    public boolean confirmPayment(int orderID) {
        Order order = this.orders.get(orderID);
        
        if (order == null) return false;
        order.pay();
        orders.remove(orderID);

        return true;
    }
}