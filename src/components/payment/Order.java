/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import components.car.*;
import components.user.*;

import java.time.Duration;

public class Order {
    private static int counter;
    private LocalDateTime invoiceDate;
    private User renter;
    private int orderID;
    private Car rentedCar;
    private int carQuantity;
    private LocalDateTime rentStartDate;
    private LocalDateTime rentEndDate;
    private double shippingFee;
    private double cashBack;
    private double totalDiscount;
    private OrderStatus status;
    private double subtotal;

    public double getSubtotal() {
        return subtotal;
    }

    public Order(Car rentedCar, int quantity, User renter) {
        this.rentedCar = rentedCar;
        this.carQuantity = quantity;
        this.renter = renter;
        this.shippingFee = quantity * 100_000;

        this.invoiceDate = LocalDateTime.now();
        this.orderID = counter;
        counter++;
    }

    public void setRentStartDate(String formattedDateAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        if (proposedDate.isAfter(currentDate)) {
            this.rentStartDate = proposedDate;
        } else {
            System.out.println("Error! The rent start date can't be before current date!");
        }
    }

    public void setRentEndDate(String formattedDateAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime proposedDate = LocalDateTime.parse(formattedDateAndTime, formatter);

        if (proposedDate.isAfter(this.rentStartDate)) {
            this.rentEndDate = proposedDate;
        } else {
            System.out.println("Error! The rent end date can't be before rent start date!");
        }
    }

    public String getRentStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentStartDate.format(formatter);
    }
    
    public String getRentEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.rentEndDate.format(formatter);
    }

    public String getInvoiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.invoiceDate.format(formatter);
    }

    public Car getRentedCar() {
        return this.rentedCar;
    }

    public int getCarQuantity() {
        return this.carQuantity;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public String getCarName() {
        return rentedCar.model;
    }

    public double calculateDuration() {
        Duration duration = Duration.between(rentStartDate, rentEndDate);
        return Math.ceil(duration.getSeconds() / 3600);
    }

    public double calculatePrice() {
        return this.rentedCar.getRentalFee() * (calculateDuration()/4);
    }

    public boolean checkOut() {
        if (this.totalDiscount == 0) this.subtotal = calculatePrice() + shippingFee;
        this.status = OrderStatus.UNPAID;
        return true;
    }

    public void printDetails() {
        System.out.println("---- Invoice details ----");
        System.out.println("Invoice date  :" + getInvoiceDate());
        if (status == OrderStatus.SUCCESSFUL)
            System.out.println("Invoice ID   : -");

        System.out.println("---- Car Details ---- ");
        System.out.println("Car brand     : " + rentedCar.brand);
        System.out.println("Car model     : " + rentedCar.model);
        System.out.println("Car capacity  : " + rentedCar.getCapacity());
        System.out.println("Rental fee    : Rp" + rentedCar.getRentalFee() + " /6hr");
        System.out.println("Car quantity  : " + carQuantity);

        System.out.println("---- Rent details ----");
        System.out.println("Start date    : " + getRentStartDate());
        System.out.println("End date      : " + getRentEndDate());
        System.out.printf("Duration (hr) : %d\n", (int) calculateDuration());

        System.out.println("---- Billing details ----");
        System.out.printf("Delivery fee  : Rp%d\n", (long) shippingFee);
        System.out.printf("Rent bill     : Rp%d\n", (long) calculatePrice());
        if (this.subtotal != 0)
            System.out.printf("Total         : Rp%d\n", (long) subtotal);
        if (this.totalDiscount != 0)
            System.out.printf("After promo   : Rp%d\n", (long) subtotal);
            
        if (this.cashBack != 0)
            System.out.printf("Cashback      : Rp%d\n", (long) cashBack);

        System.out.println();
    }

    public boolean applyPromo(Promotion promo) {
        if (promo == null) return false;
        boolean result = false;

        if (this.renter instanceof Member) {
            Member member = (Member) renter;
            if (promo.isCustomerEligible(member) && promo.isMinimumPriceEligible(this)) {
                this.totalDiscount += promo.totalDiscount();
                this.cashBack += promo.totalCashback();
                result = true;
            }

            if (promo.isShippingDiscountEligible(this)) {
                this.shippingFee -= promo.calculateShippingDiscount();
                result = true;
            }
        }

        this.subtotal = calculatePrice() - totalDiscount;

        return result;
    }

    public boolean pay() {
        this.status = OrderStatus.SUCCESSFUL;
        return true;
    }
}