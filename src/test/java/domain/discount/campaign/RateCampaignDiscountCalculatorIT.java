package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.discount.DiscountType;
import domain.discount.campaign.Campaign;
import domain.discount.campaign.CampaignDiscountCalculator;
import domain.discount.campaign.RateCampaignDiscountCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RateCampaignDiscountCalculatorIT {

    @Test
    public void givenBelowMinimumProductAmount_whenApplyDiscounts_thenDiscountMustBeZero(){
        //given
        CampaignDiscountCalculator campaignDiscountCalculator = new RateCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2, DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,1);
        shoppingCart.applyDiscounts(campaign);

        //when
        double discount = campaignDiscountCalculator.calculate(campaign,shoppingCart);

        //then
        assertEquals(discount,0);
    }

    @Test
    public void givenTwoProduct_whenApplyDiscounts_thenDiscountMustBeOne(){
        //given
        CampaignDiscountCalculator campaignDiscountCalculator = new RateCampaignDiscountCalculator();

        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,2,DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,foods);

        shoppingCart.addItem(apple,2);
        shoppingCart.applyDiscounts(campaign);

        //when
        double discount = campaignDiscountCalculator.calculate(campaign,shoppingCart);

        //then
        assertEquals(discount,1.0);
    }

    @Test
    public void givenCampaingToSubCategory_whenApplyDiscounts_thenDiscountMustBeOne(){
        //given
        CampaignDiscountCalculator campaignDiscountCalculator = new RateCampaignDiscountCalculator();

        Category naturalFoods = new Category("Natural Foods");
        Category amasyaApples = new Category("Amasya Apples");
        naturalFoods.setParent(amasyaApples);
        Campaign campaign = new Campaign(naturalFoods,10.0,2,DiscountType.RATE);
        ShoppingCart shoppingCart = new ShoppingCart();

        Product apple = new Product("Apple",5.0,amasyaApples);
        shoppingCart.addItem(apple,2);
        shoppingCart.applyDiscounts(campaign);

        //when
        double discount = campaignDiscountCalculator.calculate(campaign,shoppingCart);

        //then
        assertEquals(discount,1.0);
    }
}
