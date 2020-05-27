package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.discount.DiscountType;
import domain.discount.campaign.Campaign;
import domain.discount.campaign.CampaignDiscountCalculator;
import domain.discount.campaign.RateCampaignDiscountCalculator;
import domain.discount.coupon.Coupon;
import domain.discount.coupon.CouponDiscountCalculator;
import domain.discount.coupon.RateCouponDiscountCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateCouponDiscountCalculatorIT {
    @Test
    public void givenTwoProduct_whenApplyCoupon_thenDiscountMustBeOne(){
        //given
        CouponDiscountCalculator couponDiscountCalculator = new RateCouponDiscountCalculator();

        Category foods = new Category("Foods");
        Coupon coupon = new Coupon(2,10.0, DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,2);
        shoppingCart.applyCoupon(coupon);

        //when
        double discount = couponDiscountCalculator.calculate(coupon,shoppingCart);

        //then
        assertEquals(discount,1.0);
    }

}
