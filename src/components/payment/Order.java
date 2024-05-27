/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.util.*;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import components.user.*;
import components.vehicle.*;
import utils.Converter;

public class Order {
    private static int counter;
    private int orderID;
    private LocalDate invoiceDate;
    private User renter;
    private ArrayList<OrderItem> orderItems;
    private OrderStatus status;
    private Promotion appliedPromo;
    private long discount;
    private long cashback;

    public Order(User renter) {
        this.renter = renter;
        this.orderItems = new ArrayList<>();
        this.status = OrderStatus.UNPAID;
    }

    public boolean addVehicle(Vehicle rentedVehicle, LocalDate startDate, int duration) {
        if (contains(rentedVehicle.getVehicleID())) {
            // Will return false if the vehicle is already there
            return false;
        }

        orderItems.add(new OrderItem(rentedVehicle, startDate, duration));
        return true;
    }

    public boolean removeVehicle(Vehicle rentedVehicle) {
        // Check if the item is in the cart
        OrderItem removedVehicle = search(rentedVehicle.getVehicleID());
        if (removedVehicle == null) {
            return false;
        }

        orderItems.remove(removedVehicle);
        return true;
    }

    public int incrVehicle(String rentedVehicleID, int duration) {
        OrderItem rentedVehicle = search(rentedVehicleID);
        if (rentedVehicle != null) {
            rentedVehicle.incrDuration(duration);
            return rentedVehicle.getRentDuration();
        }

        return -1;
    }

    public int decrVehicle(String rentedVehicleID, int duration) {
        OrderItem rentedVehicle = search(rentedVehicleID);
        if (rentedVehicle != null) {
            rentedVehicle.decrDuration(duration);
            return rentedVehicle.getRentDuration();
        }

        return -1;
    }

    public long calculateSubTotal() {
        long total = 0;
        for (OrderItem item : orderItems) {
            if (item != null) {
                total += item.getTotal();
            }
        }
        return total;
    }

    public long calculateTotal() {
        return calculateSubTotal() + getShippingFee();
    }

    public long getShippingFee() {
        // TODO: Implements shipping fee
        return 0;
    }

    public boolean checkOut() {
        // Check if the balance is sufficient
        long toPay = (calculateTotal() - this.discount);
        if (this.renter.getBalance() -  toPay < 0) return false;

        // Create order ID
        counter++;
        this.orderID = counter;

        // Create invoice date
        this.invoiceDate = LocalDate.now();

        // Decrease user balance and increase user balance if there's cashback
        this.renter.decrBalance(calculateTotal() - this.discount);
        if (this.cashback != 0) this.renter.incrBalance(this.cashback);
        this.status = OrderStatus.SUCCESSFUL;
        return true;
    }

    public void printDetails() {
        System.out.printf("Kode Pemesan : %s\n", this.renter.getUserID());
        System.out.printf("Nama : %s\n", this.renter.getFullName());
        if (this.status == OrderStatus.SUCCESSFUL) {
            System.out.println("Nomor Pesanan: " + getOrderID());
            System.out.println("Tanggal Pesanan: " + getFormattedInvoiceDate());
        }
        System.out.printf("%3s | %-25s | %4s | %8s \n", "No", "Menu", "Dur.", "Subtotal");
        System.out.println("=================================================");
        
        int counter = 1;
        orderItems.sort(Comparator.comparing(OrderItem::getRentStartDate).thenComparing(OrderItem::getTotal));
        for (OrderItem item : orderItems) {
            if (item != null) {
                Vehicle vehicle = item.vehicle;
                System.out.printf(
                    "%3d | %-25s | %4d | %8s \n", 
                    counter, 
                    (vehicle.getVehicleName() + " " + vehicle.getLicenseNumber()), 
                    item.getRentDuration(),
                    item.getTotal()
                );
                
                System.out.printf("      %s - %s\n", Converter.LocalDateToString(item.rentStartDate), Converter.LocalDateToString(item.rentEndDate));
                counter++;
            }
        }

        System.out.println("=================================================");
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
        System.out.printf("%-32s: %15s\n", "Sub Total", nf.format(calculateSubTotal()));
        if (this.appliedPromo != null && this.appliedPromo instanceof RegularDiscount) {
            System.out.printf("%-32s: %15s\n", ("PROMO: " + this.appliedPromo.getPromoCode()), nf.format(-1*this.discount));
        }
        System.out.println("=================================================");
        if (this.appliedPromo != null && this.appliedPromo instanceof Cashback) {
            System.out.printf("%-32s: %15s\n", ("PROMO: " + this.appliedPromo.getPromoCode()), nf.format(this.cashback));
        }
        System.out.printf("%-32s: %15s\n", "Total", nf.format(calculateTotal()));
        System.out.printf("%-32s: %15s\n", "Saldo", nf.format(this.renter.getBalance()));
    }

    public boolean applyPromo(Promotion promo) {
        // Check eligibility {
        if (!(this.renter instanceof Member && promo.isCustomerEligible((Member) this.renter) && promo.isMinimumPriceEligible(this))) {
            return false;
        }

        this.appliedPromo = promo;
        this.cashback = promo.totalCashback(this) > promo.getMaxPromoVal() ? promo.getMaxPromoVal() : promo.totalCashback(this);
        this.discount = promo.totalDiscount(this) > promo.getMaxPromoVal() ? promo.getMaxPromoVal() : promo.totalDiscount(this);
        return true;
    }

    public long getDiscountVal() {
        return this.discount;
    }

    public boolean contains(String vehicleID) {
        return search(vehicleID) != null;
    }

    public int getDuration(String vehicleID) {
        return search(vehicleID).getRentDuration();
    }

    public int getOrderID() {
        return this.orderID;
    }

    public String getFormattedInvoiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.forLanguageTag("id"));
        return this.invoiceDate.format(formatter);
    }

    public Promotion getAppliedPromo() {
        return appliedPromo;
    }

    public int getCarNumber() {
        int counter = 0;
        for (OrderItem item : orderItems) {
            if (item.vehicle instanceof Car) counter++;
        }
        return counter;
    }

    public int getBikeNumber() {
        int counter = 0;
        for (OrderItem item : orderItems) {
            if (item.vehicle instanceof Bike) counter++;
        }
        return counter;
    }

    private OrderItem search(String vehicleID) {
        for (OrderItem item : orderItems) {
            if (item.itemID.equals(vehicleID)) {
                return item;
            }
        }

        return null;
    }
}