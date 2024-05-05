package com.payment;

import com.user.*;

public interface Applicable {
    public boolean isCustomerEligible(User user);
    public boolean isMinimumPriceEligible(Order order);
    public boolean isShippingDiscountEligible(Order order);
    public int totalDiscount();
    public int totalCashback();
    public int calculateShippingDiscount();
}
