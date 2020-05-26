package domain.discount;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.discount.campaign.Campaign;
import domain.discount.campaign.DiscountCalculator;
import domain.discount.campaign.RateCampaignDiscountCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RateCampaignDiscountCalculatorTest {

    @Test
    public void givenBelowMinimumProductAmount_whenApplyDiscounts_thenDiscountMustBeZero(){
        DiscountCalculator discountCalculator = new RateCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2,DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,1);
        shoppingCart.applyDiscounts(campaign);
        double discount = discountCalculator.calculate(campaign,shoppingCart);

        assertEquals(discount,0);
    }

    @Test
    public void givenTwoProduct_whenApplyDiscounts_thenDiscountMustBeOne(){
        DiscountCalculator discountCalculator = new RateCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2,DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,2);
        shoppingCart.applyDiscounts(campaign);
        double discount = discountCalculator.calculate(campaign,shoppingCart);

        assertEquals(discount,1.0);
    }

}
