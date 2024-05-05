package com.payment;

import com.user.Member;

public interface Applicable {
    public boolean isCustomerEligible(Member memb);
    public boolean isMinimumPriceEligible(Order order);
    public boolean isShippingDiscountEligible(Order order);
    public int totalDiscount();
    public int totalCashback();
    public int calculateShippingDiscount();
}
