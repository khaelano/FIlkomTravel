/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;

import components.user.*;

public class Cashback extends Promotion {
    public Cashback(
        String promoCode, 
        LocalDate promoStartDate, 
        LocalDate promoEndDate, 
        double cashbackPercentage, 
        long maxCashbackVal,
        long minTranscTreshold
    ) {
        super(
            promoCode, 
            promoStartDate, 
            promoEndDate,
            cashbackPercentage,
            maxCashbackVal,
            maxCashbackVal
        );
    }

    @Override
    public boolean isCustomerEligible(Member member) {
        if (member.getMembershipDuration() > 30) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order order) {
        if (order.calculatePrice() >= super.getMinTranscTreshold()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public long totalCashback(Order order) {
        if (isMinimumPriceEligible(order)) {
            double cashback = order.calculatePrice() * super.getPercentage();
            return (long) cashback;
        } else {
            return 0;
        }
    }

    @Override
    public long calculateShippingDiscount(Order order) {
        return 0;
    }

    @Override
    public long totalDiscount(Order order) {
        return 0;
    }
}
