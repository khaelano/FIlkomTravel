package com.payment;

import java.time.LocalDate;

import com.user.*;

public class PercentOffPromo extends Promotion{
    private int promoCode;
    private String promoName;
    private LocalDate mulai;
    private LocalDate akhir;

    public PercentOffPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir) {
        this.promoCode = promoCode;
        this.promoName = promoName;
        this.mulai = mulai;
        this.akhir = akhir;
    }

    @Override
    public boolean isCustomerEligible(User user) {
        if () {
            
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

    @Override
    public int calculateShippingDiscount() {
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
