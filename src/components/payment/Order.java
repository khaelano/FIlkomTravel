/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

 package components.payment;

 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
import java.time.Duration;
 import java.time.temporal.ChronoUnit;

import components.car.*;
import components.user.*;
 
 public class Order {
     private static int counter;
     private LocalDate invoiceDate;
     private User renter;
     private int orderID;
     private Vehicle rentedVehicle;
     private int rentDuration;
     private LocalDate rentStartDate;
     private LocalDate rentEndDate;
     private double shippingFee;
     private double cashBack;
     private double totalDiscount;
     private OrderStatus status;
     private double subtotal;
 
     public double getSubtotal() {
         return subtotal;
     }
 
     public Order(Vehicle rentedVehicle, int rentDuration, User renter) {
         this.rentedVehicle = rentedVehicle;
         this.rentDuration = rentDuration;
         this.renter = renter;
         this.shippingFee = rentDuration * 100_000;
         
         this.invoiceDate = LocalDate.now();
         this.orderID = counter;
         counter++;
         this.rentEndDate = this.rentStartDate.plusDays(rentDuration);
     }
 
     public void setRentStartDate(String formattedDate) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         LocalDate proposedDate = LocalDate.parse(formattedDate, formatter);
         LocalDate currentDate = LocalDate.now();
 
         if (proposedDate.isAfter(currentDate)) {
             this.rentStartDate = proposedDate;
         } else {
             System.out.println("Error! The rent start date can't be before current date!");
         }
     }
 
     public String getRentStartDate() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         return this.rentStartDate.format(formatter);
     }
     
     public String getRentEndDate() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         return this.rentEndDate.format(formatter);
     }
 
     public String getInvoiceDate() {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         return this.invoiceDate.format(formatter);
     }
 
     public Car getRentedVehicle() {
         return this.rentedVehicle;
     }
 
     public int getRentDuration() {
         return this.rentDuration;
     }

     public void setRentDuration(int dur) {
        this.rentDuration = dur;
     }
 
     public int getOrderID() {
         return this.orderID;
     }
 
     public double getShippingFee() {
         return shippingFee;
     }
 
     public String getCarName() {
         return rentedVehicle.model;
     }
 
     public double calculateDuration() {
         long days = ChronoUnit.DAYS.between(rentStartDate, rentEndDate);
         return days * 24; // converting days to hours
     }
 
     public double calculatePrice() {
         return this.rentedVehicle.getRentalFee() * (calculateDuration() / 6); // assuming the rental fee is per 6 hours
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
         System.out.println("Car brand     : " + rentedVehicle.brand);
         System.out.println("Car model     : " + rentedVehicle.model);
         System.out.println("Car capacity  : " + rentedVehicle.getCapacity());
         System.out.println("Rental fee    : Rp" + rentedVehicle.getRentalFee() + " /6hr");
         System.out.println("Car quantity  : " + rentDuration);
 
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