/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;

public class RegularDiscount extends Promotion {
    public RegularDiscount(
        String promoCode, 
        LocalDate promoStartDate, 
        LocalDate promoEndDate, 
        double discountPercentage,
        long maxDiscountVal,
        long minTranscTreshold
    ) {
        super(
            promoCode, 
            promoStartDate, 
            promoEndDate,
            discountPercentage,
            maxDiscountVal,
            minTranscTreshold
        );
    }

    @Override
    public long totalCashback(Order order) {
        return 0;
    }

    @Override
    public long totalDiscount(Order order) {
        if (isMinimumPriceEligible(order)) {
            double potongan = order.calculateSubTotal() * super.getPercentage();
            return (long) potongan;
        } else {
            return 0;
        }
    }

    @Override
    public long calculateShippingDiscount(Order order) {
        return 0;
    }
}
