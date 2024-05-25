/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;

public abstract class Promotion implements Applicable, Comparable<Promotion> {
    public String getPromoName() {
        return promoName;
    }

    protected String promoName;
    protected int promoCode;
    protected LocalDate promoStartDate;
    protected LocalDate promoEndDate;
    protected Order order;
    
    public Promotion(int promoCode, LocalDate startDate, LocalDate endDate) {
        this.promoCode = promoCode;
        this.promoStartDate = startDate;
        this.promoEndDate = endDate;
    }

    public abstract boolean setOrder(Order order);

    public int getPromoCode() {
        return promoCode;
    }

    public LocalDate getPromoStartDate() {
        return promoStartDate;
    }

    public LocalDate getPromoEndDate() {
        return promoEndDate;
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
