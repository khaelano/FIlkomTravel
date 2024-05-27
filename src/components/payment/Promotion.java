/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import components.user.Member;

public abstract class Promotion implements Applicable, Comparable<Promotion> {
    private String promoCode;
    private LocalDate promoStartDate, promoEndDate;
    private double percentage;
    private long maxPromoVal, minTranscTreshold;

    public Promotion(
        String promoCode, 
        LocalDate startDate, 
        LocalDate endDate, 
        double percentage,
        long maxPromoValue,
        long minTransactionTreshold
    ) {
        this.promoCode = promoCode;
        this.promoStartDate = startDate;
        this.promoEndDate = endDate;
        this.percentage = percentage;
        this.maxPromoVal = maxPromoValue;
        this.minTranscTreshold = minTransactionTreshold;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public LocalDate getPromoStartDate() {
        return promoStartDate;
    }

    public LocalDate getPromoEndDate() {
        return promoEndDate;
    }

    public double getPercentage() {
        return percentage;
    }
    
    public long getMaxPromoVal() {
        return maxPromoVal;
    }

    public long getMinTranscTreshold() {
        return minTranscTreshold;
    }

    @Override
    public boolean isCustomerEligible(Member member) {
        long duration = ChronoUnit.DAYS.between(member.getJoinDate(), LocalDate.now());
        return duration > 30;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        if (order.calculateSubTotal() >= getMinTranscTreshold()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public int compareTo(Promotion other) {
        return Double.compare(this.percentage, other.percentage);
    }
}