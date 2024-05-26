/*
 * 235150200111051 Khaelano Abroor Maulana
 * 235150200111057 Arza Marevi Bangun
 * 235150207111058 Muhammad Lutfi Aziz
 * 235150207111059 Nabiel Tatra Edy Firdaus
 */

package components.payment;

import components.user.*;

public interface Applicable {
    public boolean isCustomerEligible(Member member);
    public boolean isMinimumPriceEligible(Order order);
    public boolean isShippingDiscountEligible(Order order);
    public long totalDiscount(Order order);
    public long totalCashback(Order order);
    public long calculateShippingDiscount(Order order);
}
