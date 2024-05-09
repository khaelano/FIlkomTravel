package com.payment;

import java.time.LocalDate;

import com.user.User;

public class ShippingDiscount extends Promotion{
    private String promoName;
    private double rateDiscount;
    
    public ShippingDiscount(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double rateDiscount) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.rateDiscount = rateDiscount;
    }

    public double getRateDiscount() {
        return rateDiscount;
    }

    @Override
    public int calculateShippingDiscount() {
        int totalShippingDiscount = 1000;
        return totalShippingDiscount;
    }

    @Override
    public boolean isCustomerEligible(User user) {
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        System.out.println("DUMMY");
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        System.out.println("DUMMY");
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

    public int getPromoCode() {
        return promoCode;
    }

    public String getPromoName() {
        return promoName;
    }

    public LocalDate getMulai() {
        return mulai;
    }

    public LocalDate getAkhir() {
        return akhir;
    }
    
}
