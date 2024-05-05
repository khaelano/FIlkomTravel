package com.payment;

import com.user.*;

public class PercentOffPromo extends Promotion{
    @Override
    public boolean isCustomerEligible(Member memb) {
        if (memb.joindate) {
            
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int totalCashback() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int totalDiscount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int calculateShippingDiscount() {
        // TODO Auto-generated method stub
        return 0;
    }
}
