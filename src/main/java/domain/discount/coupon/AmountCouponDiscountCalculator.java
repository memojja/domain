package domain.discount.coupon;

import domain.cart.ShoppingCart;

public class AmountCouponDiscountCalculator implements CouponDiscountCalculator, IsCouponApplicable {
    @Override
    public double calculate(Coupon coupon, ShoppingCart shoppingCart) {
        if(!isApplicable(coupon,shoppingCart))
            return 0;
        return coupon.getDiscount();
    }
}
