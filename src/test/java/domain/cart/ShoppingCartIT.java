package domain.cart;

import domain.core.Category;
import domain.core.Product;
import domain.discount.campaign.Campaign;
import domain.discount.DiscountType;
import domain.discount.coupon.Coupon;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartIT {

    @Test
    public void givenAvailableProduct_whenAddCart_thenProductsQuantityIncrease(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Category category = new Category();
        Product banana = new Product("Banana",5,category);
        Product anotherBanana = new Product("Banana",5,category);

        //when
        shoppingCart.addItem(banana,20);
        shoppingCart.addItem(anotherBanana,20);

        //then
        int totalAmount = shoppingCart.getProducts().get(banana).getQuantity().get();
        assertEquals(totalAmount,40);
    }

    @Test
    public void given2Campain_whenApplyTheCart_thenApplyMaxAmounthOfDiscount(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Category foodCategory = new Category("Foods");

        Campaign campaign = new Campaign(foodCategory,20.0,2, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory,50.0,5, DiscountType.RATE);
        Campaign campaign3 = new Campaign(foodCategory,50.0,5, DiscountType.AMOUNT);

        //when
        shoppingCart.addItem(new Product("Apple",12,foodCategory),5);

        shoppingCart.applyDiscounts(campaign,campaign2,campaign3);

        //then
        assertEquals(shoppingCart.getCampaingDiscount(),50); //TODO
    }

    @Test
    public void given2CampainOneIsAfterDiscountPriceNegative_whenApplyTheCart_thenRejectThisCampain(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Category foodCategory = new Category("Foods");

        Campaign campaign = new Campaign(foodCategory,5.0,1, DiscountType.RATE);
        Campaign campaign2 = new Campaign(foodCategory,10.0,1, DiscountType.AMOUNT);
        Campaign campaign3 = new Campaign(foodCategory,50.0,1, DiscountType.AMOUNT);

        //when
        shoppingCart.addItem(new Product("Apple",1,foodCategory),10);
        shoppingCart.applyDiscounts(campaign,campaign2,campaign3);

        //then
        assertEquals(10,shoppingCart.getCampaingDiscount());
    }

    @Test
    public void givenAmounthCoupon_whenApplyTheCart_thenApplyDiscount(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Category foodCategory = new Category("Foods");

        Coupon coupon = new Coupon(2,5, DiscountType.AMOUNT);

        //when
        IntStream.range(1,3)
                .forEach(i -> shoppingCart.addItem(new Product(String.valueOf(i),i,foodCategory),1));
        shoppingCart.applyCoupon(coupon);

        //then
        assertEquals(shoppingCart.getCouponDiscount(),5);
    }

    @Test
    public void givenRateCoupon_whenApplyTheCart_thenApplyDiscount(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Category foodCategory = new Category("Foods");

        Coupon coupon = new Coupon(2,5, DiscountType.RATE);

        //when
        IntStream.range(1,3)
                .forEach(i -> shoppingCart.addItem(new Product(String.valueOf(i),i,foodCategory),1));
        shoppingCart.applyCoupon(coupon);

        //then
        assertEquals(shoppingCart.getCouponDiscount(),0.15);
    }


}
