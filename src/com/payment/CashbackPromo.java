package com.payment;

import java.time.LocalDate;

import com.user.*;

public class CashbackPromo extends Promotion{
    private double cashbackPercentage;
    private long minimumPrice;
    
    public CashbackPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double cashbackPercentage, long minimumPrice) {
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
    public long calculateShippingDiscount() {
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
    public long totalCashback() {
        if (isMinimumPriceEligible(order) == true) {
            double cashback = order.calculatePrice() * cashbackPercentage;
            return (long) cashback;
        } else {
            return 0;
        }
    }

    @Override
    public long totalDiscount() {
        return 0;
    }

    public String getPromoName() {
        return promoName;
    }

}
