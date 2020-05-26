package domain.cart;

import domain.discount.Coupon;

public interface CouponDiscountApplicable {
    void applyCoupon(Coupon coupon);
}
