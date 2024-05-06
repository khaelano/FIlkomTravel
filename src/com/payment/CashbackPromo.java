package com.payment;

import com.user.User;

public class CashbackPromo extends Promotion{

    @Override
    public int calculateShippingDiscount() {
        return 0;
    }

    @Override
    public boolean isCustomerEligible(User user) {
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public int totalCashback() {
        Order order;
        final int BATAS_DUMMY = 1_000_000;
        if (order.calulateCharges() >= BATAS_DUMMY) {
            
        } 
        return 0;
    }

    @Override
    public int totalDiscount() {
        // TODO Auto-generated method stub
        return 0;
    }

}
