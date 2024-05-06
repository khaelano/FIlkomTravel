package com.payment;

import java.time.LocalDate;

import com.user.User;

public class CashbackPromo extends Promotion{
    private int promoCode;
    private String promoName;
    private LocalDate mulai;
    private LocalDate akhir;

    
    public CashbackPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir) {
        this.promoCode = promoCode;
        this.promoName = promoName;
        this.mulai = mulai;
        this.akhir = akhir;
    }

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
