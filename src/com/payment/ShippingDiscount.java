package com.payment;

import com.user.User;

public class ShippingDiscount extends Promotion{

    @Override
    public int calculateShippingDiscount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isCustomerEligible(User user) {
        // TODO Auto-generated method stub
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
    
}
