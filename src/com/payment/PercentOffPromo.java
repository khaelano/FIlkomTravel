package com.payment;

import java.time.LocalDate;

import com.user.*;

public class PercentOffPromo extends Promotion {
    private String promoName;
    private double discountPercentage;
    private int minimumPrice;

    public PercentOffPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double discountPercentage,
            int minimumPrice) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.discountPercentage = discountPercentage;
        this.minimumPrice = minimumPrice;
    }

    public String getPromoName() {
        return promoName;
    }

    public double getdiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public int compareTo(Promotion other) { 
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isCustomerEligible(Member member) {
        if (member.getMembershipDuration() > 30) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        if (order.calculatePrice() >= minimumPrice) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public int totalCashback(Order order) {
        return 0;
    }

    @Override
    public int totalDiscount(Order order) {
        if (isMinimumPriceEligible(order) == true) {
            double potongan = order.calculatePrice() * discountPercentage;
            return (int) potongan;
        } else {
            return 0;
        }
    }

    @Override
    public int calculateShippingDiscount(Order order) {
        return 0;
    }
}
