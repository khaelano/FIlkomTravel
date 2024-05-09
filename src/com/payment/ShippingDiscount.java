package com.payment;

import java.time.LocalDate;

import com.user.*;

public class ShippingDiscount extends Promotion {
    private String promoName;
    private double discountPercentage;
    private int minimumShippingFee;

    public ShippingDiscount(int promoCode, String promoName, LocalDate mulai, LocalDate akhir,
            double discountPercentage, int minimumShippingFee) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.discountPercentage = discountPercentage;
        this.minimumShippingFee = minimumShippingFee;
    }

    @Override
    public int compareTo(Promotion other) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getPromoName() {
        return promoName;
    }

    public double getdiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public int calculateShippingDiscount(Order order) {
        if (isShippingDiscountEligible(order) == true) {
            double potongan = order.getShippingFee() * discountPercentage;
            return (int) potongan;
        } else {
            return 0;
        }
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
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        if (order.getShippingFee() > minimumShippingFee) {
            return true;
        }
        return false;
    }

    @Override
    public int totalCashback(Order order) {
        return 0;
    }

    @Override
    public int totalDiscount(Order order) {
        return 0;
    }

}
