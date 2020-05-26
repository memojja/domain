package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.discount.campaign.Campaign;

public interface IsCouponApplicable {
    default boolean isApplicable(Coupon coupon, ShoppingCart shoppingCart) {
        return shoppingCart.getNumberOfProducts() >= coupon.getMinimumProductAmount();
    }
}
