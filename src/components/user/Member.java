/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.user;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import components.car.Car;
import components.payment.Order;

public class Member extends User {
    private String username;
    private String password;
    private boolean loggedIn;
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
        this.loggedIn = true;
        this.joinDate = joinDate;

        this.orderHistory = new ArrayList<>();
    }

    public boolean register(String username, String password) {
        if (this.loggedIn != true) return false;

        this.username = username;
        this.password = password;
        return true;
    }
    
    public boolean login(String username, String password) {
        if (!(username.equals(this.username) && password.equals(this.password))) return false;
        
        System.out.println("Logged In!");
        this.loggedIn = true;
        return true;
    }

    public boolean logout() {
        if (this.loggedIn == true) return false;

        this.loggedIn = false;
        return true;
    }

    public void printHistory() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("--------------------------------- Order History -------------------------------------");
        System.out.printf("| %-16s | %-16s | %-16s | %-8s | %-13s |\n", "Invoice Date", "Start Date", "End Date",
                "Duration", "Net. Charges");

        for (Order order : orderHistory) {
            System.out.printf("| %-16s | %-16s | %-16s | %-5s hr | Rp. %-9s |\n",
                    order.getInvoiceDate(),
                    order.getRentStartDate(),
                    order.getRentEndDate(),
                    order.calculateDuration(),
                    order.getSubtotal());
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }

    public String getUsername() {
        return this.username;
    }

    public int getMembershipDuration() {
        Duration dur = Duration.between(joinDate, LocalDate.now());
        long dayToSecond = 86400;
        return (int) (dur.getSeconds() / dayToSecond);
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    @Override
    public Order makeOrder(Car car, int quantity) {
        Order order = new Order(car, quantity, this);
        orders.put(order.getOrderID(), order);

        return order;
    }

    @Override
    public boolean confirmPayment(int orderID) {
        Order order = this.orders.get(orderID);
        
        if (order == null) return false;

        order.pay();
        orderHistory.add(orders.remove(orderID));

        return true;
    }
}