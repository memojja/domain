package domain.discount.coupon;

import domain.cart.ShoppingCart;

public class RateCouponDiscountCalculator implements CouponDiscountCalculator, IsCouponApplicable {
    @Override
    public double calculate(Coupon coupon, ShoppingCart shoppingCart) {
        if(!isApplicable(coupon,shoppingCart))
            return 0;
        return shoppingCart.getTotalProductPrice() * coupon.getDiscount() / 100.0;
    }
}
