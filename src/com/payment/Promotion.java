package com.payment;

import java.time.LocalDate;

public abstract class Promotion implements Applicable, Comparable<Promotion> {
    public String getPromoName() {
        return promoName;
    }

    protected String promoName;
    protected int promoCode;
    protected LocalDate mulai;
    protected LocalDate akhir;
    protected Order order;
    
    public Promotion(int promoCode, LocalDate mulai, LocalDate akhir) {
        this.promoCode = promoCode;
        this.mulai = mulai;
        this.akhir = akhir;
    }

    public abstract boolean setOrder(Order order);

    public int getPromoCode() {
        return promoCode;
    }

    public LocalDate getMulai() {
        return mulai;
    }

    public LocalDate getAkhir() {
        return akhir;
    }

    public void resetOrder() {
        this.order = null;
    }

    @Override
    public int compareTo(Promotion other) {
        double otherDiscount = 0;
        otherDiscount += other.calculateShippingDiscount();
        otherDiscount += other.totalCashback();
        otherDiscount += other.totalDiscount();

        double thisDiscount = 0;
        thisDiscount += other.calculateShippingDiscount();
        thisDiscount += other.totalCashback();
        thisDiscount += other.totalDiscount();

        if (thisDiscount > otherDiscount)
            return 1;
        else if (thisDiscount < otherDiscount)
            return -1;
        else
            return 0;
    }
}
