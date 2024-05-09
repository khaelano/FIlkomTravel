package com.payment;

import java.time.LocalDate;

public abstract class Promotion implements Applicable, Comparable {
    protected int promoCode;
    protected LocalDate mulai;
    protected LocalDate akhir;
    
    public Promotion(int promoCode, LocalDate mulai, LocalDate akhir) {
        this.promoCode = promoCode;
        this.mulai = mulai;
        this.akhir = akhir;
    }

    public int getPromoCode() {
        return promoCode;
    }

    public LocalDate getMulai() {
        return mulai;
    }

    public LocalDate getAkhir() {
        return akhir;
    }
    
}
