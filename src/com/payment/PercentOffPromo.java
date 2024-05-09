package com.payment;

import java.time.LocalDate;

import com.user.*;

public class PercentOffPromo extends Promotion{
    private String promoName;
    private double rateDiscount;

    public PercentOffPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double rateDiscount) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.rateDiscount = rateDiscount;
    }

    public double getRateDiscount() {
        return rateDiscount;
    }

    @Override
    public boolean isCustomerEligible(User user) {
        if () {
            
        }
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        final int BATAS = 1_000_000;
        if (order.calulateCharges() >= BATAS) {
            
        }
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public int totalCashback() {
        return 0;
    }

    @Override
    public int totalDiscount() {
        return 0;
    }

    @Override
    public int calculateShippingDiscount() {
        return 0;
    }
}
