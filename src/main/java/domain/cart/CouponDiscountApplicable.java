package domain.cart;

import domain.discount.coupon.Coupon;

public interface CouponDiscountApplicable {
    void applyCoupon(Coupon coupon);
    double getCouponDiscount();
}
