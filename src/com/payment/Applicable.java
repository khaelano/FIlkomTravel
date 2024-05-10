package com.payment;

import com.user.*;

public interface Applicable {
    public boolean isCustomerEligible(Member member);
    public boolean isMinimumPriceEligible(Order order);
    public boolean isShippingDiscountEligible(Order order);
    public long totalDiscount();
    public long totalCashback();
    public long calculateShippingDiscount();
}
