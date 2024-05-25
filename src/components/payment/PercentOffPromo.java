/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import java.time.LocalDate;

import components.user.*;

public class PercentOffPromo extends Promotion {
    private double discountPercentage;
    private long minimumPrice;

    public PercentOffPromo(int promoCode, String promoName, LocalDate mulai, LocalDate akhir, double discountPercentage,
            long minimumPrice) {
        super(promoCode, mulai, akhir);
        this.promoName = promoName;
        this.discountPercentage = discountPercentage;
        this.minimumPrice = minimumPrice;
    }

    public String getPromoName() {
        return promoName;
    }

    public double getdiscountPercentage() {
        return discountPercentage;
    }

    public boolean setOrder(Order order) {
        if (order == null || !isMinimumPriceEligible(order)) return false;

        this.order = order;

        return true;
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
        if (order.calculatePrice() >= minimumPrice) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isShippingDiscountEligible(Order order) {
        return false;
    }

    @Override
    public long totalCashback() {
        return 0;
    }

    @Override
    public long totalDiscount() {
        if (isMinimumPriceEligible(order) == true) {
            double potongan = order.calculatePrice() * discountPercentage;
            return (long) potongan;
        } else {
            return 0;
        }
    }

    @Override
    public long calculateShippingDiscount() {
        return 0;
    }
}
