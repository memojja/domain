package domain.discount;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.discount.campaign.AmountCampaignDiscountCalculator;
import domain.discount.campaign.Campaign;
import domain.discount.campaign.DiscountCalculator;
import domain.discount.campaign.RateCampaignDiscountCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmountCampaignDiscountCalculatorTest {
    @Test
    public void givenBelowMinimumProductAmount_whenApplyDiscounts_thenDiscountMustBeZero(){
        DiscountCalculator discountCalculator = new AmountCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2,DiscountType.AMOUNT);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,1);
        shoppingCart.applyDiscounts(campaign);
        double discount = discountCalculator.calculate(campaign,shoppingCart);

        assertEquals(discount,0);
    }

    @Test
    public void givenTwoProduct_whenApplyDiscounts_thenDiscountMustBeOne(){
        DiscountCalculator discountCalculator = new AmountCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2,DiscountType.AMOUNT);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,2);
        shoppingCart.applyDiscounts(campaign);
        double discount = discountCalculator.calculate(campaign,shoppingCart);

        assertEquals(discount,10.0);
    }

    @Test
    public void givenCampaingToSubCategory_whenApplyDiscounts_thenDiscountMustBeOne(){
        DiscountCalculator discountCalculator = new AmountCampaignDiscountCalculator();

        Category naturalFoods = new Category("Natural Foods");
        Category amasyaApples = new Category("Amasya Apples");
        naturalFoods.setParent(amasyaApples);
        Campaign campaign = new Campaign(naturalFoods,10.0,2,DiscountType.AMOUNT);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,amasyaApples);
        shoppingCart.addItem(apple,2);
        shoppingCart.applyDiscounts(campaign);
        double discount = discountCalculator.calculate(campaign,shoppingCart);

        assertEquals(discount,10.0);
    }
}
