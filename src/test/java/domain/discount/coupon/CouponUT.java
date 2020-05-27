package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.core.ProductQuantityHolder;
import domain.discount.DiscountType;
import domain.discount.campaign.Campaign;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CouponUT {
    @Test
    public void givenValidCoupon_whenCalculateDiscount_thenDiscountMustbeAboveZero(){
        //given
        Category foods = new Category("Foods");
        Coupon coupon = new Coupon(10,15, DiscountType.AMOUNT);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.getNumberOfProducts()).thenReturn(20l);

        //when
        double discount = coupon.calculateDiscount(shoppingCart);

        //then
        assertEquals(discount,15);
    }

    @Test
    public void givenNonValidDCoupon_whenCalculateDiscount_thenDiscountMustbeero(){
        //given
        Category foods = new Category("Foods");
        Coupon coupon = new Coupon(10,15, DiscountType.AMOUNT);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.getNumberOfProducts()).thenReturn(1l);

        //when
        double discount = coupon.calculateDiscount(shoppingCart);

        //then
        assertEquals(discount,0);
    }
}
