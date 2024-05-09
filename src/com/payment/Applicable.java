package com.payment;

import com.user.*;

public interface Applicable {
    public boolean isCustomerEligible(Member member);
    public boolean isMinimumPriceEligible(Order order);
    public boolean isShippingDiscountEligible(Order order);
    public int totalDiscount(Order order);
    public int totalCashback(Order order);
    public int calculateShippingDiscount(Order order);
}
