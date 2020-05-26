package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.discount.campaign.Campaign;

public interface CouponDiscountCalculator {
    double calculate(Coupon coupon, ShoppingCart shoppingCart);
}
