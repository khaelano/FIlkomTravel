package com.payment;

import java.time.LocalDate;

import com.user.*;

public class CashbackPromo extends Promotion{
    private String promoName;
    private double cashbackPercentage;
    private int minimumPrice;
    
    public CashbackPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double cashbackPercentage, int minimumPrice) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.cashbackPercentage = cashbackPercentage;
        this.minimumPrice = minimumPrice;
    }

    public double getCashbackPercentage() {
        return cashbackPercentage;
    }

    @Override
    public boolean setOrder(Order order) {
        if (order == null || !isMinimumPriceEligible(order)) return false;

        this.order = order;

        return true;
    }

    @Override
    public int calculateShippingDiscount() {
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
    public int totalCashback() {
        if (isMinimumPriceEligible(order) == true) {
            double cashback = order.calculatePrice() * cashbackPercentage;
            return (int) cashback;
        } else {
            return 0;
        }
    }

    @Override
    public int totalDiscount() {
        return 0;
    }

    public String getPromoName() {
        return promoName;
    }

}
